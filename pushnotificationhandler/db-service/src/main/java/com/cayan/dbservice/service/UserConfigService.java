package com.cayan.dbservice.service;

import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.repository.UserConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserConfigService {

    @Autowired
    UserConfigRepository userConfigRepository;

    public List<UserConfig> getAll() {
        return  userConfigRepository.findAll();
    }

    public UserConfig getUserConfig(long id) {
        return  userConfigRepository.findById(id).get();
    }

    public void deleteUserConfig(long id) throws Exception {
            userConfigRepository.deleteById(id);
    }

    public List<UserConfig> getUserConfigByUserId(String userId) {
        return  userConfigRepository.findAllByUserId(userId);
    }

    public List<UserConfig> getUserConfigbyTime(Integer time) {
        return  userConfigRepository.findByTimeInterval(time);
    }

    public List<UserConfig> getUserConfigbyLocationTime(UserConfig userConfig) {
        return  userConfigRepository.findByLocationAndTimeInterval( userConfig.getLocation() ,userConfig.getTimeInterval());
    }
}
