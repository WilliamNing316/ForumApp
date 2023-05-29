package com.example.ngm;

import android.graphics.Bitmap;

public class SliderItem {
    private Bitmap img_bitmap;
    public SliderItem(Bitmap bitmap){
        this.img_bitmap = bitmap;
    }
    public Bitmap getImg_bitmap(){
        return img_bitmap;
    }

}
