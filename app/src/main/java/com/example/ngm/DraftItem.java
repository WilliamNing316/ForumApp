package com.example.ngm;


import android.graphics.Bitmap;

public class DraftItem {

    private Bitmap mImageResource;
    private String mTitle;
    private String mContent;
    private String mDate;




    public DraftItem(Bitmap profileImgResource, String title, String content, String author, String date) {
        mImageResource = profileImgResource;
        mContent = content;
        mTitle = title;
        mDate = date;
    }

    public Bitmap getImageResource() {
        return mImageResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent(){return mContent;}

    public String getDate() {
        return mDate;
    }

}