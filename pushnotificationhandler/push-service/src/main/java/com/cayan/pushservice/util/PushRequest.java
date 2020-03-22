package com.cayan.pushservice.util;

import com.cayan.pushservice.model.PushPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class PushRequest {


    public static String getResult(List<PushPayload> pushPayload) {

        try {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://expo-server:3000/sendPush");
        HttpEntity<List<PushPayload>> entity = new HttpEntity<>(pushPayload, headers);



            ResponseEntity<String> result = restTemplate.postForEntity(builder.build().toUri(),entity,String.class);
            return result.getBody();
        }
        catch (Exception err) {
            System.out.println(err);
            return null;
        }
    }

}
