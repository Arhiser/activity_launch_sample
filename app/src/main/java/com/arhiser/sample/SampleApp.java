package com.arhiser.sample;

import android.app.Application;

import com.arhiser.sample.model.Repository;

public class SampleApp extends Application {

    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        repository = new Repository();
    }

    public Repository getRepository() {
        return repository;
    }
}
