package cz.upce.fei.cv05.dto;

import cz.upce.fei.cv05.entity.ReactionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReactionDto implements Serializable {
    private Long id;
    private ProfileDto profile;
    private ReactionType reactionType;
}
