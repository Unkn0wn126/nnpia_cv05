package cz.upce.fei.cv05.repository;

import cz.upce.fei.cv05.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    @Query("select u from User u where u.name = :userName")
    User findByUserName(@Param("userName") String userName);
}
