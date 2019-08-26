package com.tm.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.qs.magic.sdk.listener.MagicVideoListener;
import com.qs.magic.sdk.view.MagicVideoView;

/**
 * @author hasee
 */
public class MainActivity extends AppCompatActivity {

    private MagicVideoView magicVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                magicVideoView =new MagicVideoView(getApplication(),
                        "","","2ZjLbhEBCFAzBbihEtxLEq25mXKw","300789");
                magicVideoView.init(new MagicVideoListener() {

                    @Override
                    public void onMagicRequestAd() {
                        Log.d("onMagicRequest","onMagicRequestAd");
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
                        Log.d("onMagicRequest","onMagicRewarded"+msg);
                    }
                });
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
