package com.lego.resaiksolutionstask.fragment;

/**
 * @author Lego on 29.08.2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.resaiksolutionstask.R;
import com.lego.resaiksolutionstask.controller.JsonController;
import com.lego.resaiksolutionstask.model.ImageModel;
import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private JsonController mJsonController;
    private ImageModel mImageModel;

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
        mJsonController = JsonController.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        getJson();
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        Picasso.with(getContext()).load(mImageModel.getUrl()).into(imageView);
        if (!mImageModel.getComment().equals("")) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(mImageModel.getComment());
        }
        return rootView;
    }

    private void getJson() {
        mImageModel = mJsonController.getmImages().get(getArguments().getInt(ARG_SECTION_NUMBER) -1);
    }


}
