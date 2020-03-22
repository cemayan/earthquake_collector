package com.cayan.analyticsengine.schema;

import com.cayan.AggEarthQuake;
import com.cayan.EarthQuake;
import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import java.io.IOException;


public class AggEqSchema implements DeserializationSchema<AggEarthQuake>, SerializationSchema<AggEarthQuake> {

    @Override
    public AggEarthQuake deserialize(byte[] bytes) throws IOException {
        Gson gson = new Gson();
        return  gson.fromJson(new String(bytes), AggEarthQuake.class);
    }

    @Override
    public byte[] serialize(AggEarthQuake myEvent) {
        return myEvent.toString().getBytes();
    }

    @Override
    public TypeInformation<AggEarthQuake> getProducedType() {
        return TypeExtractor.getForClass(AggEarthQuake.class);
    }

    @Override
    public boolean isEndOfStream(AggEarthQuake myEvent) {
        return false;
    }
}