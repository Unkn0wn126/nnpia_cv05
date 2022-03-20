package cz.upce.fei.cv05.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProfileDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String quote;
    private String dateOfBirth;
    private MultipartFile image;
    private String pathToImage;
}
