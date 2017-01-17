package com.zsorg.neteasecloudmusic.models.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsorg.neteasecloudmusic.models.beans.MusicBean;
import com.zsorg.neteasecloudmusic.utils.MusicUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project:NeteaseCloudMusic
 *
 * @Author: piyel_000
 * Created on 2017/1/16.
 * E-mail:piyell@qq.com
 */

public class DiskMusicDao {

    private final SQLiteDatabase mDB;

    public DiskMusicDao(Context context) {
        mDB = new DiskMusicHelper(context, "diskMusic", 1).getWritableDatabase();
    }

    @Override
    protected void finalize() throws Throwable {
        mDB.close();
        super.finalize();
    }

    public void addMusic(MusicBean musicBean) {
        addMusic(musicBean.getName(), musicBean.getSinger(), musicBean.getAlbum(),musicBean.getDuration(), musicBean.getPath());
    }

    public void clearAll() {
        mDB.execSQL("delete from diskMusic;");
    }

    public void addMusic(String name, String singer, String album,long duration, String path) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("singer",singer);
        values.put("album",album);
        values.put("path",path);
        values.put("parent",path.substring(0,path.lastIndexOf(File.separatorChar)));
        values.put("duration",duration);
        mDB.insert("diskMusic", null, values);
    }

    public List<MusicBean> queryAll() {
        Cursor cursor = mDB.rawQuery("select * from diskMusic order by name desc;", null);
        ArrayList<MusicBean> musicList = new ArrayList<>();
        while (null != cursor && cursor.moveToNext()) {
            String name = cursor.getString(0);
            String singer = cursor.getString(1);
            String album = cursor.getString(2);
            String path = cursor.getString(4);
            long duration = cursor.getLong(3);
            musicList.add(new MusicBean(name, singer, album,duration, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return musicList;
    }

    public Map<String,List<MusicBean>> queryAllName() {
        Cursor cursor = mDB.rawQuery("select name,singer,album,path,duration,count(path) from diskMusic group by name order by name desc;", null);
        HashMap<String, List<MusicBean>> musicMap = new HashMap<>();
        while (null != cursor && cursor.moveToNext()) {
            String name = cursor.getString(0);
            String singer = cursor.getString(1);
            String album = cursor.getString(2);
            String path = cursor.getString(3);
            long duration = cursor.getLong(4);
            int count = cursor.getInt(5);
            List<MusicBean> list = musicMap.get(name);
            if (null == list) {
                list = new ArrayList<>();
                musicMap.put(name, list);
            }

            list.add(new MusicBean(name, singer, album,duration, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return musicMap;
    }

    public List<MusicBean> querySingerList() {
        Cursor cursor = mDB.rawQuery("select singer,path,count(path) from diskMusic group by singer order by singer desc;", null);
        List<MusicBean> list = new ArrayList<>();
        while (null != cursor && cursor.moveToNext()) {
            String singer = cursor.getString(0);
            String path = cursor.getString(1);
            int count = cursor.getInt(2);
            list.add(new MusicBean(null, singer, null,count, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    public Map<String,List<MusicBean>> queryAllSinger() {
        Cursor cursor = mDB.rawQuery("select name,singer,album,path,duration,count(path) from diskMusic group by singer order by singer desc;", null);
        HashMap<String, List<MusicBean>> musicMap = new HashMap<>();
        while (null != cursor && cursor.moveToNext()) {
            String name = cursor.getString(0);
            String singer = cursor.getString(1);
            String album = cursor.getString(2);
            String path = cursor.getString(3);
            long duration = cursor.getLong(4);
            int count = cursor.getInt(5);
            List<MusicBean> list = musicMap.get(name);
            if (null == list) {
                list = new ArrayList<>();
                musicMap.put(name, list);
            }

            list.add(new MusicBean(name, singer, album,duration, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return musicMap;
    }

    public List<MusicBean> queryAlbumList() {
        Cursor cursor = mDB.rawQuery("select singer,album,path,count(path) from diskMusic group by album order by album desc;", null);
        List<MusicBean> list = new ArrayList<>();
        while (null != cursor && cursor.moveToNext()) {
            String singer = cursor.getString(0);
            String album = cursor.getString(1);
            String path = cursor.getString(2);
            int count = cursor.getInt(3);
            list.add(new MusicBean(null, singer, album,count, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    public Map<String,List<MusicBean>> queryAllAlbum() {
        Cursor cursor = mDB.rawQuery("select name,singer,album,path,duration,count(path) from diskMusic group by album order by album desc;", null);
        HashMap<String, List<MusicBean>> musicMap = new HashMap<>();
        while (null != cursor && cursor.moveToNext()) {
            String name = cursor.getString(0);
            String singer = cursor.getString(1);
            String album = cursor.getString(2);
            String path = cursor.getString(3);
            long duration = cursor.getLong(4);
            int count = cursor.getInt(5);
            List<MusicBean> list = musicMap.get(name);
            if (null == list) {
                list = new ArrayList<>();
                musicMap.put(name, list);
            }

            list.add(new MusicBean(name, singer, album,duration, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return musicMap;
    }

    public List<MusicBean> queryPathList() {
        Cursor cursor = mDB.rawQuery("select parent,path,count(parent) from diskMusic group by album order by parent desc;", null);
        List<MusicBean> list = new ArrayList<>();
        while (null != cursor && cursor.moveToNext()) {
            String parent = cursor.getString(0);
            String path = cursor.getString(1);
            int count = cursor.getInt(2);
            String[] parentAndFileName = MusicUtil.getParentAndFileName(parent);
            list.add(new MusicBean(parentAndFileName[1],parent ,parentAndFileName[0],count, path));
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }
}