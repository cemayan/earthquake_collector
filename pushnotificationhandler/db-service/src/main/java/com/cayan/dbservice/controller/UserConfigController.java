package com.cayan.dbservice.controller;

import com.cayan.dbservice.model.Token;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.service.TokenService;
import com.cayan.dbservice.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-config")
public class UserConfigController {

    @Autowired
    UserConfigService userConfigService;

    @GetMapping("/")
    public List<UserConfig> getAllUserConfig() {
        return userConfigService.getAll();
    }

    @GetMapping("/{id}")
    public UserConfig getUserConfig(@PathVariable("id") long id) {
        return userConfigService.getUserConfig(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserConfig(@PathVariable("id") String id) {
        try {
            userConfigService.deleteUserConfig(Long.valueOf(id));
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<UserConfig> getUserConfigByUserId(@PathVariable("userId") String userId) {
        return userConfigService.getUserConfigByUserId(userId);
    }

    @GetMapping("/time/{time}")
    public List<UserConfig> getUserConfigbyTime(@PathVariable("time") Integer time) {
        return userConfigService.getUserConfigbyTime(time);
    }

    @PostMapping
    public List<UserConfig> getUserConfigbyTime(@RequestBody UserConfig userConfig) {
        return userConfigService.getUserConfigbyLocationTime(userConfig);
    }

}
