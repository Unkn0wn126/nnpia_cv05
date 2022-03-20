package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.User;
import cz.upce.fei.cv05.entity.UserRole;

import java.util.List;

public interface UserService {
    User add(String userName, String email, String password);
    void update(Long id, String email, String password);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String userName);
    void remove(Long id);
    void login(String userName, String password);
    void logout();
}
