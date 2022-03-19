package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findById(Long id);

    @Query("select r from Reaction r where r.post.id = 1")
    List<Reaction> findReactionByPostId(Long id);

    @Query("select r from Reaction r where r.profile.id = 1")
    List<Reaction> findReactionByProfileId(Long id);
}
