package com.cayan.tokenservice.controller;

import com.cayan.tokenservice.service.KafkaService;
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
