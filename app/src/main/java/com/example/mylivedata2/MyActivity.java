package com.example.mylivedata2;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.net.Network;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        tv1 = (TextView)findViewById(R.id.tv1);



        LiveData<Network> networkLiveData = new ConnectivityLiveData(this);
        networkLiveData.observe(this, new Observer<Network>() {
            @Override
            public void onChanged(@Nullable Network network) {
                tv1.setText("Network = "+network.describeContents());
                Toast.makeText(MyActivity.this, "Network = "+network.describeContents(), Toast.LENGTH_SHORT).show();
                Log.i("My Network = ",network.describeContents()+"");
            }
        });


        //Toast.makeText(MyActivity.this, "api called", Toast.LENGTH_SHORT).show();
        RetrofitRepository.getIntData().observe(this, new Observer<StoreInfo>() {
            @Override
            public void onChanged(@Nullable StoreInfo storeInfo) {
                Toast.makeText(MyActivity.this, "Info = "+storeInfo.getStore(), Toast.LENGTH_SHORT).show();
                tv1.setText("Info = " + storeInfo.getStore());
                //((TextView) MyActivity.this.findViewById(R.id.tv1)).setText("Info = " + storeInfo.getStore());
            }
        });

        /*LiveData<String> userName = Transformations.switchMap(data, new Function<Object, LiveData<String>>() {
            @Override
            public LiveData<String> apply(Object currentTime) {
                MutableLiveData<String> newVal = new MutableLiveData<String>();
                tv1.setText("current time in m seconds " + currentTime);
                return newVal;
            }
        });*/
    }
    public void getTime(View view)
    {
        RetrofitRepository.getStoreInfo();
    }
}
