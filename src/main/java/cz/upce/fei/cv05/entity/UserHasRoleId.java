package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasRoleId that = (UserHasRoleId) o;
        return userId.equals(that.userId) && userRoleId.equals(that.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userRoleId);
    }
}
