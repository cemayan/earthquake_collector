package com.cayan.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "user_config")
public class UserConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String json;
    public String userId;
    public Integer timeInterval;
    public String location;

    public UserConfig() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "id=" + id +
                ", json='" + json + '\'' +
                '}';
    }

}
