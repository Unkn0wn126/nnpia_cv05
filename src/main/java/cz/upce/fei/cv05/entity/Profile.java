package cz.upce.fei.cv05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "profile")
    private Set<Post> profilePosts;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private User user;

    @Column()
    private String name;

    @Column()
    private String surname;

    @Column(columnDefinition = "text")
    private String quote;

    @Column()
    private Date dateOfBirth;

    @Column()
    private String pathToImage;
}
