package com.lego.resaiksolutionstask.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.lego.resaiksolutionstask.R;
import com.lego.resaiksolutionstask.controller.JsonController;
import com.lego.resaiksolutionstask.dialog.FavoriteDialog;
import com.lego.resaiksolutionstask.model.ImageModel;
import com.lego.resaiksolutionstask.utils.Settings;
import com.lego.resaiksolutionstask.utils.UpdateImage;

import java.util.Random;


public class ImageFragment extends Fragment{

    private FavoriteDialog mAddFavorite;
    private SliderLayout sliderShow;
    private JsonController mJsonController;

    private ImageModel[] mImageModels;
    private boolean mRandomPic;
    private boolean mFavoritePic;

    public ImageFragment() {
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
        mRandomPic = Settings.orderPickChange;
        mFavoritePic = Settings.showAll;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        sliderShow = (SliderLayout) view.findViewById(R.id.slider);
        mJsonController = JsonController.getInstance();


        initImages();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddFavorite = new FavoriteDialog(getContext(), new UpdateImage() {
                    @Override
                    public void updateImage() {
                        initImages();
                    }
                });
                sliderShow.getCurrentPosition();
                mAddFavorite.show(mJsonController.getmImages().indexOfValue(getmImageModels()[sliderShow.getCurrentPosition()]));
            }
        });
        return view;
    }

    private void initImages() {
        sliderShow.removeAllSliders();
        SparseArray<ImageModel> Images = mJsonController.getmImages();
        setmImageModels(new ImageModel[Images.size()]);

        for (int i = 0; i < Images.size(); i++) {
            getmImageModels()[i] = Images.get(i);
        }

        //random order
        if (Settings.orderPickChange) {
            shufle();
        }

        //show all/favorite
        for (int i = 0; i < Images.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(getmImageModels()[i].getComment())
                    .image(getmImageModels()[i].getUrl());
            if (Settings.showAll) {
                if (getmImageModels()[i].isFavorite()) {
                    sliderShow.addSlider(textSliderView);
                }
            } else {
                sliderShow.addSlider(textSliderView);
            }
        }
        Settings.reDraw = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        acceptSettings();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void acceptSettings() {
        //animation
        if (Settings.animationPickChange) {
            sliderShow.setPresetTransformer(SliderLayout.Transformer.ZoomIn);
        } else {
            sliderShow.setPresetTransformer(SliderLayout.Transformer.CubeIn);
        }

        //auto change
        if (Settings.autoPickChange) {
            sliderShow.startAutoCycle();
        } else {
            sliderShow.stopAutoCycle();
        }

        //change duration
        sliderShow.setDuration(Settings.intervalPickChange * 1000);

        if (Settings.reDraw || mFavoritePic!=Settings.showAll || mRandomPic!=Settings.orderPickChange) {
            initImages();
        }
    }

    private void shufle() {
        Random rnd = new Random();
        for (int i = 0; i < getmImageModels().length - 1; i++) {
            int index = rnd.nextInt(getmImageModels().length);
            ImageModel buf = getmImageModels()[index];
            getmImageModels()[index] = getmImageModels()[i];
            getmImageModels()[i] = buf;
        }
    }

    public ImageModel[] getmImageModels() {
        return mImageModels;
    }

    public void setmImageModels(ImageModel[] mImageModels) {
        this.mImageModels = mImageModels;
    }
}
