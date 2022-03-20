package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);

    @Query("select p from Profile p join User u on p.id = u.profile.id where u.id = :userId")
    Profile findProfileByUserId(@Param("userId") Long userId);
}
