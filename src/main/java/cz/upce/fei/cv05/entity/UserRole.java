package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 20)
    private UserRoleType userRoleType;

    @OneToMany(mappedBy = "user")
    private Set<UserHasRole> userRoles;
}
