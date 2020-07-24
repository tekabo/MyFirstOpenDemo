package com.ysj.pageslide.Imageandtabslide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/7/24
 * PackageName com.ysj.pageslide.Imageandtabslide
 */
public class FmPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public FmPagerAdapter(List<Fragment> fragmentList, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList;
        this.fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList!=null&&fragmentList.isEmpty()?fragmentList.size():0;
    }
}
