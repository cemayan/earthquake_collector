package com.cayan.analyticsengine.service;

import com.cayan.analyticsengine.schema.EqSchema;
import com.cayan.interfaces.IKafkaService;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class KafkaService implements IKafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private FlinkService flinkService;

    @Value("${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${kafka.bootstrapServersTest}")
    private String bootstrapServersTest;

    @Value("${kafka.groupId}")
    private String groupId;

    @Value("${kafka.topicId}")
    private String topicId;

    @Override
    public DataStream KafkaConsumer() {

        try {

            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", bootstrapServers);
            properties.setProperty("group.id", groupId);
            properties.setProperty("max.poll.records", "9999999");

            DataStream stream = flinkService.getExcEnv()
                    .addSource(new FlinkKafkaConsumer<>(topicId, new EqSchema(), properties));

            System.out.println("stream created");
            return  stream;
        }
        catch (Exception e ){
            System.out.println(e);
            return null;
        }
    }

    @PostConstruct
    public KafkaTemplate getKafkaTemplate() {
        return kafkaTemplate;
    }

    @Override
    public void KafkaProducer(String msg, String topic) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
