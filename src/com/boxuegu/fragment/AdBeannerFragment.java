package com.boxuegu.fragment;

import com.boxuegu.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class AdBeannerFragment extends Fragment {
    private String ab;   //广告
    private ImageView iv;  //图片
    public static AdBeannerFragment newInstance(Bundle args){
        AdBeannerFragment af = new AdBeannerFragment();
        af.setArguments(args);
        return af;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        //获取广告图片名称
        ab = arg.getString("ad");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        if (ab != null){
            if ("banner_1".equals(ab)){
                iv.setImageResource(R.drawable.banner_1);
            }else if ("banner_2".equals(ab)){
                iv.setImageResource(R.drawable.banner_2);
            }else if ("banner_3".equals(ab)){
                iv.setImageResource(R.drawable.banner_3);
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (iv != null){
            iv.setImageDrawable(null);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        //创建广告图片控件
    	iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }

}