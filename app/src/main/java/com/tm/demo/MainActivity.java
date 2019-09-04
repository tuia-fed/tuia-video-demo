package com.tm.demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        findViewById(R.id.buttonVideo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Video1Activity.class));
            }
        });

        findViewById(R.id.buttonVideo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Video2Activity.class));
            }
        });
    }

}
