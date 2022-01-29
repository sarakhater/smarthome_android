package com.iot.smarthomemasterandroid.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElectricData {
    @SerializedName("readingTime")
    @Expose
    String readingTime;
    @SerializedName("id")
    @Expose
    String deviceId;
    @SerializedName("reading")
    @Expose
    String reading;
    @SerializedName("type")
    @Expose
    String deviceName;
    @SerializedName("consumption")
    @Expose
    String consumption;
    @SerializedName("cost")
    @Expose
    String cost;

    public ElectricData(String deviceId, String deviceName , String reading ) {
        this.deviceId = deviceId;
        this.reading = reading;
        this.deviceName = deviceName;
    }

    public ElectricData(String readingTime, String deviceId, String reading, String deviceName, String consumption, String cost) {
        this.readingTime = readingTime;
        this.deviceId = deviceId;
        this.reading = reading;
        this.deviceName = deviceName;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String photoURL) {
        this.deviceName = photoURL;
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
