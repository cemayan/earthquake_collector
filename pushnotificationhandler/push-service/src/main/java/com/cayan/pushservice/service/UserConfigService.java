package com.cayan.pushservice.service;

import com.cayan.pushservice.model.UserConfig;
import com.cayan.pushservice.util.HttpRequest;
import com.cayan.pushservice.util.HttpRequestAll;
import com.cayan.pushservice.util.HttpRequestUserConfig;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserConfigService {

    public List<UserConfig> getAllUserConfig(Optional<Integer> time) {
        return HttpRequestAll.getResult(UserConfig.class, "user-config", time);
    }

    public List<UserConfig> getAllUserConfigLocation(UserConfig userConfig) {
        return HttpRequestUserConfig.getResult(UserConfig.class, "user-config", userConfig);
    }


    public UserConfig getUserConfig(long id) {
        return HttpRequest.getResult(UserConfig.class, "user-config", id );
    }
}
