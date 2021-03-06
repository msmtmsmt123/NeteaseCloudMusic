package com.zsorg.neteasecloudmusic.presenters;


import com.zsorg.neteasecloudmusic.models.PlaylistModel;
import com.zsorg.neteasecloudmusic.models.beans.MusicBean;
import com.zsorg.neteasecloudmusic.views.IPlaylistView;

import java.util.ArrayList;

/**
 * Project:NeteaseCloudMusic
 *
 * @Author: piyel_000
 * Created on 2017/1/18.
 * E-mail:piyell@qq.com
 */

public class PlaylistPresenter {

    private final IPlaylistView iPlaylistView;

    public PlaylistPresenter(IPlaylistView iPlaylistView) {
        this.iPlaylistView = iPlaylistView;
    }

    public void requestList() {
        iPlaylistView.showItems(new PlaylistModel(iPlaylistView.getContext()).loadPlaylistList());
    }

    public ArrayList<MusicBean> loadPlaylist(int id) {
        return (ArrayList<MusicBean>) new PlaylistModel(iPlaylistView.getContext()).loadPlaylist(id);
    }
}
