package cz.upce.fei.cv05.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private ProfileDto profile;
}
