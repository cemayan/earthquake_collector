package com.cayan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EarthQuake {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getDepth() {
        return Depth;
    }

    public void setDepth(String depth) {
        Depth = depth;
    }

    public String findLocation() {

        Pattern p = Pattern.compile("\\((.*?)\\)",Pattern.DOTALL);
        Matcher matcher = p.matcher(this.lokasyon);
        if (matcher.find()){
            return matcher.group(0).replaceAll("[()]", "");
        }
        else {
            return this.lokasyon.trim();
        }

    }

    private String name;
    private String lokasyon;
    private String lat;
    private String lng;
    private String mag;
    private String Depth;
}
