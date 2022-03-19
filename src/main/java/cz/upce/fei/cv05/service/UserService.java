package cz.upce.fei.cv05.service;

public interface UserService {
    void add(String userName, String email, String password);
    void update(Long id, String email, String password);
    void getUser(Long id);
    void getUser(String userName);
    void remove(Long id);
}
