package cz.upce.fei.cv05.dto;

import cz.upce.fei.cv05.dto.ReactionDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class PostDto implements Serializable {
    private Long id;
    private String heading;
    private String content;
    private Set<ReactionDto> postReactions;
    private Date postedAt;
    private ProfileDto profile;
}
