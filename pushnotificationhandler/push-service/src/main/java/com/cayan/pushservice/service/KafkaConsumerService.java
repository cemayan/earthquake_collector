package com.cayan.pushservice.service;

import com.cayan.pushservice.model.EarthQuake;
import com.cayan.pushservice.model.PushPayload;
import com.cayan.pushservice.model.Token;
import com.cayan.pushservice.model.UserConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class KafkaConsumerService {

   @Autowired
   UserConfigService userConfigService;

   @Autowired
   JoinService joinService;

   @Autowired
   PushService pushService;

   Gson gson = new Gson();

    @Value("${push.chunkSize}")
    private Integer chunkSize;

    @KafkaListener(topics = "${kafka.topic}")
    public void listenEarthQuakes(@Payload String msg) {

        try {
            EarthQuake earthQuake = gson.fromJson(msg, EarthQuake.class);
            UserConfig userConfig = new UserConfig();
            userConfig.setLocation(earthQuake.getLocation());
            userConfig.setTimeInterval(Integer.parseInt(earthQuake.getTime()));

            List<UserConfig> userConfigs = userConfigService.getAllUserConfigLocation(userConfig);

            List<PushPayload>  pushPayloads =  joinService.join(userConfigs, msg);
            pushService.sendPush(pushPayloads, chunkSize);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
