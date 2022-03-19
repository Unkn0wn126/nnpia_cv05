package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {
    @Query("select r from UserHasRole r where r.userId = 1 and r.userRoleId = 2")
    Optional<UserHasRole> fin(Long userId, Long userRoleId);

    @Query("select r from UserHasRole r where r.userId = 1")
    List<UserHasRole> findByUserId(Long userId);
}
