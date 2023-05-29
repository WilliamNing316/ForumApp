package com.example.ngm;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class MyItem {

    private Bitmap mImageResource;
    public ArrayList<Bitmap> mImageList = new ArrayList<>();
    private Bitmap mprofileImgResource;
    private String mTitle;
    private String mContent;
    private String mAuthor;
    private String mDate;
    public int mPraise = 0;
    public int mCollect = 0;
    public int mTransmit = 0;
    public int mComment = 0;
    public Boolean up_not = false;
    public Boolean collect_not = false;
    public Boolean transmit_not = false;
    private int up_down_state = 0;




    public MyItem(Bitmap profileImgResource, ArrayList<Bitmap> ImageList, String title, String content, String author, String date, String comment) {
//        mImageList.add(imageResource);
        mprofileImgResource = profileImgResource;
        mImageList = ImageList;
        mContent = content;
        mTitle = title;
        mAuthor = author;
        mDate = date;
    }

    public ArrayList<Bitmap> getImageResource() {
        return mImageList;
    }

    public Bitmap getMprofileImgResource() {
        return mprofileImgResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent(){return mContent;}

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public int getPraise(){return mPraise;}
    public int getmCollect(){return mCollect;}
    public int getmTransmit(){return mTransmit;}
    public int getmComment(){return mComment;}

}