package com.iot.smarthomemasterandroid.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AuthorizationSharedHelper {

        public static String getAuthorizationHash(Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("AuthorizationHash","");
        }

        public static void setAuthorizationHash(Context context, String authorizationHash) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putString("AuthorizationHash", authorizationHash ).commit();
        }
    }

