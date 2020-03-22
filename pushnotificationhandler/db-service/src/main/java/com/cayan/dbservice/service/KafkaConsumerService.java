package com.cayan.dbservice.service;

import com.cayan.dbservice.model.Push;
import com.cayan.dbservice.model.Token;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.repository.PushServiceRepository;
import com.cayan.dbservice.repository.TokenServiceRepository;
import com.cayan.dbservice.repository.UserConfigRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    UserConfigRepository userConfigRepository;

    @Autowired
    PushServiceRepository pushServiceRepository;

    @Autowired
    TokenServiceRepository tokenServiceRepository;

    Gson gson = new Gson();

    @KafkaListener(topics = "${kafka.user_config_topic}")
    public void listenUserConfig(@Payload String msg) {

        try {

            UserConfig userConfig = gson.fromJson(msg,UserConfig.class);

            //For save
            if(!userConfigRepository.existsByUserIdAndLocation(userConfig.getUserId(), userConfig.getLocation()) ) {
                userConfigRepository.save(userConfig);
            }

            //For update
            if(userConfigRepository.existsById(userConfig.getId())) {

                UserConfig existUserConfig = userConfigRepository.findById(userConfig.getId()).get();
                existUserConfig.setJson(userConfig.getJson());
                existUserConfig.setLocation(userConfig.getLocation());
                existUserConfig.setTimeInterval(userConfig.getTimeInterval());
                userConfigRepository.save(existUserConfig);
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(msg);
    }

    @KafkaListener(topics = "${kafka.push_service_topic}")
    public void listenPushService(@Payload String msg) {

        try {
            Push push = gson.fromJson(msg, Push.class);
            pushServiceRepository.save(push);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(msg);

    }


    @KafkaListener(topics = "${kafka.token_service_topic}")
    public void listenTokenService(@Payload String msg) {

        try {
            Token token = gson.fromJson(msg,Token.class);
            if(!tokenServiceRepository.existsByUserId(token.getUserId())) {
                tokenServiceRepository.save(token);
            }
            else {
                Token existToken= tokenServiceRepository.getTokenByUserId(token.getUserId());
                existToken.setJson(token.getJson());
                existToken.setToken(token.getToken());
                tokenServiceRepository.save(existToken);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(msg);
    }
}
