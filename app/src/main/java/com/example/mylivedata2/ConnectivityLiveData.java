package com.example.mylivedata2;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class ConnectivityLiveData extends LiveData<Network> {
    private ConnectivityManager connectivityManager;
    public ConnectivityLiveData(Context context)
    {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    private NetworkCallback listener = new NetworkCallback() {
        @Override
        public void onAvailable(Network network){
            //this part runs on background thread so use postValue
            postValue(network);
        }
        @Override
        public void onLost(Network network){
            postValue(network);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActive() {
        //super.onActive();
        connectivityManager.registerDefaultNetworkCallback(listener);
    }

    @Override
    protected void onInactive() {
        //super.onInactive();
        connectivityManager.unregisterNetworkCallback(listener);
    }
}
