package com.example.mylivedata2;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class CounterViewModel extends ViewModel {
    //for livedata
    private MutableLiveData<Integer> counter = new MutableLiveData<>();
    private static int ONE_SECOND=1000;
    private static int InitCount=0;

    public CounterViewModel()
    {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter.postValue(InitCount++);
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    public MutableLiveData<Integer> getCounter()
    {
        return counter;
    }
}
