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
 *  激励视频类广告直接加载模式介绍：
 *  0.在Application中调用    MagicSDK.init(this); 先初始化SDk
 *  1.初始化广告控件magicVideoView ,不建议重复初始化
 *  2.缓存广告    magicVideoView.loadAd();
 *  3.加载广告
 *           if (magicVideoView!=null){
 *                     magicVideoView.openNewVideoTask(Video2Activity.this,true);
 *           }
 *
 *  4.合适时机销毁广告控件magicVideoView，避免内存泄露或者其他问题
 *          if (magicVideoView != null) {
 *             magicVideoView.destory();
 *         }
 */
public class Video2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        findViewById(R.id.ButtonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MagicVideoView magicVideoView =new MagicVideoView(MagicApp.getApp(),
                        "91200183954567","","2ZjLbhEBCFAzBbihEtxLEq25mXKw","301616",
                        CommonUtils.getPesudoDeviceId(Video2Activity.this)+"1",new MagicVideoListener() {

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
                magicVideoView.openNewVideoTask(Video2Activity.this,true);
            }
        });
    }
}
