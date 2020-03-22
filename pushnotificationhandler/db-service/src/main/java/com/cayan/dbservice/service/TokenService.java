package com.cayan.dbservice.service;

import com.cayan.dbservice.model.Token;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.repository.TokenServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    @Autowired
    TokenServiceRepository tokenServiceRepository;

    public List<Token> getAll() {
        return  tokenServiceRepository.findAll();
    }
    public Token getToken(long id) {
        return  tokenServiceRepository.findById(id).get();
    }

    public Token getTokenByUserId(String userId) {
        return  tokenServiceRepository.getTokenByUserId(userId);
    }
}
