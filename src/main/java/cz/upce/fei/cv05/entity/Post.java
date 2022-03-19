package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 50)
    private String heading;

    @Getter
    @Setter
    @Column(length = 255)
    private String content;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Getter
    @Setter
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_post_id")
    private Post parentPost;

    @Getter
    @Setter
    @OneToMany(mappedBy = "post")
    private Set<Reaction> postReactions;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date postedAt;
}
