package com.cayan.userconfig.controller;

import com.cayan.userconfig.interfaces.IKafkaService;
import com.cayan.userconfig.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    KafkaService kafkaService;

    @PostMapping
    public void sendKafka(@RequestBody String config) {
        kafkaService.sendMessage(config);
    }

}
