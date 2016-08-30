package com.lego.resaiksolutionstask.model;

/**
 * @author Lego on 29.08.2016.
 */

public class ImageModel {

    private int id;
    private String url;
    private String comment;
    private boolean favorite;

    public ImageModel(int id, String url, String comment, boolean favorite) {
        this.id = id;
        this.url = url;
        this.comment = comment;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
