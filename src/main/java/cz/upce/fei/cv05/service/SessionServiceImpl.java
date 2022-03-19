package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class SessionServiceImpl implements SessionService {

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
