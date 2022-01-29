package com.iot.smarthomemasterandroid.remote;

public  interface SmartHomeIView extends MvpView {

    void onSuccess(SensorSyncRespose response);
    void onError(Throwable e);

    void onSuccess(Object o);
}


