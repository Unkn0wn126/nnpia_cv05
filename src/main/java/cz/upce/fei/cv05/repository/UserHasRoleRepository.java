package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.UserHasRole;
import cz.upce.fei.cv05.entity.UserHasRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHasRoleRepository extends JpaRepository<UserHasRole, UserHasRoleId> {
    @Query("select r from UserHasRole r where r.user.id = :userId and r.role.id = :userRoleId")
    Optional<UserHasRole> findById(@Param("userId") Long userId, @Param("userRoleId") Long userRoleId);

    @Query("select r from UserHasRole r where r.user.id = :userId")
    List<UserHasRole> findByUserId(@Param("userId") Long userId);
}
