package com.kerrystudio.BoundServiceSelfDev;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private int mProgress=0;
    private int mMaxValue=5000;
    private boolean mIsPause=false;
    private Handler mHandler;

    private Binder mBinder=new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler=new Handler();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    public void startPretendLongRunningTask(){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(mProgress>=mMaxValue||mIsPause){
                    mProgress+=100;
                    mHandler.postDelayed(this, 100);
                }
                else{
                    mHandler.removeCallbacks(this);
                }

            }
        };
    }
}
