package com.example.mylivedata2;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class Imageeeeeeeeeee implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause1()
    {
        Log.i("My Observer ","pause");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume1(){
        Log.i("My Observer ","resume");
    }
}
