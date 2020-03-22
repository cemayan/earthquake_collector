package com.cayan.pushservice.service;

import com.cayan.pushservice.model.PushPayload;
import com.cayan.pushservice.model.Token;
import com.cayan.pushservice.model.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JoinService {

    List<PushPayload> pushPayloadList = new ArrayList<>();

    @Autowired
    TokenService tokenService;

    public List<PushPayload> join(List<UserConfig> userConfigs, String msg) {

        pushPayloadList.clear();

        userConfigs.forEach(x-> {
            Token token = tokenService.getTokenByUserId(x.userId);
            pushPayloadList.add(new PushPayload( msg ,x.userId, token.token));
            System.out.println(token);
        });

        return  pushPayloadList;
    }

}
