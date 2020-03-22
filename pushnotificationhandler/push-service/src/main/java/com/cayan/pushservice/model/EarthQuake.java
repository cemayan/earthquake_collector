package com.cayan.pushservice.model;

public class EarthQuake {

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEqCount() {
        return eqCount;
    }

    public void setEqCount(String eqCount) {
        this.eqCount = eqCount;
    }

    public String location;
    public String time;
    public String eqCount;

}
