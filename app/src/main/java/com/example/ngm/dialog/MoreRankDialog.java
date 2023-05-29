package com.example.ngm.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.cardview.widget.CardView;

import com.example.ngm.R;
import com.example.ngm.ui.dashboard.DashboardFragment;

import java.util.ArrayList;


/**
 * @author ganhuanhui
 * 时间：2019/12/11 0011
 * 描述：https://blog.csdn.net/qq_32518491/article/details/85000421
 */
public class MoreRankDialog extends Dialog {


    private TextView study;
    private TextView entertainment;
    private TextView help;
    private TextView transaction;
    private TextView time;
    private TextView praise;
    private TextView comment;
    private TextView my_follow;
    private TextView hottest;

    private ArrayList<Boolean> type_selection;
    private ArrayList<Boolean> rank_selection;
    private ArrayList<Boolean> other_two_selection;

    private Context mContext;

    private LinearLayout disappear;


    private Intent intent;


    public MoreRankDialog(@NonNull Context context, int theme) {
        super(context,theme);
        this.mContext = context;
        type_selection = new ArrayList<>(4);
        int type_i = 0;
        for(int i = 0;i < 4;i ++){
            type_i++;
            type_selection.add(false);
        }
        rank_selection = new ArrayList<>(3);
        int rank_i = 0;
        for(int i = 0;i < 3;i ++){
            rank_i++;
            rank_selection.add(false);
        }
        other_two_selection = new ArrayList<>(2);
        int other = 0;
        for(int i = 0;i < 3;i ++){
            other++;
            other_two_selection.add(false);
        }
        set_zero(type_selection);
        init();
        setLayout();
    }



    private void init() {
        setContentView(R.layout.more_rank_dialog);

        study = findViewById(R.id.study_1);
        study.setOnClickListener(this::onClick);

        entertainment = findViewById(R.id.entertainment_1);
        entertainment.setOnClickListener(this::onClick);

        help = findViewById(R.id.help_1);
        help.setOnClickListener(this::onClick);

        transaction = findViewById(R.id.transaction_1);
        transaction.setOnClickListener(this::onClick);

        time = findViewById(R.id.time);
        time.setOnClickListener(this::onClick);

        praise = findViewById(R.id.praise);
        praise.setOnClickListener(this::onClick);

        comment = findViewById(R.id.comment);
        comment.setOnClickListener(this::onClick);

        my_follow = findViewById(R.id.my_follow);
        my_follow.setOnClickListener(this::onClick);

        hottest = findViewById(R.id.hottest);
        hottest.setOnClickListener(this::onClick);

        disappear = findViewById(R.id.disappear);
        disappear.setOnClickListener(this::onClick);



//        rank = findViewById(R.id.rank);
//        rank.setOnClickListener(this::onClick);


    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }






    @SuppressLint("ResourceAsColor")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.study_1:
                if(type_selection.get(0))
                    type_selection.set(0,false);
                else {
                    set_zero(type_selection);
                    type_selection.set(0,true);
                }
                set_selected(1,type_selection);
//
                break;
            case R.id.entertainment_1:
                if(type_selection.get(1))
                    type_selection.set(1,false);
                else {
                    set_zero(type_selection);
                    type_selection.set(1,true);
                }
                set_selected(1,type_selection);
//
                break;
            case R.id.help_1:
                if(type_selection.get(2))
                    type_selection.set(2,false);
                else {
                    set_zero(type_selection);
                    type_selection.set(2,true);
                }
                set_selected(1,type_selection);

                break;
            case R.id.transaction_1:
                if(type_selection.get(3))
                    type_selection.set(3,false);
                else {
                    set_zero(type_selection);
                    type_selection.set(3,true);
                }
                set_selected(1,type_selection);

                break;

            case R.id.time:
                if(rank_selection.get(0))
                    rank_selection.set(0,false);
                else {
                    set_zero(rank_selection);
                    rank_selection.set(0,true);
                }
                set_selected(2,rank_selection);

                break;
            case R.id.praise:
                if(rank_selection.get(1))
                    rank_selection.set(1,false);
                else {
                    set_zero(rank_selection);
                    rank_selection.set(1,true);
                }
                set_selected(2,rank_selection);

                break;
            case R.id.comment:
                if(rank_selection.get(2))
                    rank_selection.set(2,false);
                else {
                    set_zero(rank_selection);
                    rank_selection.set(2,true);
                }
                set_selected(2,rank_selection);

                break;

            case R.id.my_follow:
                if(other_two_selection.get(0))
                    other_two_selection.set(0,false);
                else {
                    set_zero(other_two_selection);
                    other_two_selection.set(0,true);
                }
                set_selected(3,other_two_selection);

                break;
            case R.id.hottest:
                if(other_two_selection.get(1))
                    other_two_selection.set(1,false);
                else {
                    set_zero(other_two_selection);
                    other_two_selection.set(1,true);
                }
                set_selected(3,other_two_selection);

                break;
            case R.id.disappear:
                super.dismiss();
                break;
        }
    }

    public void set_zero(ArrayList<Boolean> selection){
        for(int i = 0;i < selection.size(); i++){
            selection.set(i,false);
        }
    }


    @SuppressLint("ResourceAsColor")
    public void set_selected(int type, ArrayList<Boolean> selection){
        switch (type){
            case 1:
                for(int i =0;i < selection.size(); i++){
                    if (selection.get(i)){
                        switch (i){
                            case 0:
                                study.setBackgroundResource(R.color.maintheme);
                                study.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 1:
                                entertainment.setBackgroundResource(R.color.maintheme);
                                entertainment.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 2:
                                help.setBackgroundResource(R.color.maintheme);
                                help.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 3:
                                transaction.setBackgroundResource(R.color.maintheme);
                                transaction.setTextColor(Color.parseColor("#ffffff"));
                                break;
                        }
                    }
                    else {
                        switch (i){
                            case 0:
                                study.setBackgroundResource(R.color.white);
                                study.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 1:
                                entertainment.setBackgroundResource(R.color.white);
                                entertainment.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 2:
                                help.setBackgroundResource(R.color.white);
                                help.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 3:
                                transaction.setBackgroundResource(R.color.white);
                                transaction.setTextColor(Color.parseColor("#000000"));
                                break;
                        }
                    }

                }
                break;
            case 2:
                for(int i =0;i < selection.size(); i++){
                    if (selection.get(i)){
                        switch (i){
                            case 0:
                                time.setBackgroundResource(R.color.maintheme);
                                time.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 1:
                                praise.setBackgroundResource(R.color.maintheme);
                                praise.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 2:
                                comment.setBackgroundResource(R.color.maintheme);
                                comment.setTextColor(Color.parseColor("#ffffff"));
                                break;
                        }
                    }
                    else {
                        switch (i){
                            case 0:
                                time.setBackgroundResource(R.color.white);
                                time.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 1:
                                praise.setBackgroundResource(R.color.white);
                                praise.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 2:
                                comment.setBackgroundResource(R.color.white);
                                comment.setTextColor(Color.parseColor("#000000"));
                                break;
                        }
                    }

                }
                break;
            case 3:
                for(int i =0;i < selection.size(); i++){
                    if (selection.get(i)){
                        switch (i){
                            case 0:
                                my_follow.setBackgroundResource(R.color.maintheme);
                                my_follow.setTextColor(Color.parseColor("#ffffff"));
                                break;
                            case 1:
                                hottest.setBackgroundResource(R.color.maintheme);
                                hottest.setTextColor(Color.parseColor("#ffffff"));
                                break;
                        }
                    }
                    else {
                        switch (i){
                            case 0:
                                my_follow.setBackgroundResource(R.color.white);
                                my_follow.setTextColor(Color.parseColor("#000000"));
                                break;
                            case 1:
                                hottest.setBackgroundResource(R.color.white);
                                hottest.setTextColor(Color.parseColor("#000000"));
                                break;
                        }
                    }

                }
                break;

        }
    }

    @Override
    public void show() {
        super.show();
    }
    public void disappear(){super.dismiss();}
}
