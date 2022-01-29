package com.iot.smarthomemasterandroid.remote;

public interface SmartHomeIPresenter  <V extends SmartHomeIView> extends MvpPresenter<V> {
    void syncSensor(int currentId ,String currentType , String currentAction) ;
}

