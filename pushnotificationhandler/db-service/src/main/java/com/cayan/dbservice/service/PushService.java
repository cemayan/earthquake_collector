package com.cayan.dbservice.service;

import com.cayan.dbservice.model.Push;
import com.cayan.dbservice.model.UserConfig;
import com.cayan.dbservice.repository.PushServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushService {

    @Autowired
    PushServiceRepository pushServiceRepository;

    public List<Push> getAll() {
        return  pushServiceRepository.findAll();
    }
}
