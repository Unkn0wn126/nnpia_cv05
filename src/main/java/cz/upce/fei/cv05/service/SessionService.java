package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.User;

public interface SessionService {
    User getUser();
    void setUser(User user);
}
