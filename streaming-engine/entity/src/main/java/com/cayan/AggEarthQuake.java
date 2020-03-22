package com.cayan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AggEarthQuake {


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEqCount() {
        return eqCount;
    }

    public void setEqCount(String eqCount) {
        this.eqCount = eqCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AggEarthQuake(String location, String eqCount, String time) {
        this.location = location;
        this.eqCount = eqCount;
        this.time = time;
    }



    private String location;
    private String eqCount;
    private String time;


}
