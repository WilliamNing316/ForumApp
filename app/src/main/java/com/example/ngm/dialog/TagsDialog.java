package com.example.ngm.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import com.example.ngm.DraftBoxActivity;
import com.example.ngm.DraftItem;
import com.example.ngm.HtmlTextRecord;
import com.example.ngm.MainActivity;
import com.example.ngm.MyItem;
import com.example.ngm.PopupWindow;
import com.example.ngm.PublicData;
import com.example.ngm.R;
import com.example.ngm.ui.dashboard.DashboardFragment;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author ganhuanhui
 * 时间：2019/12/11 0011
 * 描述：https://blog.csdn.net/qq_32518491/article/details/85000421
 */
public class TagsDialog extends AppCompatDialog {


    private TextView study;
    private TextView entertainment;
    private TextView help;
    private TextView transaction;
    private TextView notag;

    private Context mContext;


    private Intent intent;


    public TagsDialog(@NonNull Context context, int theme) {
        super(context,theme);
        this.mContext = context;
        init();
        setLayout();
    }



    private void init() {
        setContentView(R.layout.tags_dialog);
        study = findViewById(R.id.study);
        study.setOnClickListener(this::onClick);

        entertainment = findViewById(R.id.entertainment);
        entertainment.setOnClickListener(this::onClick);
        help = findViewById(R.id.help);
        help.setOnClickListener(this::onClick);

        transaction = findViewById(R.id.transaction);
        transaction.setOnClickListener(this::onClick);

        notag = findViewById(R.id.notag);
        notag.setOnClickListener(this::onClick);

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

    public void hide_big_color(){
//        reded.setVisibility(View.INVISIBLE);
//        orangeed.setVisibility(View.INVISIBLE);
//        greened.setVisibility(View.INVISIBLE);
//        blueed.setVisibility(View.INVISIBLE);
//        purpleed.setVisibility(View.INVISIBLE);
//        purple2ed.setVisibility(View.INVISIBLE);
//        blackededed.setVisibility(View.INVISIBLE);
//
//        red.setVisibility(View.VISIBLE);
//        orange.setVisibility(View.VISIBLE);
//        green.setVisibility(View.VISIBLE);
//        blue.setVisibility(View.VISIBLE);
//        purple.setVisibility(View.VISIBLE);
//        purple2.setVisibility(View.VISIBLE);
//        black.setVisibility(View.VISIBLE);
    }


    @SuppressLint("ResourceAsColor")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.study:
                super.dismiss();
                DashboardFragment.tags.setImageResource(R.drawable.tagsed);
                DashboardFragment.tagtext.setText("学习");
                break;
            case R.id.entertainment:
                super.dismiss();
                DashboardFragment.tags.setImageResource(R.drawable.tagsed);
                DashboardFragment.tagtext.setText("娱乐");
                break;
            case R.id.help:
                super.dismiss();
                DashboardFragment.tags.setImageResource(R.drawable.tagsed);
                DashboardFragment.tagtext.setText("求助");
                break;
            case R.id.transaction:
                super.dismiss();
                DashboardFragment.tags.setImageResource(R.drawable.tagsed);
                DashboardFragment.tagtext.setText("交易");
                break;
            case R.id.notag:
                super.dismiss();
                DashboardFragment.tags.setImageResource(R.drawable.tags);
                DashboardFragment.tagtext.setText("");
                break;
        }
    }


    @Override
    public void show() {
        super.show();
    }
    public void disappear(){super.dismiss();}
}
