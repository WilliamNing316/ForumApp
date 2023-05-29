package com.example.ngm;

import android.graphics.Bitmap;

public class FanItem {
    private Bitmap img_bitmap;
    private String nick_name;
    public FanItem(Bitmap bitmap, String nick_name){
        this.img_bitmap = bitmap;
        this.nick_name = nick_name;
    }
    public Bitmap getImg_bitmap(){
        return img_bitmap;
    }

    public String getNick_name()
    {
        return nick_name;
    }
}
