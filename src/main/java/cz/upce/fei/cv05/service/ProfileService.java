package cz.upce.fei.cv05.service;

import java.util.Date;

public interface ProfileService {
    void add(Long userId, String name, String surname, String quote, Date dateOfBirth);
    void update(Long profileId, String name, String surname, String quote);
    void getProfile(Long profileId);
    void getProfileByUserId(Long userId);
    void getProfileByUserName(String userName);
    void remove(Long id);
}
