package com.cayan.interfaces;

import org.apache.flink.streaming.api.datastream.DataStream;

public interface IKafkaService {
    DataStream KafkaConsumer();
    void KafkaProducer(String msg, String topic);
}
