package com.boxuegu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.boxuegu.R;
import com.boxuegu.bean.VideoBean;

//列表
public class VideoListAdapter extends BaseAdapter{

    private Context mContext;
    private List<VideoBean> vbl;  //视频列表数据
    private int selectedPosition = -1;  //点击时选中的位置
    private OnSelectListener onSelectListener;
    public VideoListAdapter(Context context,OnSelectListener onSelectListener){
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }

    public void setSelectedPosition(int position){
        selectedPosition = position;
    }

    //设置数据更新界面
    public void setData(List<VideoBean> vbl){
        this.vbl = vbl;
        notifyDataSetChanged();
    }

    //获取Item的总数
    @Override
    public int getCount() {
        return vbl == null ? 0 : vbl.size();
    }

  //根据position得到item对应的对象
    @Override
    public VideoBean getItem(int position) {
        return vbl == null ? null : vbl.get(position);
    }

    //根据position得到item对应的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //得到对应position的item视图
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder vh;
        if (view == null){
            vh = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.video_list_item,null
            );
            vh.tv_title = (TextView) view.findViewById(R.id.tv_video_title);
            vh.iv_icon = (ImageView) view.findViewById(R.id.iv_left_icon);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        final VideoBean bean = getItem(position);
        vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
        vh.tv_title.setTextColor(Color.parseColor("#333333"));
        if (bean!=null){
            vh.tv_title.setText(bean.secondTitle);
            //设置选中效果
            if (selectedPosition == position){
                vh.iv_icon.setImageResource(R.drawable.course_intro_icon);
                vh.tv_title.setTextColor(Color.parseColor("#009958"));

            }else {
                vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
                vh.tv_title.setTextColor(Color.parseColor("#333333"));
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean == null)
                    return;
                //播放视频
                onSelectListener.onSelect(position,vh.iv_icon);
            }
        });
        return view;
    }

    class ViewHolder{
        public TextView tv_title;
        public ImageView iv_icon;
    }

    //创建接口，传递position和ImageView
    public interface OnSelectListener{
        void onSelect(int position,ImageView iv);
    }
}
