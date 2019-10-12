package com.tm.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.qs.magic.sdk.listener.MagicVideoListener;
import com.qs.magic.sdk.util.CommonUtils;
import com.qs.magic.sdk.view.MagicVideoView;

/**
 *  激励互动类广告直接加载模式介绍：
 *  1.初始化广告控件magicVideoView ,不建议重复初始化
 *  2.缓存广告    magicVideoView.loadAd();
 *  3.加载广告
 *           if (magicVideoView!=null){
 *                     magicVideoView.openNewVideoTask(Reward2Activity.this,true);
 *           }
 *
 *  4.合适时机销毁广告控件magicVideoView，避免内存泄露或者其他问题
 *          if (magicVideoView != null) {
 *             magicVideoView.destory();
 *         }
 */
public class Reward2Activity extends AppCompatActivity {

    private MagicVideoView magicVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward2);
        initView();
        findViewById(R.id.ButtonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (magicVideoView!=null){
                    magicVideoView.openNewVideoTask(Reward2Activity.this,true);
                }
            }
        });
    }

    private void initView() {
        if (magicVideoView == null){
            magicVideoView =new MagicVideoView(MagicApp.getApp(),
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
                    Log.d("onMagicRequest","onMagicAdShow");
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
                public void onMagicAdClose(String s) {
                    Log.d("onMagicRequest","onMagicAdClose"+s);
                }
            });
        }
    }
}
