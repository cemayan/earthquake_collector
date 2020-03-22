package com.cayan.pushservice.service;

import com.cayan.pushservice.model.Token;
import com.cayan.pushservice.model.UserConfig;
import com.cayan.pushservice.util.HttpRequest;
import com.cayan.pushservice.util.HttpRequestAll;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    public List<Token> getAllToken(Optional<Integer> time) {
        return HttpRequestAll.getResult(Token.class, "token", time);
    }
    public Token getToken(long id) {
        return HttpRequest.getResult(Token.class, "token", id );
    }

    public Token getTokenByUserId(String userId) {
        return HttpRequest.getResult(Token.class, "token/user", userId );
    }
}
