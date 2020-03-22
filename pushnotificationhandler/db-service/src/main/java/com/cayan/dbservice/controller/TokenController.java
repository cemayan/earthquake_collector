package com.cayan.dbservice.controller;

import com.cayan.dbservice.model.Push;
import com.cayan.dbservice.model.Token;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.service.PushService;
import com.cayan.dbservice.service.TokenService;
import com.cayan.dbservice.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/")
    public List<Token> getAllToken() {
        return tokenService.getAll();
    }

    @GetMapping("/{id}")
    public Token getToken(@PathVariable("id") long id) {
        return tokenService.getToken(id);
    }

    @GetMapping("/user/{id}")
    public Token getTokenByUserId(@PathVariable("id") String id) {
        return tokenService.getTokenByUserId(id);
    }

}
