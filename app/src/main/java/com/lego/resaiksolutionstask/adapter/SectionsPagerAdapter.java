package com.lego.resaiksolutionstask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lego.resaiksolutionstask.controller.JsonController;
import com.lego.resaiksolutionstask.fragment.PlaceholderFragment;

/**
 * @author Lego on 29.08.2016.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private int mCurrentPosition;

    @Override
    public Fragment getItem(int position) {
        setmCurrentPosition(position);
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return JsonController.getInstance().getmImages().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        setmCurrentPosition(position);
        return null;
    }

    public void nextItem() {
        if (getmCurrentPosition() == getCount() - 1) {
            getItem(0);
        } else {
            getItem(getmCurrentPosition() + 1);
        }
    }

    public int getmCurrentPosition() {
        return mCurrentPosition;
    }

    public void setmCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }
}