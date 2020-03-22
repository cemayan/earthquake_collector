package com.cayan.interfaces;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public interface IFlinkService {

    StreamExecutionEnvironment getExcEnv();
    DataStream aggFilter(DataStream dataStream);
    void sinkKafka(DataStream dataStream);

}
