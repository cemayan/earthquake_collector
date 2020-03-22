package com.cayan.pushservice.util;
import com.google.gson.Gson;


public class ConvertString {

    public static <T> Object convert(String json, Class<T> model) {
        Gson gson = new Gson();
        return gson.fromJson(json,model);
    }
}