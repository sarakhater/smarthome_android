package com.iot.smarthomemasterandroid.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SensorSyncRespose {

    @SerializedName("data")
    @Expose
    private boolean data;

    @SerializedName("message")
    @Expose
    private String message;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

