package cz.upce.fei.cv05.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Data
@Embeddable
public class UserHasRoleId implements Serializable {

    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @GeneratedValue
    @Column(name = "role_id")
    private Long userRoleId;

    public UserHasRoleId(Long userId, Long userRoleId) {
        this.userId = userId;
        this.userRoleId = userRoleId;
    }

    public UserHasRoleId() {

    }
}
