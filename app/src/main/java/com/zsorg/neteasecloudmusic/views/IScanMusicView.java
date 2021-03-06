package com.zsorg.neteasecloudmusic.views;

import android.content.Context;

/**
 * Project:NeteaseCloudMusic
 *
 * @Author: piyel_000
 * Created on 2017/1/16.
 * E-mail:piyell@qq.com
 */

public interface IScanMusicView {
    void startScan();

    void finishScan();

    void onError(Throwable throwable);

    Context getContext();
}
