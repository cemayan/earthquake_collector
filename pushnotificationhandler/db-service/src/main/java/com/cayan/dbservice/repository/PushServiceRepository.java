package com.cayan.dbservice.repository;

import com.cayan.dbservice.model.Push;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface PushServiceRepository extends JpaRepository<Push, Long> {

}
