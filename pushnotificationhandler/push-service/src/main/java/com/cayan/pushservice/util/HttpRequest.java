package com.cayan.pushservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class HttpRequest {

    public static <T>  T getResult(Class<T> c, String serviceName, Object id) {

        try {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://db-service:8078/" + serviceName + "/" + id.toString());


            ResponseEntity<String> result = restTemplate.getForEntity(builder.build().toUri(),String.class);
            return (T)ConvertString.convert(result.getBody(),c);
        }
        catch (Exception err) {
            System.out.println(err);
            return null;
        }
    }

}
