package com.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.R;
import com.boxuegu.utils.MD5Utils;


public class RegisterActivity extends AppCompatActivity {
    //����
    private TextView tv_main_title;
    //���ذ�ť
    private TextView tv_back;
    //ע�ᰴť
    private Button btn_register;
    //�˺š����롢�ٴ���������Ŀؼ�
    private EditText et_user_name,et_psw,et_psw_again;
    //�˺š����롢�ٴ���������Ŀؼ��Ļ�ȡֵ
    private String userName,psw,pswAgain;
    //���Ⲽ��
    private RelativeLayout rl_rirle_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("ע��");
        tv_back = (TextView)findViewById(R.id.tv_back);
        rl_rirle_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_rirle_bar.setBackgroundColor(Color.TRANSPARENT);
        btn_register = (Button)findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw =(EditText) findViewById(R.id.et_psw);
        et_psw_again = (EditText)findViewById(R.id.et_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"�������û���",Toast.LENGTH_LONG).show();
                    return;
                }else  if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"����������",Toast.LENGTH_LONG).show();
                    return;
                }else  if (TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"���ٴ���������",Toast.LENGTH_LONG).show();
                    return;
                }else if (!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"���ε����벻һ��",Toast.LENGTH_LONG).show();
                    return;
                }else if (isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this,"���˻����Ѿ�����",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Toast.makeText(RegisterActivity.this,"ע��ɹ�",Toast.LENGTH_LONG).show();
                    saveRegisterInfo(userName,psw);
                    Intent data = new Intent();
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void getEditString() {
        userName = et_user_name.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
    }

    private boolean isExistUserName(String userName) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPwd = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPwd)){
            has_userName = true;
        }
        return has_userName;
    }

    private void saveRegisterInfo(String userName, String psw) {
        String md5Psw = MD5Utils.md5(psw);
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.apply();
    }
}
