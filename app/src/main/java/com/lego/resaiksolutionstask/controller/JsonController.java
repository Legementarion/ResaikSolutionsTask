package com.lego.resaiksolutionstask.controller;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.SparseArray;

import com.lego.resaiksolutionstask.model.ImageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Lego on 29.08.2016.
 */

public class JsonController {
    private SparseArray<ImageModel> mImages = new SparseArray<>();
    private static JsonController instance;


    private JsonController() {
    }

    public static JsonController getInstance() {
        return instance == null ? (instance = new JsonController()) : instance;
    }

    public void init(AssetManager activity) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(activity));
            JSONArray m_jArry = obj.getJSONArray("images");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                ImageModel imageModel = new ImageModel(Integer.valueOf(jo_inside.getString("_id")),
                        jo_inside.getString("picture"),
                        jo_inside.getString("comment"),
                        Boolean.valueOf(jo_inside.getString("isFavourite")));
                mImages.put(Integer.parseInt(jo_inside.getString("index")), imageModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset(AssetManager activity) {
        String json;
        try {
            InputStream is = activity.open("generated.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public SparseArray<ImageModel> getmImages() {
        return mImages;
    }

    public void setmImages(SparseArray<ImageModel> mImages) {
        this.mImages = mImages;
    }
}
