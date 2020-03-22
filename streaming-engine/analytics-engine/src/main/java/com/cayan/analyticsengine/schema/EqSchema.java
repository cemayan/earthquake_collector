package com.cayan.analyticsengine.schema;

import com.cayan.EarthQuake;
import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import java.io.IOException;


public class EqSchema implements DeserializationSchema<EarthQuake>, SerializationSchema<EarthQuake> {

    @Override
    public EarthQuake deserialize(byte[] bytes) throws IOException {
        Gson gson = new Gson();
        return  gson.fromJson(new String(bytes), EarthQuake.class);
    }

    @Override
    public byte[] serialize(EarthQuake myEvent) {
        return myEvent.toString().getBytes();
    }

    @Override
    public TypeInformation<EarthQuake> getProducedType() {
        return TypeExtractor.getForClass(EarthQuake.class);
    }

    @Override
    public boolean isEndOfStream(EarthQuake myEvent) {
        return false;
    }
}