package com.boxuegu.activity;

/**
 * Created by Tao on 2020/3/31.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.boxuegu.R;

import java.util.Timer;
import java.util.TimerTask;



public class SplashActivity extends AppCompatActivity {

    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //���ô˽���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        tv_version = (TextView) findViewById(R.id.tv_version);
        try {
            //��ȡ�������Ϣ
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("V" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }
        //����Timer�ô˽����ӳ�3�����ת,timer����һ���̣߳�����̲߳���ִ��task
        Timer timer = new Timer();
        //timertaskʵ��runnable�ӿ�,TimerTask���ʾһ����ָ��ʱ����ִ�е�task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 3000);//�������task���ӳ�3��֮���Զ�ִ��
    }
}