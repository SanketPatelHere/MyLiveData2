package com.example.mylivedata2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepository {
    public static final String BASE_URL = "http://www.zoftino.com/api/";
    private static MutableLiveData<StoreInfo> data = new MutableLiveData<>();
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofitClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }
    public static LiveData<StoreInfo> getIntData()
    {
        return data;
    }
    public static void getStoreInfo()
    {
        Log.i("My before retrofit call",Thread.currentThread().getName());
        Call<StoreInfo> call = getRetrofitClient().create(StoreApi.class).getStoreInfo();
        call.enqueue(new Callback<StoreInfo>() {
            @Override
            public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                StoreInfo si = response.body();
                data.postValue(si);
                Log.i("My after retrofit call",Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<StoreInfo> call, Throwable t) {
                Log.i("My Error ","Retrofit failure");
            }
        });
    }
}
