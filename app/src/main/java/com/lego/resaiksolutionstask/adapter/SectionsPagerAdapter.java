package com.lego.resaiksolutionstask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        mCurrentPosition = position;
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }

    public void nextItem(){
        if (mCurrentPosition == getCount()-1){
            getItem(0);
        }else {
            getItem(mCurrentPosition + 1);
        }
    }
}