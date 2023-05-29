package com.example.ngm.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import com.example.ngm.CommentApplication;
import com.example.ngm.CommentItem;
import com.example.ngm.DetailedPostActivity;
import com.example.ngm.DraftBoxActivity;
import com.example.ngm.DraftItem;
import com.example.ngm.HtmlTextRecord;
import com.example.ngm.MainActivity;
import com.example.ngm.MyItem;
import com.example.ngm.PopupWindow;
import com.example.ngm.PublicData;
import com.example.ngm.R;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author ganhuanhui
 * 时间：2019/12/11 0011
 * 描述：https://blog.csdn.net/qq_32518491/article/details/85000421
 */
public class RichTextDialog extends AppCompatDialog {
    private Context mContext;
    private HtmlTextRecord htmlTextRecord;
    private InputMethodManager imm;
    private EditText editText;
    private RelativeLayout rlDlg;
    private int mLastDiff = 0;
    private TextView tvNumber;
    private int maxNumber = 100;
    private TextView underline_text;
    private  Editable editable;

    private TextView blacked;
    private TextView Italic;
    private TextView underline;

    private CircleImageView red;
    private CircleImageView orange;
    private CircleImageView green;
    private CircleImageView blue;
    private CircleImageView purple;
    private CircleImageView purple2;
    private CircleImageView black;

    private TextView blackeded;
    private CircleImageView reded;
    private CircleImageView orangeed;
    private CircleImageView greened;
    private CircleImageView blueed;
    private CircleImageView purpleed;
    private CircleImageView purple2ed;
    private CircleImageView blackededed;

    // 创建 SpannableStringBuilder 对象
    private SpannableStringBuilder spannableStringBuilder;

    private String selectedText;

    private int startSelection;
    private int endSelection;
    private ForegroundColorSpan colorSpan;

    private SpannableString spannableString;
    private SeekBar seekBar;

    private Intent intent;


    private ImageView nextview;
    private ImageView next_readyview;
    private ImageView exit;
    private ImageView draft;

    private TextView draftNumber;
    private LinearLayout draftNumView;





    public interface OnTextSendListener {

        void onTextSend(String msg);

        void dismiss();
    }

    private OnTextSendListener mOnTextSendListener;

    public RichTextDialog(@NonNull Context context,int theme) {
        super(context,theme);
        this.mContext = context;
        init();
        if(PublicData.draft_order > 0){
            draftNumber.setText(PublicData.draft_order+"");
            draftNumView.setVisibility(View.VISIBLE);
        }
        seekBar.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(0);
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                   int fontSize = (int) ((0.1*progress) + 20);  // 设置字体大小的基准值，并根据 SeekBar 进度进行调整

                                                   // 获取选中文本的起始和结束位置
                                                   int selectionStart = editText.getSelectionStart();
                                                   int selectionEnd = editText.getSelectionEnd();

                                                   // 应用字体大小到选中文本
                                                   SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(editText.getText());
                                                   spannableStringBuilder.setSpan(new AbsoluteSizeSpan(fontSize, true), selectionStart, selectionEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                                   editText.setText(spannableStringBuilder);
                                                   editText.setSelection(selectionStart,selectionEnd);
                                               }


            @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {

                                               }
                                           });
        setLayout();
    }

    /**
     * 最大输入字数  默认100
     */
    @SuppressLint("SetTextI18n")
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
        tvNumber.setText("0/" + maxNumber);
    }

    /**
     * 设置输入提示文字
     */




    private void init() {
        setContentView(R.layout.rich_text_edit);
        underline_text = findViewById(R.id.underline);
        underline_text.setPaintFlags(underline_text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        editText = findViewById(R.id.edit_view);


        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this::onClick);

        draft = findViewById(R.id.draft);
        draft.setOnClickListener(this::onClick);
        draftNumber = findViewById(R.id.draf_number);
        draftNumView = findViewById(R.id.draft_num_view);

        nextview = findViewById(R.id.next);
        next_readyview = findViewById(R.id.next_ready);
        nextview.setOnClickListener(this::onClick);
        next_readyview.setOnClickListener(this::onClick);

        blacked = findViewById(R.id.blacked);
        blacked.setOnClickListener(this::onClick);

        Italic = findViewById(R.id.Italic_text);
        Italic.setOnClickListener(this::onClick);

        underline = findViewById(R.id.underline);
        underline.setOnClickListener(this::onClick);

        seekBar = findViewById(R.id.seekbar);

        red = findViewById(R.id.red);
        orange = findViewById(R.id.orange);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        purple = findViewById(R.id.purple);
        purple2 = findViewById(R.id.purple2);
        black = findViewById(R.id.black);

        reded = findViewById(R.id.reded);
        orangeed = findViewById(R.id.oranged);
        greened = findViewById(R.id.greened);
        blueed = findViewById(R.id.blued);
        purpleed = findViewById(R.id.purpled);
        purple2ed = findViewById(R.id.purple2ed);
        blackededed = findViewById(R.id.blackeded);

        red.setOnClickListener(this::onClick);
        orange.setOnClickListener(this::onClick);
        green.setOnClickListener(this::onClick);
        blue.setOnClickListener(this::onClick);
        purple.setOnClickListener(this::onClick);
        purple2.setOnClickListener(this::onClick);
        black.setOnClickListener(this::onClick);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable1) {
//                htmlTextRecord = new HtmlTextRecord(editText.getText().toString());
                editable = editText.getText();
                spannableString = new SpannableString(editable);
////                htmlTextRecord.text_content = editText.getText().toString();
//                Log.e("text0",htmlTextRecord.text_content);
                if(editText.getText()+"" != ""){
                    nextview.setVisibility(View.INVISIBLE);
                    next_readyview.setVisibility(View.VISIBLE);
                }
                else {
                    next_readyview.setVisibility(View.INVISIBLE);
                    nextview.setVisibility(View.VISIBLE);
                }
            }
        });

// 获取选中的文本的起始和结束位置



        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
                    RichTextDialog.this.dismiss();
                }
                return false;
            }
        });
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void hide_big_color(){
        reded.setVisibility(View.INVISIBLE);
        orangeed.setVisibility(View.INVISIBLE);
        greened.setVisibility(View.INVISIBLE);
        blueed.setVisibility(View.INVISIBLE);
        purpleed.setVisibility(View.INVISIBLE);
        purple2ed.setVisibility(View.INVISIBLE);
        blackededed.setVisibility(View.INVISIBLE);

        red.setVisibility(View.VISIBLE);
        orange.setVisibility(View.VISIBLE);
        green.setVisibility(View.VISIBLE);
        blue.setVisibility(View.VISIBLE);
        purple.setVisibility(View.VISIBLE);
        purple2.setVisibility(View.VISIBLE);
        black.setVisibility(View.VISIBLE);
    }

    public void setTextColor(int color){
        startSelection = editText.getSelectionStart();
        endSelection = editText.getSelectionEnd();
//        SpannableString spannableString = new SpannableString(editable);
//        spannableString.setSpan(new TypefaceSpan("monospace"), startSelection, endSelection, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        selectedText = editText.getText().toString().substring(startSelection, endSelection);
        colorSpan = new ForegroundColorSpan(color);
        spannableStringBuilder = new SpannableStringBuilder(editText.getText());
        // 将 StyleSpan 应用到选中的文本
        spannableStringBuilder.setSpan(colorSpan, startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置编辑框的文本为修改后的 SpannableStringBuilder 对象
        editText.setText(spannableStringBuilder);
        editText.setSelection(startSelection,endSelection);

    }

    public void setUnderline_text(){
//        startSelection = editText.getSelectionStart();
//        endSelection = editText.getSelectionEnd();
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(editText.getText());
//        spannableStringBuilder.setSpan(new UnderlineSpan(), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//// 将设置好下划线的 SpannableString 设置回 EditText
//        editText.setText(spannableString);
//        editText.setSelection(startSelection,endSelection);

        startSelection = editText.getSelectionStart();
        endSelection = editText.getSelectionEnd();
        selectedText = editText.getText().toString().substring(startSelection, endSelection);
        spannableStringBuilder = new SpannableStringBuilder(editText.getText());
        spannableStringBuilder.setSpan(new UnderlineSpan(), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置编辑框的文本为修改后的 SpannableStringBuilder 对象
        editText.setText(spannableStringBuilder);
        editText.setSelection(startSelection,endSelection);

    }

    public void setItalic_text(){
        startSelection = editText.getSelectionStart();
        endSelection = editText.getSelectionEnd();
        selectedText = editText.getText().toString().substring(startSelection, endSelection);
////        Typeface typeface = editText.getTypeface();
//        Typeface typeface = editText.getTypeface();
//
//// 判断字体是否倾斜
//        boolean isItalic = (typeface != null && typeface.isItalic());
        CharacterStyle[] styleSpans = editable.getSpans(startSelection, endSelection, CharacterStyle.class);
        spannableString = new SpannableString(editable);
        // 将 StyleSpan 应用到选中的文本

//        boolean isBold = false;
//        boolean hasUnderline = false;
//        boolean isItalic = false;

//        for (CharacterStyle span : styleSpans) {
//            if (span instanceof StyleSpan) {
//                int style = ((StyleSpan) span).getStyle();
////                isBold = (style == Typeface.BOLD || style == Typeface.BOLD_ITALIC);
//                isItalic = (style == Typeface.ITALIC || style == Typeface.BOLD_ITALIC);
//            }
//        }
//        Log.e("121",isItalic+"");
//        StyleSpan styleSpan = new StyleSpan(Typeface.NORMAL);
//        if(!isItalic){
//            styleSpan = new StyleSpan(Typeface.ITALIC);
//            spannableString.setSpan(styleSpan, startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        else {
//            spannableString.removeSpan(styleSpan);
////            spannableString.setSpan(new StyleSpan(Typeface.NORMAL), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        StyleSpan[] styleSpans1 = editable.getSpans(startSelection, endSelection, StyleSpan.class);
//        for (StyleSpan styleSpan : styleSpans1) {
//            if (styleSpan.getStyle() == Typeface.ITALIC) {
//                editable.removeSpan(styleSpan);
//
//            }
//            else {
//                spannableString.setSpan(new StyleSpan(Typeface.ITALIC), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            Log.e("121",(styleSpan.getStyle() == Typeface.ITALIC)+"");
//        }
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置编辑框的文本为修改后的 SpannableStringBuilder 对象
        editText.setText(spannableString);
        editText.setSelection(startSelection,endSelection);

    }

    public void setBold(){
//        startSelection = editText.getSelectionStart();
//        endSelection = editText.getSelectionEnd();
//        spannableString= new SpannableString(editText.getText());
//        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        editText.setText(spannableString);
//        editText.setSelection(startSelection,endSelection);

        startSelection = editText.getSelectionStart();
        endSelection = editText.getSelectionEnd();
        selectedText = editText.getText().toString().substring(startSelection, endSelection);
        spannableString = new SpannableString(editable);
        // 将 StyleSpan 应用到选中的文本
//        StyleSpan[] styleSpans1 = editable.getSpans(startSelection, endSelection, StyleSpan.class);
//        for (StyleSpan styleSpan : styleSpans1) {
//            if (styleSpan.getStyle() == Typeface.BOLD) {
//                editable.removeSpan(styleSpan);
//            }
//            else {
//                spannableString.setSpan(new StyleSpan(Typeface.BOLD), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            Log.e("121",(styleSpan.getStyle() == Typeface.BOLD)+"");
//        }
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置编辑框的文本为修改后的 SpannableStringBuilder 对象
        editText.setText(spannableString);
        editText.setSelection(startSelection,endSelection);
    }



    @SuppressLint("ResourceAsColor")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.blacked:
                setBold();
//                startSelection = editText.getSelectionStart();
//                endSelection = editText.getSelectionEnd();
//
//                spannableString.setSpan(new TypefaceSpan("monospace"), startSelection, endSelection, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                selectedText = editText.getText().toString().substring(startSelection, endSelection);
//
//                spannableStringBuilder = new SpannableStringBuilder(editText.getText());
//
//                // 创建 StyleSpan 对象，设置字体样式为粗体
//                StyleSpan boldStyleSpan = new StyleSpan(Typeface.BOLD);
//
//                // 将 StyleSpan 应用到选中的文本
//                spannableStringBuilder.setSpan(boldStyleSpan, startSelection, endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                // 设置编辑框的文本为修改后的 SpannableStringBuilder 对象
//                editText.setText(spannableStringBuilder);
//                editText.setSelection(startSelection,endSelection);
                break;
            case R.id.next_ready:
                PublicData.post_order += 1;
                PublicData.mItems.add(0,new MyItem(null,null,"Title " + PublicData.post_order, editable+"", "Author "+PublicData.post_order, "Date "+PublicData.post_order, "Comment "+PublicData.post_order));
                editText.setText("");
                intent = new Intent(this.getContext(), MainActivity.class);
                intent.putExtra("post_order",PublicData.post_order);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                this.getContext().startActivity(intent);
                break;

            case R.id.draft:
                intent = new Intent(this.getContext(), DraftBoxActivity.class);
                this.getContext().startActivity(intent);
                break;
            case R.id.exit:

                intent = new Intent(this.getContext(), MainActivity.class);
//                RichTextDialog inputTextMsgDialog = new RichTextDialog(this.getContext(),R.style.rich_text);
//                inputTextMsgDialog.show();
                PopupWindow popupWindow = new PopupWindow(this.getContext(),R.style.window);
                popupWindow.show(this.getContext(), "是否保存到草稿箱?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        selected[0] = true;
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            // Handle Yes button click
                            save_draft();
                            editText.setText("");
                            if(PublicData.draft_order > 0){
                                draftNumber.setText(PublicData.draft_order+"");
                                draftNumView.setVisibility(View.VISIBLE);

                            }
                        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                            // Handle No button click
                        }

                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        getContext().startActivity(intent);

                    }
                });

                break;
            case R.id.underline:
                setUnderline_text();
                break;
            case R.id.Italic_text:
                setItalic_text();
                break;

            case R.id.red:
                hide_big_color();
                reded.setVisibility(View.VISIBLE);
                red.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.out_red));
                }
                break;
            case R.id.orange:
                hide_big_color();
                orangeed.setVisibility(View.VISIBLE);
                orange.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.maintheme));
                }
                break;
            case R.id.green:
                hide_big_color();
                greened.setVisibility(View.VISIBLE);
                green.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.light_green));
                }
                break;
            case R.id.blue:
                hide_big_color();
                blueed.setVisibility(View.VISIBLE);
                blue.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.light_blue));
                }
                break;
            case R.id.purple:
                hide_big_color();
                purpleed.setVisibility(View.VISIBLE);
                purple.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.purple_200));
                }
                break;
            case R.id.purple2:
                hide_big_color();
                purple2ed.setVisibility(View.VISIBLE);
                purple2.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.purple_500));
                }
                break;
            case R.id.black:
                hide_big_color();
                blackededed.setVisibility(View.VISIBLE);
                black.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getContext().getColor(R.color.black));
                }
                break;

        }
    }


    public void save_draft(){
        PublicData.draft_order += 1;
        PublicData.drafts.add(0,new DraftItem(null,"Title " + PublicData.draft_order, "Content "+editText.getText()+"", "Date "+PublicData.draft_order, "Comment "+PublicData.draft_order));
    }

    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0;
        if (mOnTextSendListener!=null) mOnTextSendListener.dismiss();

    }

    @Override
    public void show() {
        super.show();
    }
}
