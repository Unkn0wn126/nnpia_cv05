package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    @Query("select p from Post p where p.id between :startId and :endId")
    List<Post> findPostByIdBetween(@Param("startId") Long start, @Param("endId") Long finish);

    @Query("select p from Post p where p.profile.id = :profileId")
    List<Post> findPostsByProfileId(@Param("profileId") Long id);

    @Query("select p from Post p where p.parentPost.id = :parentPostId")
    List<Post> findPostsByParentPostId(@Param("parentPostId") Long id);
}
