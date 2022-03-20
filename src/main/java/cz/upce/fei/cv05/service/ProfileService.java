package cz.upce.fei.cv05.service;

import cz.upce.fei.cv05.entity.Profile;

import java.util.Date;

public interface ProfileService {
    void add(Long userId, String name, String surname, String quote, Date dateOfBirth, String pathToImage);
    void update(Long profileId, String name, String surname, String quote, Date dateOfBirth, String pathToImage);
    Profile getProfile(Long profileId);
    Profile getProfileByUserId(Long userId);
    Profile getProfileByUserName(String userName);
    void remove(Long id);
}
