package com.example.ngm;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class CommentItem {

    private Bitmap mImageResource;
    private String mAccount;
    private String nick_name;
    private String comment_content;
    private String mDate;
    private ArrayList<CommentItem> answers;
    public int praise_num = 0;
    public boolean praise = false;




    public CommentItem(Bitmap profileImgResource, String account, String content, String nickname, String date) {
        mImageResource = profileImgResource;
        mAccount = account;
        comment_content = content;
        nick_name = nickname;
        mAccount = account;
        mDate = date;
    }

    public Bitmap getImageResource() {
        return mImageResource;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getContent(){return comment_content;}

    public String getmAccount(){return mAccount;}

    public String getDate() {
        return mDate;
    }

}