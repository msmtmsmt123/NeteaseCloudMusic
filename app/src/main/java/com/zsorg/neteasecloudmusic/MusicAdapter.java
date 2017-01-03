package com.zsorg.neteasecloudmusic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.telephony.SmsMessage;
import android.view.View;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by piyel_000 on 2017/1/3.
 */

class MusicAdapter extends FragmentPagerAdapter {
    @BindArray(R.array.music)
    String[] mTabName;

    public MusicAdapter(View view, FragmentManager fm) {
        super(fm);
        ButterKnife.bind(this,view);
    }

    @Override
    public Fragment getItem(int position) {
        return SubMusicFragment.newInstance(" ", " ");
    }

    @Override
    public int getCount() {
        return mTabName.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }
}
