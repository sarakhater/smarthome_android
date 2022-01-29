package com.iot.smarthomemasterandroid.remote;

import android.app.Activity;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final CompositeDisposable mCompositeDisposable;
    private V mMvpView;

    public BasePresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public Activity activity() {
        return getMvpView().activity();
    }


    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }


    public V getMvpView() {
        return mMvpView;
    }


    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


}
