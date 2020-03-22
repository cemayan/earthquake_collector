package com.cayan.analyticsengine.controller;


import com.cayan.analyticsengine.service.FlinkService;
import com.cayan.analyticsengine.service.KafkaService;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {




    @GetMapping("/")
    public  void startStreamAnalytics() {

    }
}
