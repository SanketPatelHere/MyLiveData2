package com.example.mylivedata2;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.util.Log;

public class LiveDataaaaaaaaaa extends LiveData implements LifecycleObserver {
    @Override
    protected void onActive() {
        super.onActive();
        Log.i("My Active = ","");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.i("My Inactive = ","");
    }
}
