package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "profile")
    private Set<Post> profilePosts;

    @Getter
    @Setter
    @OneToOne(optional = false, mappedBy = "profile")
    private User user;

    @Getter
    @Setter
    @Column()
    private String name;

    @Getter
    @Setter
    @Column()
    private String surname;

    @Getter
    @Setter
    @Column()
    private String quote;

    @Getter
    @Setter
    @Column()
    private Date dateOfBirth;
}
