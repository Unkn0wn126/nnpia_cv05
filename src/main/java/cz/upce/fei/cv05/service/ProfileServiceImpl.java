package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.Profile;
import cz.upce.fei.cv05.entity.User;
import cz.upce.fei.cv05.entity.UserRole;
import cz.upce.fei.cv05.entity.UserRoleType;
import cz.upce.fei.cv05.repository.ProfileRepository;
import cz.upce.fei.cv05.repository.UserRepository;
import cz.upce.fei.cv05.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@SessionScope
public class ProfileServiceImpl implements ProfileService {

    private final SessionService sessionService;

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserRoleRepository userRoleRepository;

    public ProfileServiceImpl(SessionService sessionService, UserRepository userRepository, ProfileRepository profileRepository, UserRoleRepository userRoleRepository) {
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void add(Long userId, String name, String surname, String quote, Date dateOfBirth) {
        if (sessionService.getUser() == null){
            throw new RuntimeException();
        }
        List<UserRole> roles = userRoleRepository.findUserRoleByUserId(sessionService.getUser().getId());
        if (!roles.contains(UserRoleType.ADMIN) && !Objects.equals(sessionService.getUser().getId(), userId)){
            throw new RuntimeException();
        }

        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setQuote(quote);

        profileRepository.save(profile);

        User user = userRepository.findById(userId).get();
        user.setProfile(profile);

        profile.setUser(user);

        userRepository.save(user);
        profileRepository.save(profile);
    }

    @Override
    public void update(Long profileId, String name, String surname, String quote) {
        if (sessionService.getUser() == null){
            throw new RuntimeException();
        }

        Profile profile = profileRepository.findById(profileId).get();

        List<UserRole> roles = userRoleRepository.findUserRoleByUserId(sessionService.getUser().getId());
        if (!roles.contains(UserRoleType.ADMIN) || sessionService.getUser().getId() != profile.getUser().getId()){
            throw new RuntimeException();
        }

        profile.setName(name);
        profile.setSurname(surname);
        profile.setQuote(quote);

        profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).get();
        return profile;
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        Profile profile = profileRepository.findProfileByUserId(userId);
        return profile;
    }

    @Override
    public Profile getProfileByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        Profile profile = profileRepository.findProfileByUserId(user.getId());
        return profile;
    }

    @Override
    public void remove(Long id) {
        if (sessionService.getUser() == null){
            throw new RuntimeException();
        }
        Profile byId = profileRepository.getById(id);
        List<UserRole> roles = userRoleRepository.findUserRoleByUserId(sessionService.getUser().getId());
        if (!roles.contains(UserRoleType.ADMIN) || sessionService.getUser().getId() != byId.getUser().getId()){
            throw new RuntimeException();
        }

        profileRepository.delete(byId);
    }
}
