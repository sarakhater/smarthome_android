package com.iot.smarthomemasterandroid.remote;

import android.app.Activity;

public interface MvpView {
    Activity activity();

    void showLoading();

    void hideLoading();
    void onError(Throwable throwable);
}
