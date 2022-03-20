package cz.upce.fei.cv05.controller;

import cz.upce.fei.cv05.dto.PostDto;
import cz.upce.fei.cv05.dto.ProfileDto;
import cz.upce.fei.cv05.dto.ReactionDto;
import cz.upce.fei.cv05.entity.Post;
import cz.upce.fei.cv05.entity.Profile;
import cz.upce.fei.cv05.entity.Reaction;
import cz.upce.fei.cv05.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private final SessionService sessionService;

    private final PostService postService;

    private final ProfileService profileService;

    private final UserService userService;

    private final FileService fileService;

    public PostController(SessionService sessionService, PostService postService, ProfileService profileService, UserService userService, FileService fileService) {
        this.sessionService = sessionService;
        this.postService = postService;
        this.profileService = profileService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){ return "error"; }

    @GetMapping("/post-list")
    public String showAllPosts(Model model){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }
        List<Post> all = postService.getAllPosts();
        List<PostDto> postDtos = all.stream().map(this::convertToDto).collect(Collectors.toList());

        model.addAttribute("posts", postDtos);
        return "post-list";
    }

    private PostDto convertToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostedAt(post.getPostedAt());
        postDto.setContent(post.getContent());
        postDto.setHeading(post.getHeading());
        Profile profile = post.getProfile();
        ProfileDto author = new ProfileDto();
        author.setName(profile.getName());
        author.setSurname(profile.getSurname());
        author.setDateOfBirth(profile.getDateOfBirth().toString());
        author.setId(profile.getId());
        author.setQuote(profile.getQuote());
        author.setPathToImage(profile.getPathToImage());
        postDto.setProfile(author);
        postDto.setPostReactions(new HashSet<>());
        for (Reaction reaction:
                post.getPostReactions()) {
            ReactionDto reactionDto = new ReactionDto();
            reactionDto.setId(reaction.getId());
            reactionDto.setReactionType(reaction.getReactionType());

            Profile reactionProfile = post.getProfile();
            ProfileDto reactionAuthor = new ProfileDto();
            reactionAuthor.setName(reactionProfile.getName());
            reactionAuthor.setSurname(reactionProfile.getName());
            reactionAuthor.setDateOfBirth(reactionProfile.getDateOfBirth().toString());
            reactionAuthor.setId(reactionProfile.getId());
            reactionAuthor.setQuote(reactionProfile.getQuote());
            // reactionAuthor.setImage(reactionProfile.getPathToImage());
            reactionDto.setProfile(reactionAuthor);

            postDto.getPostReactions().add(reactionDto);
        }

        return postDto;
    }

    @GetMapping("/post-detail/{id}")
    public String showPostDetail(@PathVariable(required = false) Long id, Model model){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }

        Post post = postService.getPost(id);
        PostDto postDto = convertToDto(post);
        List<Post> childPosts = postService.getChildPosts(id);

        List<PostDto> childPostsDto = childPosts.stream().
                map(this::convertToDto).
                collect(Collectors.toList());

        model.addAttribute("post", postDto);
        model.addAttribute("childPosts", childPostsDto);

        return "post-detail";
    }

    @GetMapping("/post-form")
    public String showPostForm(Model model){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }

        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);

        return "create-post";
    }

    @PostMapping("/post-form-process")
    public String postFormProcess(PostDto postDto){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }

        Post post = new Post();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setHeading(postDto.getHeading());
        Profile profile = profileService.getProfileByUserId(sessionService.getUser().getId());
        post.setProfile(profile);

        postService.add(post);
        return "redirect:/post-list";
    }
}
