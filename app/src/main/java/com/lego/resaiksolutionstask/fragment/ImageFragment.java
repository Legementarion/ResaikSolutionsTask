package com.lego.resaiksolutionstask.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lego.resaiksolutionstask.R;
import com.lego.resaiksolutionstask.adapter.SectionsPagerAdapter;
import com.lego.resaiksolutionstask.utils.Settings;


public class ImageFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public ImageFragment() {
        // Required empty public constructor
    }
    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.tabContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Settings.autoPickChange){
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mSectionsPagerAdapter.nextItem();
                }
            }, Settings.intervalPickChange*1000);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
