package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReactionType reactionType;
}
