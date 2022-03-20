package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.*;
import cz.upce.fei.cv05.repository.UserHasRoleRepository;
import cz.upce.fei.cv05.repository.UserRepository;
import cz.upce.fei.cv05.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.List;

@Service
@SessionScope
public class UserServiceImpl implements UserService {

    private final SessionService sessionService;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final UserHasRoleRepository userHasRoleRepository;

    public UserServiceImpl(SessionService sessionService, UserRepository userRepository, UserRoleRepository userRoleRepository, UserHasRoleRepository userHasRoleRepository) {
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userHasRoleRepository = userHasRoleRepository;
    }

    @Override
    public User add(String userName, String email, String password) {
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(password);
        UserRole userRole = userRoleRepository.findById(2L).get();
        UserHasRole userHasRole = new UserHasRole();
        userHasRole.setUser(user);
        userHasRole.setRole(userRole);
        user.setUserRoles(new HashSet<>());
        user.getUserRoles().add(userHasRole);
        userRole.setUserRoles(new HashSet<>());
        userRole.getUserRoles().add(userHasRole);

        userRepository.save(user);
        userRoleRepository.save(userRole);
        userHasRole.setId(new UserHasRoleId(user.getId(), userRole.getId()));
        userHasRoleRepository.save(userHasRole);

        return user;
    }

    @Override
    public void update(Long id, String email, String password) {
        if (sessionService.getUser() == null){
            throw new RuntimeException();
        }
        List<UserRole> roles = userRoleRepository.findUserRoleByUserId(sessionService.getUser().getId());
        if (!roles.contains(UserRoleType.ADMIN) || sessionService.getUser().getId() != id){
            throw new RuntimeException();
        }

        User user = userRepository.findById(id).get();
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void remove(Long id) {
        if (sessionService.getUser() == null){
            throw new RuntimeException();
        }
        List<UserRole> roles = userRoleRepository.findUserRoleByUserId(sessionService.getUser().getId());
        if (!roles.contains(UserRoleType.ADMIN) || sessionService.getUser().getId() != id){
            throw new RuntimeException();
        }

        User user = userRepository.findById(id).get();
        userRepository.delete(user);

    }

    @Override
    public void login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (!user.getPassword().equals(password)){
            throw new RuntimeException();
        }

        sessionService.setUser(user);
    }

    @Override
    public void logout() {
        sessionService.setUser(null);
    }
}
