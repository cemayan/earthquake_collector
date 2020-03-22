package com.cayan.pushservice.controller;


import com.cayan.pushservice.model.Token;
import com.cayan.pushservice.model.UserConfig;
import com.cayan.pushservice.service.KafkaConsumerService;
import com.cayan.pushservice.service.KafkaService;
import com.cayan.pushservice.service.TokenService;
import com.cayan.pushservice.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @Autowired
    UserConfigService userConfigService;

    @Autowired
    TokenService tokenService;



}
