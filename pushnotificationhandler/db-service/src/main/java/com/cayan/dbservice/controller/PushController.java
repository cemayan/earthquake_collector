package com.cayan.dbservice.controller;

import com.cayan.dbservice.model.Push;
import com.cayan.dbservice.model.Token;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.service.KafkaConsumerService;
import com.cayan.dbservice.service.PushService;
import com.cayan.dbservice.service.TokenService;
import com.cayan.dbservice.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/push")
public class PushController {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @Autowired
    PushService pushService;

    @GetMapping("/")
    public List<Push> getAllPush() {
        return pushService.getAll();
    }

}
