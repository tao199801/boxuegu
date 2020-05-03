package com.boxuegu.activity;

import android.content.pm.ActivityInfo;
import com.boxuegu.R;
import com.boxuegu.adapter.ExrecisesDetailAdapter;
import com.boxuegu.bean.ExercisesBean;
import com.boxuegu.utils.AnalysisUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ExercisesDetailActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private String title;
    private int id;
    private List<ExercisesBean> ebl;
    private ExrecisesDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //设置界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //获取从习题界面传递过来的章节ID
        id = getIntent().getIntExtra("id",0);
      //获取从习题界面传递过来的章节标题
        title = getIntent().getStringExtra("title");
        ebl = new ArrayList<ExercisesBean>();
        initData();
        init();
    }
    private void initData(){
        try {
        	//从XML文件中获取习题数据
            InputStream is = getResources().getAssets().open("chapter" + id +".xml");
            ebl = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化控件
    private void init(){
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        lv_list = (ListView)findViewById(R.id.lv_list);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(16.0f);
        tv.setText("第一部分、选择题（单选）");
        tv.setPadding(10,15,0,0);
        lv_list.addHeaderView(tv);
        tv_main_title.setText(title);
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter = new ExrecisesDetailAdapter(ExercisesDetailActivity.this,new ExrecisesDetailAdapter.OnSelectListener(){
            @Override
            public void onSelectD(int postion, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
                //判断如果答案不是4即D答案
            	if (ebl.get(postion).answer != 4){
                    ebl.get(postion).select=4;
                }else {
                    ebl.get(postion).select = 0;
                }
                switch (ebl.get(postion).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }
            @Override
            public void onSelectC(int postion, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
            	//判断如果答案不是3即C答案
            	if (ebl.get(postion).answer != 3){
                    ebl.get(postion).select=3;
                }else {
                    ebl.get(postion).select = 0;
                }
                switch (ebl.get(postion).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }
            @Override
            public void onSelectB(int postion, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
            	//判断如果答案不是2即B答案
            	if (ebl.get(postion).answer != 2){
                    ebl.get(postion).select=2;
                }else {
                    ebl.get(postion).select = 0;
                }
                switch (ebl.get(postion).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }
            @Override
            public void onSelectA(int postion, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
            	//判断如果答案不是1即A答案
            	if (ebl.get(postion).answer != 1){
                    ebl.get(postion).select=1;
                }else {
                    ebl.get(postion).select = 0;
                }
                switch (ebl.get(postion).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

        });
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }

}
