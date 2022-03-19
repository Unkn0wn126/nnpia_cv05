package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(UserHasRoleId.class)
public class UserHasRole {

    @Id
    @Getter
    @Setter
    private Long userId;

    @Id
    @Getter
    @Setter
    private Long userRoleId;
}
