package com.futuristic.careweb.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setInfo(String key, String value){
        prefs.edit().putString(key, value).commit();

    }


    public String getInfo(String key) {
        String info = prefs.getString(key,"");
        return info;
    }


    public void deleteInfo(String key) {
        prefs.edit().remove(key);
    }
}