package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.User;
import cz.upce.fei.cv05.entity.UserRole;
import cz.upce.fei.cv05.entity.UserRoleType;
import cz.upce.fei.cv05.repository.UserRepository;
import cz.upce.fei.cv05.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class UserServiceImpl implements UserService {

    private final SessionService sessionService;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(SessionService sessionService, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void add(String userName, String email, String password) {
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);
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
