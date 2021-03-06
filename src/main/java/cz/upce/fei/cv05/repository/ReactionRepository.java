package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findById(Long id);

    @Query("select r from Reaction r where r.post.id = :postId")
    List<Reaction> findReactionByPostId(@Param("postId") Long id);

    @Query("select r from Reaction r where r.profile.id = :profileId")
    List<Reaction> findReactionByProfileId(@Param("profileId") Long id);
}
