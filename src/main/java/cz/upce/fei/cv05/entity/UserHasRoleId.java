package cz.upce.fei.cv05.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserHasRoleId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long userRoleId;
}
