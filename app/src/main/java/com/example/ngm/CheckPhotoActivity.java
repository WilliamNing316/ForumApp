package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class CheckPhotoActivity extends AppCompatActivity {

    public SliderView sliderView;
    private ArrayList<SliderItem> sliderItems = new ArrayList<>();
    private Intent intent;
    private int post_order;
    private int current_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_photo);

        intent = getIntent();
        post_order = intent.getIntExtra("post_order",-1);
        current_page = intent.getIntExtra("current_page",0);
        if(post_order != -1){
            for (int i = 0;i < PublicData.mItems.get(post_order).getImageResource().size();i++){
                sliderItems.add(new SliderItem(PublicData.mItems.get(post_order).getImageResource().get(i)));
            }
        }

        sliderView = findViewById(R.id.checkSlider);
        sliderView.setSliderAdapter(new SliderAdapter(this,sliderItems));
        sliderView.setCurrentPagePosition(current_page);
        sliderView.setIndicatorRadius(3);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

    }
//    @Override
    public void onBackPressed() {
        PublicData.currentActivity = DetailedPostActivity.class.hashCode();//用于限制此时无法再重复进入CheckPhotoActivity
        super.onBackPressed();
    }

}