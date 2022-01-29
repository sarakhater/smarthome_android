package com.iot.smarthomemasterandroid.remote;

import com.iot.smarthomemasterandroid.ui.home.ElectricData;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("Sensors/Sync")
    Observable<Object> syncSensor(@Body ElectricData data) ;
}
