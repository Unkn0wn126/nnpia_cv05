package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UserHasRoleId implements Serializable {
    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private Long userRoleId;

    public UserHasRoleId(Long userId, Long userRoleId) {
        this.userId = userId;
        this.userRoleId = userRoleId;
    }
}
