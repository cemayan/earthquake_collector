package com.cayan.dbservice.repository;

import com.cayan.dbservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TokenServiceRepository extends JpaRepository<Token, Long> {

    Token getTokenByUserId(String userId);
    Boolean existsByUserId(String userId);
}
