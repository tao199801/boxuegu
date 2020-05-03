package com.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.boxuegu.bean.UserBean;
import com.boxuegu.bean.VideoBean;
import com.boxuegu.sqlite.SQLiteHelper;

public class DBUtils {
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    private static DBUtils instance = null;
    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        //getWritableDatabase();可写的数据库对象
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    //保存用户个人资料信息
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.userName);
        cv.put("nickName", bean.nickName);
        cv.put("sex", bean.sex);
        cv.put("signature", bean.signature);
        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }

    //获取个人资料信息
    public UserBean getUserInfo(String userName) {
    														//获取对应用户名的个人信息
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?"; 
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()){
            bean = new UserBean();
            bean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature=cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return bean;
    }

    //修改资料
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName = ?", new String[]{userName});
    }
    
  //保存视频播放记录
    public void saveVideoPlayList(VideoBean bean, String userName) {
        //判断如果里面已经有此记录则需要先删除再重新存放
        if (hasVideoPlay(bean.chapterId, bean.videoId, userName)) {
            boolean isDelete = delVideoPlay(bean.chapterId, bean.videoId, userName);
            if (!isDelete) {
                //没有删除成功
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("userName", userName);
        cv.put("chapterId", bean.chapterId);
        cv.put("videoId", bean.videoId);
        cv.put("videoPath", bean.videoPath);
        cv.put("title", bean.title);
        cv.put("secondTitle", bean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST, null, cv);

    }

    /**
     * 删除已经存在的是视屏记录
     * @param chapterId
     * @param videoId
     * @param userName
     * @return
     */

    public boolean delVideoPlay(int chapterId, int videoId, String userName) {
        boolean delSuccess = false;
        int row = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST, " chapterId=? AND videoId=? AND userName=?",
                new String[]{chapterId + "", videoId + "", userName});
        if (row > 0) {
            delSuccess = true;
        }

        return delSuccess;
    }
    /**
     * 判断视频记录是否存在
     * @param chapterId
     * @param videoId
     * @param userName
     * @return
     */
    public boolean hasVideoPlay(int chapterId, int videoId, String userName) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST + " WHERE chapterId=? AND videoId=? AND userName =?";
        Cursor cursor = db.rawQuery(sql, new String[]{ chapterId + "", videoId + "", userName});
        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }

    public ArrayList<VideoBean> getVideoHistory(String userName){
        String sql="SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST + " WHERE userName = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        ArrayList<VideoBean> vbl = new ArrayList<VideoBean  >();
        VideoBean bean = null;
        while (cursor.moveToNext()){
            bean = new VideoBean();
            bean.chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
            bean.videoId = cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.videoPath = cursor.getString(cursor.getColumnIndex("videoPath"));
            bean.title = cursor.getString(cursor.getColumnIndex("title"));
            bean.secondTitle = cursor.getString(cursor.getColumnIndex("secondTitle"));
            vbl.add(bean);
            bean = null;

        }
        cursor.close();
        return vbl;
    }
    

}
