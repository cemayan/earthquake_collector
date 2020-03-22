package com.cayan.pushservice.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HttpRequestAll {


    public static <T> List<T> convertStringToObject(String json, Class<T> c) {
        Gson gson = new Gson();
        return gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, c).getType());
    }

    public static  <T>  List<T> getResult(Class<T> c, String serviceName, Optional<Integer> time) {
        try {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = null;

        if(time.isPresent()) {
            builder    = UriComponentsBuilder.fromHttpUrl("http://db-service:8078/" + serviceName + "/time/" + time.get() );
        }else {
            builder    = UriComponentsBuilder.fromHttpUrl("http://db-service:8078/" + serviceName + "/"  );
        }


            ResponseEntity<String> result = restTemplate.getForEntity(builder.build().toUri(),String.class);
            return convertStringToObject(result.getBody(), c);
        }
        catch (Exception err) {
            System.out.println(err);
            return null;
        }
    }

}
