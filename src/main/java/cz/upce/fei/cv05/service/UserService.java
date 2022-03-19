package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.User;

public interface UserService {
    void add(String userName, String email, String password);
    void update(Long id, String email, String password);
    User getUserById(Long id);
    User getUserByName(String userName);
    void remove(Long id);
    void login(String userName, String password);
    void logout();
}
