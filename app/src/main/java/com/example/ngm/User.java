package com.example.ngm;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class User {
    public Bitmap profileImgResource; //头像
    public String account; //账号
    public String username; //昵称
    public String password; //密码
    public String gender;
    public String birthday;
    public String intro;
    public ArrayList<String> following_;
    public ArrayList<String> follower_;
    public ArrayList<MyItem> myItems;



    public User(Bitmap src,String account_,String username_){
        profileImgResource = src;
        account = account_;
        username = username_;
    }

}
