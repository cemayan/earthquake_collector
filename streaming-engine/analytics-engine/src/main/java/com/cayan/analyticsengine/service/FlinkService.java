package com.cayan.analyticsengine.service;

import com.cayan.AggEarthQuake;
import com.cayan.EarthQuake;
import com.cayan.interfaces.IFlinkService;
import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FlinkService implements IFlinkService {

    @Autowired
    private  KafkaService kafkaService;

    private static StreamExecutionEnvironment env = null ;
    @Value("${flink.timeWindow}")
    private String timeWindow;


    @Value("${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${kafka.bootstrapServersTest}")
    private String bootstrapServersTest;

    @Override
    public  StreamExecutionEnvironment getExcEnv()  {

        if( env == null) {
            try {
                env = StreamExecutionEnvironment.getExecutionEnvironment();
                env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  env;
    }


    @Override
    public DataStream aggFilter(DataStream dataStream) {

        String tt = timeWindow;

        DataStream lastStream = dataStream
                .keyBy((KeySelector<EarthQuake, String>)o -> { return o.findLocation();})
                .timeWindow(Time.minutes(Long.valueOf(timeWindow)))
                .apply(new WindowFunction<EarthQuake, Tuple2<String, Long>, String, TimeWindow>() {

                    @Override
                    public void apply(String lokasyon, TimeWindow timeWindow, Iterable<EarthQuake> iterable, Collector<Tuple2<String, Long>> collector) throws Exception {
                        long count = 0;

                        for (EarthQuake ignored : iterable) {
                            count++;
                        }
                        collector.collect(new Tuple2<String, Long>(lokasyon, count));
                    }
                })
                .process(new ProcessFunction<Tuple2<String, Long>, String>() {
                    @Override
                    public void processElement(Tuple2<String, Long> stringLongTuple2, Context context, Collector<String> collector) throws Exception {
                        Gson gson = new Gson();
                        String strOut = gson.toJson(new AggEarthQuake(stringLongTuple2.getField(0), stringLongTuple2.getField(1).toString(), tt));
                        collector.collect(strOut);
                    }
                });

        return lastStream;
    }

    @Override
    public void sinkKafka(DataStream dataStream) {
        try {
            dataStream.addSink(new FlinkKafkaProducer<>(bootstrapServers,"analyzedTopic", new SimpleStringSchema()));
            System.out.println("A record was successfully write!");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
