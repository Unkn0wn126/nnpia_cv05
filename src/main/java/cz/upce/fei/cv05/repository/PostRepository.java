package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    @Query("select p from Post p where p.id between 1 and 2")
    List<Post> findPostByIdBetween(Long start, Long finish);

    @Query("select p from Post p where p.profile.id = 1")
    List<Post> findPostsByProfileId(Long id);

    @Query("select p from Post p where p.parentPost.id = 1")
    List<Post> findPostsByParentPostId(Long id);
}
