package com.tm.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.qs.magic.sdk.listener.MagicVideoListener;
import com.qs.magic.sdk.util.CommonUtils;
import com.qs.magic.sdk.view.MagicVideoView;

public class Reward2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward2);
        findViewById(R.id.ButtonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MagicVideoView magicVideoView =new MagicVideoView(MagicApp.getApp(),
                        "91200183954567","","2AaKHTwmu8zCzVTUJJ6o5TNkHMDb","307101",
                        CommonUtils.getPesudoDeviceId(Reward2Activity.this),new MagicVideoListener() {

                    @Override
                    public void onMagicRequestAd() {
                        Log.d("onMagicRequest","onMagicRequestRewardVideo");
                    }

                    @Override
                    public void onMagicAdSuccessed() {
                        Log.d("onMagicRequest","onMagicAdSuccessed");

                    }

                    @Override
                    public void onMagicAdShow() {

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

                    @Override
                    public void onMagicAdClose() {
                        Log.d("onMagicRequest","onMagicAdClose");
                    }

                });
                magicVideoView.openNewVideoTask(Reward2Activity.this,true);
            }
        });
    }
}
