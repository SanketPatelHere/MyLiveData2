package com.example.mylivedata2;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Slider extends AppCompatActivity {
    ViewPager viewPage;
    LinearLayout sliderDotspanel;
    private int dotsCount;
    private ImageView dots[]; //for dynamic add imageview = for dot
    Timer timer;
    int currentFrame;
    boolean timerStartFlag = true;
    Imageeeeeeeeeee imageeeeeeeeeee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        viewPage = (ViewPager)findViewById(R.id.viewPage);
        sliderDotspanel = (LinearLayout)findViewById(R.id.sliderDots);
        final ImageAdapter adapterView = new ImageAdapter(this);
        viewPage.setAdapter(adapterView);

        dotsCount = adapterView.getCount();  //5 images
        dots = new ImageView[dotsCount]; //5 dots
        for(int i=0;i<dotsCount;i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        //observeImage();
        imageeeeeeeeeee = new Imageeeeeeeeeee();
        getLifecycle().addObserver(imageeeeeeeeeee);

        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("Myy onPageScrolled = ","position = "+position+", positionOffset = "+positionOffset+", positionOffsetPixels = "+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                for(int i2=0;i2<dotsCount;i2++)
                {
                    dots[i2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("Myy onPageScrollState=","state = "+state);
            }
        });
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        viewPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText(Slider.this, "touched", Toast.LENGTH_SHORT).show();
                //return false;

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("My touched = ","down");
                        if (timer != null) {
                            timerStartFlag = false;
                        }
                        return false;
                    case MotionEvent.ACTION_UP:
                        Log.i("My touched = ","up");
                        timerStartFlag = true;
                        return false;
                }
                return false;
            }
        });

        if(savedInstanceState!=null)
        {
            //int n2 = Integer.parseInt(savedInstanceState.getString("current")+"");
            String n2 = savedInstanceState.getString("current");
            Log.i("My savedInstanceState=","current = "+n2);
        }
    }

    private int getCurFrameFromAnimationDrawable(ViewPager drawable){
        try {
            return viewPage.getCurrentItem();
        }catch (Exception ex){
            Log.e("My get = ", "getCurFrameFromAnimationDrawable: Exception");
            return 0;
        }
    }

    private void setCurFrameToAnimationDrawable(ViewPager drawable, int curFrame){
        try {
            viewPage.setCurrentItem(curFrame);
        }catch (Exception ex){
            Log.e("My set = ", "setCurFrameToAnimationDrawable: Exception");
        }
    }

    @Override
    protected void onPause() {
        Log.i("Myy pause"," pause");

        try {
            currentFrame = viewPage.getCurrentItem();

            Log.i("My pause frameNumber = ",currentFrame+"");
            getCurFrameFromAnimationDrawable(viewPage);
            super.onPause();
        }
        catch (Exception e)
        {
            Log.i("My Error ",e+"");
        }
    }

    @Override
    protected void onResume() {
        Log.i("My resume frameNumber=",currentFrame+"");
        setCurFrameToAnimationDrawable(viewPage, (currentFrame));

        super.onResume();
    }

    public class SliderTimer extends TimerTask
    {

        @Override
        public void run() {
            Slider.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(timerStartFlag == true) {

                        if (viewPage.getCurrentItem() < 4) {
                            Log.i("My viewPage.getCurrentI", (viewPage.getCurrentItem()) + "");
                            viewPage.setCurrentItem(viewPage.getCurrentItem() + 1);
                        } else {
                            viewPage.setCurrentItem(0);
                        }
                    }
                }
            });
        }
    }

    //for rotation of activity

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("My onSaveInstanceState=","outstate = "+outState+", outPersistentState"+outPersistentState);
        outState.putString("current",currentFrame+"");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i("My onRestoreInstanceSt=","savedInstanceState = "+savedInstanceState+", persistentState"+persistentState);

    }
}
