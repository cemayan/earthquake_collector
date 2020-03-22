package com.cayan.pushservice.service;

import com.cayan.pushservice.model.PushPayload;
import com.cayan.pushservice.model.Token;
import com.cayan.pushservice.model.UserConfig;
import com.cayan.pushservice.util.PushRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PushService {

    public void sendPush(List<PushPayload> pushPayloads,Integer chunkSize) {

        //TODO: Chunk size' a göre gönderilmeli.
        PushRequest.getResult(pushPayloads);
    }
}
