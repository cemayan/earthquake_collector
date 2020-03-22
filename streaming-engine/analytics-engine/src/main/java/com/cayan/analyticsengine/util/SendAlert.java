package com.cayan.analyticsengine.util;

import com.cayan.AggEarthQuake;
import com.cayan.analyticsengine.service.KafkaService;
import com.google.gson.Gson;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class SendAlert {

    private static Gson gson = new Gson();

    public SendAlert() {

    }

    public  void sendMail(Tuple2<String, Long> data, String time) {
        System.out.println(data.getField(0) + " lokasyonunda " + time + " dakika içinde "   + data.getField(1) +  " kez deprem oldu" );

        try{
            AggEarthQuake aggEarthQuake = new AggEarthQuake(data.getField(0), data.getField(1).toString() , time);
            if(time.equals("1")) {
             String last1_eq =  gson.toJson(aggEarthQuake);
             //kafkaService.KafkaProducer(last1_eq,"last1_eq");
            }
            else if(time.equals("2")) {
                String last2_eq =  gson.toJson(aggEarthQuake);
                //kafkaService.KafkaProducer(last2_eq,"last2_eq");
            }
            else if(time.equals("2")) {
                String last3_eq =  gson.toJson(aggEarthQuake);
              // kafkaService.KafkaProducer(last3_eq,"last1_eq");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
