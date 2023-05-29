package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngm.dialog.InputTextMsgDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
//import com.potyvideo.slider.library.SliderLayout;
//import com.potyvideo.slider.library.SliderTypes.BaseSliderView;
//import com.potyvideo.slider.library.SliderTypes.TextSliderViewCurve;
//import com.potyvideo.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedPostActivity extends AppCompatActivity {

    private Intent intent;
    private int mImageResource;
    private int mprofileImgResource;
    private String mTitle;
    private String mContent;
    private String mAuthor;
    private String mDate;
//    private SliderLayout sliderLayout;

    public static int post_order;

    public ImageView mImageView;
    public TextView mTitleTextView;
    public TextView mContentView;
    public TextView mAuthorTextView;
    public TextView mDateTextView;
    public SliderView sliderView;
    public static TextView mCommentnum;
    public static TextView mCommentnum1;
    private NestedScrollView nestedScrollView;
    private ImageView comment_area;
    private ArrayList<SliderItem> sliderItems = new ArrayList<>();


    public CircleImageView profile_img;
    private CardView comment_view;
    private BottomSheetDialog bottomSheetDialog;
    private InputTextMsgDialog inputTextMsgDialog;
    static public RecyclerView comments_recycleview;
    static public CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);
        intent = getIntent();





//        sliderItems = PublicData.mItems.get(post_order).getImageResource();
        post_order = intent.getIntExtra("post_order",-1);
        if(post_order != -1){
            for (int i = 0;i < PublicData.mItems.get(post_order).getImageResource().size();i++){
                sliderItems.add(new SliderItem(PublicData.mItems.get(post_order).getImageResource().get(i)));
            }
        }
//        mImageResource = intent.getIntExtra("img_src",0);
//        mprofileImgResource = intent.getIntExtra("profile_img_src",0);
//        mContent = intent.getStringExtra("content");
//        mTitle = intent.getStringExtra("title");
//        mAuthor = intent.getStringExtra("author");
//        mDate = intent.getStringExtra("date");
        setView();
        adapter = new CommentAdapter(PublicData.commentItems);
        comments_recycleview.setLayoutManager(new LinearLayoutManager(this));
        comments_recycleview.setAdapter(adapter);

//        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "https://media.comicbook.com/2017/04/big-bang-theory-cast-kaley-cuoco-jim-parsons-992959.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "https://ksassets.timeincuk.net/wp/uploads/sites/55/2017/08/2017_GameOfThrones_HBO_220817-920x584.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.mail);
        file_maps.put("Big Bang Theory", R.drawable.up);
        file_maps.put("House of Cards", R.drawable.down);
        file_maps.put("Game of Thrones", R.drawable.praise);
//
//        for (String name : file_maps.keySet()) {
//            TextSliderViewCurve textSliderView = new TextSliderViewCurve(this, false);
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
//                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);
//
//            sliderLayout.addSlider(textSliderView);
//        }
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
////        sliderLayout.setCustomAnimation(new DescriptionAnimation(false));
//        sliderLayout.setDuration(4000);
//        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);



    }

    private void setView(){
//        Toast toast = new Toast(this);
//        toast.setText(mTitle);
//        toast.show();
//        sliderLayout = findViewById(R.id.slider);
        sliderView = findViewById(R.id.imageSlider);
//        mImageView = findViewById(R.id.image_view);
        mTitleTextView = findViewById(R.id.title_text_view);
        mAuthorTextView = findViewById(R.id.author_text_view);
        mDateTextView = findViewById(R.id.date_text_view);
        profile_img = findViewById(R.id.profile_pic);
        mContentView = findViewById(R.id.text_content);
        comment_view = findViewById(R.id.comment);
        comments_recycleview = findViewById(R.id.comments);
        mCommentnum = findViewById(R.id.comment_nums);
        mCommentnum1 = findViewById(R.id.comment_num);
        nestedScrollView = findViewById(R.id.scroll);
        comment_area = findViewById(R.id.comment_area);
        comment_view.setOnClickListener(this::onClick);
        comment_area.setOnClickListener(this::onClick);


        profile_img.setImageBitmap(PublicData.user.profileImgResource);

        sliderView.setSliderAdapter(new SliderAdapter(this,sliderItems));
        sliderView.setIndicatorRadius(3);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.startAutoCycle();
//        mImageView.setImageBitmap(PublicData.mItems.get(post_order).getImageResource().get(2));
        mTitleTextView.setText(PublicData.mItems.get(post_order).getTitle());
        mContentView.setText(PublicData.mItems.get(post_order).getContent());
        mAuthorTextView.setText(PublicData.mItems.get(post_order).getAuthor());
        mDateTextView.setText(PublicData.mItems.get(post_order).getDate());
        update_comment_num();

    }
    static public void update_comment_num(){
        mCommentnum.setText("共有"+PublicData.commentItems.size()+"条评论");
        mCommentnum1.setText(PublicData.commentItems.size()+"");
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.comment:
                inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog);
                inputTextMsgDialog.show();
                break;
            case R.id.comment_area:
                nestedScrollView.smoothScrollTo(0,mCommentnum1.getScrollY());
                break;
        }
    }
}