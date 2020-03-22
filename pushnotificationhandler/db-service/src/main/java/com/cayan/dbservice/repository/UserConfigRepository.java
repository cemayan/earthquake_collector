package com.cayan.dbservice.repository;

import com.cayan.dbservice.model.UserConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserConfigRepository extends JpaRepository<UserConfig, Long> {

    List<UserConfig> findByTimeInterval(Integer time);
    List<UserConfig> findAllByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsById(Long id);
    boolean existsByUserIdAndLocation(String userId ,String location);

    UserConfig getByUserId(String userId);
    List<UserConfig> findByLocationAndTimeInterval(String location ,Integer time);

}
