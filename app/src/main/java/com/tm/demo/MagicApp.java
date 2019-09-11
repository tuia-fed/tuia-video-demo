package com.tm.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.qs.magic.sdk.MagicSDK;

public class MagicApp extends Application {

    private static MagicApp myApp;

    public static MagicApp getApp() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        MagicSDK.init(this);
        Stetho.initializeWithDefaults(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            MultiDex.install(this);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
