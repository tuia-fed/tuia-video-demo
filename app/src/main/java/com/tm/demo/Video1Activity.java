package com.tm.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.qs.magic.sdk.listener.MagicVideoListener;
import com.qs.magic.sdk.util.CommonUtils;
import com.qs.magic.sdk.view.MagicVideoView;

public class Video1Activity extends AppCompatActivity {

    private MagicVideoView magicVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);
        magicVideoView =new MagicVideoView(MagicApp.getApp(),
                "91200183954567","","2ZjLbhEBCFAzBbihEtxLEq25mXKw","301616",
                CommonUtils.getPesudoDeviceId(Video1Activity.this),new MagicVideoListener() {

            @Override
            public void onMagicRequestAd() {
                Log.d("onMagicRequest","onMagicRequestRewardVideo");
            }

            @Override
            public void onMagicAdSuccessed() {
                Log.d("onMagicRequest","onMagicAdSuccessed");

            }

            @Override
            public void onMagicAdEmpty() {
                Log.d("onMagicRequest","onMagicAdEmpty");
            }

            @Override
            public void onMagicAdFailed(Response<String> response) {
                Log.d("onMagicRequest","onMagicAdFailed"+response.body());
            }

            @Override
            public void onMagicRewarded(String msg) {
                Log.d("onMagicRequest","onMagicReward"+msg);
            }

        });

        findViewById(R.id.ButtonRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                magicVideoView.loadAd();
            }
        });

        findViewById(R.id.ButtonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (magicVideoView.checkLocalData()){
                    magicVideoView.openNewVideoTask(Video1Activity.this,false);
                }else {
                    magicVideoView.openNewVideoTask(Video1Activity.this,true);
                }

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (magicVideoView!=null){
            magicVideoView.destory();
        }
    }
}
