package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.UserHasRole;
import cz.upce.fei.cv05.entity.UserHasRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHasRoleRepository extends JpaRepository<UserHasRole, UserHasRoleId> {
    @Query("select r from UserHasRole r where r.user.id = 1 and r.role.id = 2")
    Optional<UserHasRole> findById(Long userId, Long userRoleId);

    @Query("select r from UserHasRole r where r.user.id = 1")
    List<UserHasRole> findByUserId(Long userId);
}
