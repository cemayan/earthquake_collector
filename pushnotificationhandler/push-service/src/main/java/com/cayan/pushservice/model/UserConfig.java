package com.cayan.pushservice.model;

public class UserConfig {

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



    public long id;
    public String json;
    public String userId;
    public Integer timeInterval;
    public String location;
}
