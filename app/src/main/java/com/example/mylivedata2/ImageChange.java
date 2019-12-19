package com.example.mylivedata2;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ImageChange extends AppCompatActivity {
    ImageView imgv;
    AnimationDrawable animation;
    AnimationDrawable sphereResume, sphereAnimation;
    //ImageViewModel imageViewModel;

    //for livedata
    CounterViewModel counterViewModel;
    Observer<Integer> dataObserver;
    TextView tvImage;
    Imageeeeeeeeeee imageeeeeeeeeee;
    LiveDataaaaaaaaaa l;
    boolean looping = false;
    Drawable currentFrame, checkFrame;
    List<Animator> mAnimatorList = new ArrayList<>();
    int lastposition;
    int pausedposition;
    int counter = 0;
    private ObjectAnimator anim;
    Runnable runnable;
    Handler handler;
    Thread thread;
    int delayTimeSec = 1000; //1 Sec

    Thread timer;
    int z;
    int t;
    int frameNumber;
    int imageArray[] = {R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_change);
        imgv = (ImageView)findViewById(R.id.imgv);
        //imgv.setImageResource(imageArray[0]);
        //imgv.setImageDrawable(getResources().getDrawable(R.drawable.blink));
        //image animation
        animation = new AnimationDrawable();
        /*animation.addFrame(getResources().getDrawable(R.drawable.chhouitte),5000);
        animation.addFrame(getResources().getDrawable(R.drawable.coursier),5000);
        animation.addFrame(getResources().getDrawable(R.drawable.jai_faim),5000);
        animation.addFrame(getResources().getDrawable(R.drawable.marche),5000);
        animation.addFrame(getResources().getDrawable(R.drawable.thankyou_graphics),5000);*/
        for(z=0;z<=4;z++)
        {
            animation.addFrame(getResources().getDrawable(imageArray[z]), 2000);
            t=z;
        }

        imgv.setBackgroundDrawable(animation);
        animation.start();


        /////livedata

        //observeImage();
        imageeeeeeeeeee = new Imageeeeeeeeeee();
        getLifecycle().addObserver(imageeeeeeeeeee);

        l = new LiveDataaaaaaaaaa();
        getLifecycle().addObserver(l);
        //or
        tvImage = (TextView)findViewById(R.id.tvImage);
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        dataObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                lastposition = integer;
                int n = getCurFrameFromAnimationDrawable(animation);
                //tvImage.setText("Position at = "+integer+ ", Counter = "+frameNumber);
                tvImage.setText("Name = "+n);
                //Log.i("My Position ","at = "+integer+ ", name = "+frameNumber);
                //integer = pausedposition;
            }
        };
        try
        {
            //dataObserver.wait();
        }
        catch (Exception e)
        {
            Log.i("My Error ",e+"");
        }

        counterViewModel.getCounter().observe(this, dataObserver);




    }
    private int getCurFrameFromAnimationDrawable(AnimationDrawable drawable){
        try {
            Field mCurFrame = drawable.getClass().getDeclaredField("mCurFrame");
            mCurFrame.setAccessible(true);
            return  mCurFrame.getInt(drawable);
        }catch (Exception ex){
            Log.e("My get = ", "getCurFrameFromAnimationDrawable: Exception");
            return 0;
        }
    }

    private void setCurFrameToAnimationDrawable(AnimationDrawable drawable, int curFrame){
        try {
            Field mCurFrame = drawable.getClass().getDeclaredField("mCurFrame");
            mCurFrame.setAccessible(true);
            mCurFrame.setInt(drawable, curFrame);
            Class[] param = new Class[]{Runnable.class, long.class};
            Method method = Drawable.class.getDeclaredMethod("scheduleSelf", param);
            method.setAccessible(true);
            method.invoke(drawable, drawable, 2000);
        }catch (Exception ex){
            Log.e("My set = ", "setCurFrameToAnimationDrawable: Exception");
        }
    }
    @Override
    protected void onStart() {
        Log.i("Myy start"," start");
        //setCurFrameToAnimationDrawable(animation, (frameNumber));
        super.onStart();

        try
        {
        }
        catch (Exception e)
        {
            Log.i("My Error ",e+"");
        }

    }

    @Override
    protected void onPause() {
        Log.i("Myy pause"," pause");

        try {
            pausedposition = lastposition;
            currentFrame = animation.getCurrent();
            for (int i1 = 0; i1 < animation.getNumberOfFrames(); i1++) {
                checkFrame = animation.getFrame(i1);
                if (checkFrame == currentFrame) {
                    frameNumber = i1;
                    break;
                }
            }
            Log.i("My pause frameNumber = ",frameNumber+"");
            //setCurFrameToAnimationDrawable(animation, frameNumber);

            getCurFrameFromAnimationDrawable(animation);
            super.onPause();

        }
        catch (Exception e)
        {
            Log.i("My Error ",e+"");
        }

    }

    @Override
    protected void onResume() {
        Log.i("Myy resume"," resume");
        //animation.start();
            try
            {
                //dataObserver.notify();
                //runnable.notify();
                //this.thread.run();
                //this.timer.run();
                //Log.i("My image = ",animation.getFrame(t)+"");


                Log.i("My animation = ",animation.getCurrent()+"");
                Log.i("My resume frameNumber=",frameNumber+"");
                Log.i("My image = ",imgv.getContentDescription()+"");
                Log.i("My t = ",t+"");
                //getCurFrameFromAnimationDrawable(animation);
                setCurFrameToAnimationDrawable(animation, (frameNumber-1));
                super.onResume();

            }
            catch (Exception e)
            {
                Log.i("My Error ",e+"");
            }



    }

    @Override
    protected void onStop() {
        Log.i("Myy stop"," stop");
        //getCurFrameFromAnimationDrawable(animation);
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Myy restart"," restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //this.thread.interrupt();
        Log.i("Myy destroy"," destroy");
    }
}
