package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpacePageActivity extends AppCompatActivity {


    private Intent intent;
    private int width;
    private int height;
    private CircleImageView profile_img;
    private LinearLayout follower_bar;
    private LinearLayout following_bar;
    private LinearLayout post_bar;
    private ConstraintLayout information;
    private ImageView mail;
    private CardView follow_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_page);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;

        bind_views();


    }

    protected void bind_views(){
        profile_img = findViewById(R.id.profile_image);
        profile_img.getLayoutParams().width = width/4;
        profile_img.getLayoutParams().height = width/4;

        follower_bar = findViewById(R.id.followers_bar);
        follower_bar.getLayoutParams().width = width/5;
        follower_bar.getLayoutParams().height = 2*width/5;

        post_bar = findViewById(R.id.post_bar);
        post_bar.getLayoutParams().width = width/5;
        post_bar.getLayoutParams().height = 2*width/5;

        following_bar = findViewById(R.id.following_bar);
        following_bar.getLayoutParams().width = width/5;
        following_bar.getLayoutParams().height = 2*width/5;

        information = findViewById(R.id.infomations);
        information.getLayoutParams().height = height/4;

        follow_btn = findViewById(R.id.ID_view);
        follow_btn.getLayoutParams().height = width/13;
        follow_btn.getLayoutParams().width = 7*width/24;

        mail = findViewById(R.id.mail);
        mail.getLayoutParams().height = width/13;
        mail.getLayoutParams().width = width/10;

        mail.setOnClickListener(this::onClick);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.mail:
                intent = new Intent(this,ChatActivity.class);
                startActivity(intent);
                break;
        }
    }
}