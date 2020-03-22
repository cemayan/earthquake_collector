package com.cayan.analyticsengine;

import com.cayan.analyticsengine.service.FlinkService;
import com.cayan.analyticsengine.service.KafkaService;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyticsEngineApplication implements CommandLineRunner {

	@Autowired
	private KafkaService kafkaService;

	@Autowired
	private FlinkService flinkService;

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DataStream dataStream =  kafkaService.KafkaConsumer();
		DataStream aggDataStream =   flinkService.aggFilter(dataStream);
		flinkService.sinkKafka(aggDataStream);

		try {
			flinkService.getExcEnv().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
