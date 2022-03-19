package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.Post;
import cz.upce.fei.cv05.entity.Reaction;
import cz.upce.fei.cv05.entity.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostService extends JpaRepository<Post, Long> {
    void add(String heading, String content);
    void update(Long id, String heading, String content);
    Post getPost(Long id);
    List<Post> getProfilePosts(Long profileId);
    List<Post> getChildPosts(Long postId);
    List<Reaction> getPostReactions(Long postId);
    void addPostReaction(Long postId, Long profileId, ReactionType reactionType);
    void remove(Long id);
}
