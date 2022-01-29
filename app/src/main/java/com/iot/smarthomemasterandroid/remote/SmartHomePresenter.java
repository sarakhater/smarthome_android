package com.iot.smarthomemasterandroid.remote;

import com.iot.smarthomemasterandroid.ui.home.ElectricData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public  class SmartHomePresenter <V extends SmartHomeIView> extends BasePresenter<V> implements SmartHomeIPresenter<V>{

    @Override
    public void syncSensor(int currentId ,String currentType , String currentAction) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .syncSensor(new ElectricData(String.valueOf(currentId),currentType,currentAction))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

}

