package com.boxuegu.view;

import android.app.Activity;
import com.boxuegu.R;
import com.boxuegu.adapter.ExercisesAdapter;
import com.boxuegu.bean.ExercisesBean;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public ExercisesView(Activity context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }
    private void initView(){
        mCurrentView = mInflater.inflate(R.layout.main_view_exercises,null);
        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    
    //设置数据
    private void initData(){
        ebl = new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean = new ExercisesBean();
            bean.id = (i+1);
            switch (i){
                case 0:
                    bean.title = "第1章 博客(网站)简介";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title = "第2章 认识WordPress";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title = "第3章 WordPress基础";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title = "第4章 熟悉Linux系统";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title = "第5章 PHP基本语法";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title = "第6章 MySQL数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title = "第7章 Nginx服务器";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title = "第8章 云服务器简介";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title = "第9章 环境搭建过程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title = "第10章 WordPress主题";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }
    
    //获取当前在导航栏上方显示对应的view
    public View getView(){
        if (mCurrentView == null){
            createView();
        }
        return mCurrentView;
    }
    
    //显示当前导航栏上方所对应的view界面
    public void showView(){
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}