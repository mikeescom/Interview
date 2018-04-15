package com.zumepizza.interview;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class ZumeApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
