package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.Post;
import cz.upce.fei.cv05.entity.Reaction;
import cz.upce.fei.cv05.entity.ReactionType;
import cz.upce.fei.cv05.repository.PostRepository;
import cz.upce.fei.cv05.repository.ProfileRepository;
import cz.upce.fei.cv05.repository.ReactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final SessionService sessionService;

    private final ProfileRepository profileRepository;

    private final ReactionRepository reactionRepository;

    public PostServiceImpl(PostRepository postRepository, SessionService sessionService, ProfileRepository profileRepository, ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.sessionService = sessionService;
        this.profileRepository = profileRepository;
        this.reactionRepository = reactionRepository;
    }

    @Override
    public void add(Post post) {
        postRepository.save(post);
    }

    @Override
    public void update(Long id, String heading, String content) {
        Post post = postRepository.findById(id).get();
        post.setHeading(heading);
        post.setContent(content);

        postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getProfilePosts(Long profileId) {
        return postRepository.findPostsByProfileId(profileId);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts;
    }

    @Override
    public List<Post> getChildPosts(Long postId) {
        return postRepository.findPostsByParentPostId(postId);
    }

    @Override
    public List<Reaction> getPostReactions(Long postId) {
        return reactionRepository.findReactionByPostId(postId);
    }

    @Override
    public void addPostReaction(Long postId, Long profileId, ReactionType reactionType) {
        Reaction reaction = new Reaction();
        reaction.setReactionType(reactionType);
        reaction.setProfile(profileRepository.findById(profileId).get());
        reaction.setPost(postRepository.findById(postId).get());

        reactionRepository.save(reaction);
    }

    @Override
    public void remove(Long id) {
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }
}
