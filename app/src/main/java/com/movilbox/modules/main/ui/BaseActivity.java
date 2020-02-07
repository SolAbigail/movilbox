package com.movilbox.modules.main.ui;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseActivity extends Application {

    private static BaseActivity instance;
    public Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("movilbox")
                .schemaVersion(42)
                .modules(new SimpleRealmModule())
                .build();
        Realm.setDefaultConfiguration(config);

    }

    public static BaseActivity getInstance(){
        return instance;
    }
}
