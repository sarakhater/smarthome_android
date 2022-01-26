package com.iot.smarthomemasterandroid.ui.home;

public class ElectricData {
    String readingTime;
    String deviceId;
    String reading;
    String photoURL;
    String consumption;
    String cost;

    public ElectricData(String readingTime, String deviceId, String reading, String photoURL, String consumption, String cost) {
        this.readingTime = readingTime;
        this.deviceId = deviceId;
        this.reading = reading;
        this.photoURL = photoURL;
        this.consumption = consumption;
        this.cost = cost;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
