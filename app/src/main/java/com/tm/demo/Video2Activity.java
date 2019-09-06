package com.tm.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;
import com.qs.magic.sdk.listener.MagicVideoListener;
import com.qs.magic.sdk.util.CommonUtils;
import com.qs.magic.sdk.view.MagicVideoView;

public class Video2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        findViewById(R.id.ButtonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MagicVideoView magicVideoView =new MagicVideoView(MagicApp.getApp(),
                        "91200183954567","","2AaKHTwmu8zCzVTUJJ6o5TNkHMDb","307101",
                        CommonUtils.getPesudoDeviceId(Video2Activity.this),new MagicVideoListener() {

                    @Override
                    public void onMagicRequestAd() {
                        Log.d("onMagicRequest","onMagicRequestRewardVideo");
                    }

                    @Override
                    public void onMagicAdSuccessed() {
                        Log.d("onMagicRequest","onMagicAdSuccessed");
                        ToastUtils.showShort("广告请求成功");

                    }

                    @Override
                    public void onMagicAdEmpty() {
                        Log.d("onMagicRequest","onMagicAdEmpty");
                        ToastUtils.showShort("暂时没有广告了");
                    }

                    @Override
                    public void onMagicAdFailed(Response<String> response) {
                        Log.d("onMagicRequest","onMagicAdFailed"+response.body());
                        ToastUtils.showShort("广告请求失败");
                    }

                    @Override
                    public void onMagicRewarded(String msg) {
                        Log.d("onMagicRequest","onMagicReward"+msg);
                        ToastUtils.showShort(msg);
                    }

                });
                magicVideoView.openNewVideoTask(Video2Activity.this,true);
            }
        });
    }
}
