package com.iot.smarthomemasterandroid.remote;

import android.app.Activity;

public interface MvpPresenter<V extends MvpView> {

    Activity activity();

    void attachView(V mvpView);

    void detachView();



}
