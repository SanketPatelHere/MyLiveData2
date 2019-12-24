package com.example.mylivedata2;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class ThreadDemo extends AppCompatActivity {
    private TextView tv1;
    private Button btnStart;
    String result1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);
        tv1 = (TextView) findViewById(R.id.tv1);
        btnStart = (Button) findViewById(R.id.btnStart);
        //tv1.setText("Threadingggggg ");
        //new MyAsyncTask(tv1).execute();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText("Threadingggggg ");
                new MyAsyncTask(tv1).execute();
            }
        });

        if (savedInstanceState != null)
        {
            Log.i("My saved updated2", savedInstanceState.getString("updated"));
            Log.i("My saved new2", savedInstanceState.getString("new"));
            tv1.setText(savedInstanceState.getString("new"));
        }
    }

    /*
    //for rotation only use this
    <activity
    android:name=".Slider"
    android:configChanges="orientation|keyboardHidden" />*/
    //not working savedinstance state = not sol. of rotation
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("currenttext", tv1.getText().toString());
        outState.putString("new", result1);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        //if (savedInstanceState != null)
        {
            Log.i("My saved updated", savedInstanceState.getString("updated"));
            Log.i("My saved new", savedInstanceState.getString("new"));
            tv1.setText(savedInstanceState.getString("new"));
        }
    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }*/

    public class MyAsyncTask extends AsyncTask<Void, Void, String> {
        WeakReference<TextView> mTextView;

        public MyAsyncTask(TextView tv) {
            mTextView = new WeakReference<>(tv);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Random r = new Random();
            int n = r.nextInt(11);  //0 to 10 random
            int s = n * 200;
            try {
                Thread.sleep(s);
            } catch (Exception e) {
                Log.i("My Error = ", e + "");
            }
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            mTextView.get().setText(result);
            Bundle b = new Bundle();
            b.putString("updated",result);
            result1 = result;
            onSaveInstanceState(b);
        }
    }
}