package cz.upce.fei.cv05;

import cz.upce.fei.cv05.entity.Profile;
import cz.upce.fei.cv05.entity.User;
import cz.upce.fei.cv05.service.ProfileService;
import cz.upce.fei.cv05.service.SessionService;
import cz.upce.fei.cv05.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
class ProfileServiceTests {

    @Autowired
    ProfileService profileService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Test
    void addProfileToUser() {
        userService.add("root", "email@example.com", "1234");
        userService.login("root", "1234");
        profileService.add(sessionService.getUser().getId(), "Admin", "Root", "I am root!", new Date(0), null);

        Profile profile = profileService.getProfileByUserId(sessionService.getUser().getId());
        Assertions.assertThat(profile.getUser().getId()).isEqualTo(sessionService.getUser().getId());
    }

}
