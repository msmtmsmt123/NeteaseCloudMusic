package com.zsorg.neteasecloudmusic.presenters;

import android.view.View;
import android.widget.TextView;

import com.zsorg.neteasecloudmusic.BaseHolder;
import com.zsorg.neteasecloudmusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piyel_000 on 2017/1/5.
 */

public class SongListItemHolder extends BaseHolder {
    @BindView(R.id.tv_song_name)
    TextView tvTitle;
    @BindView(R.id.tv_songs_in_total)
    TextView tvContent;

    public SongListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
