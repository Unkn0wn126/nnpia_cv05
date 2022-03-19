package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findById(Long id);

    @Query("select role from user_roles role join UserHasRole ur on ur.role.id = role.id where ur.user.id = 1")
    List<UserRole> findUserRoleByUserId(Long id);
}
