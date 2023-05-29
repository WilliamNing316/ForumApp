package com.example.ngm.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.UriUtils;
import com.example.ngm.DraftBoxActivity;
import com.example.ngm.DraftItem;
import com.example.ngm.GetPositionActivity;
import com.example.ngm.MainActivity;
import com.example.ngm.MyItem;
import com.example.ngm.PopupWindow;
import com.example.ngm.PublicData;
import com.example.ngm.R;
import com.example.ngm.databinding.FragmentDashboardBinding;
import com.example.ngm.dialog.InputTextMsgDialog;
import com.example.ngm.dialog.RichTextDialog;
import com.example.ngm.dialog.TagsDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private int width = 0;
    private int height = 0;


    public static ImageView tags;
    public static TextView tagtext;


    public int new_or_add = 0;
    private Intent intent;
    private int draft_numbers = 0;
    private NormalAdapter normalAdapter;
    private String temp_pic_path;
    private Bitmap bitmap;
    private ArrayList<Bitmap> bitmaps;
    Uri uri;

    private RichTextDialog richTextDialog;

//    private int mprofileImgResource;
    private Bitmap draft_bitmap;
    private String mTitle;
    private String mContent;


    private Dialog bottomDialog;


    private AlertDialog mAudioDialog;
    private TextView mAudioNotify;
    private ImageView mVoiceState;
    private TextView mAudioSend;
    private boolean mCancelSend;
    private final String TAG = "AudioSendActivity";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        PublicData.currentActivity = this.hashCode();

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.picture.setOnClickListener(this::onClick);
//        binding.text.getLayoutParams().width = width - 20;
//        binding.audio.setOnClickListener(this::onClick);


        final ImageView nextview = binding.next;
        final ImageView next_readyview = binding.nextReady;
        next_readyview.setOnClickListener(this::onClick);
        binding.exit.setOnClickListener(this::onClick);
        binding.draft.setOnClickListener(this::onClick);
        binding.getPosition.setOnClickListener(this::onClick);
        binding.textHtml.setOnClickListener(this::onClick);
        binding.tags.setOnClickListener(this::onClick);

        tags = binding.tags;
        tagtext = binding.tagsText;
//        binding.link.setOnClickListener(this::onClick);
//        binding.recordAudio.setOnTouchListener(this::onTouch);
        if(PublicData.draft_order > 0){
            binding.drafNumber.setText(PublicData.draft_order+"");
            binding.draftNumView.setVisibility(View.VISIBLE);
        }
        final EditText editText = binding.text;


//        ServiceSettings.updatePrivacyAgree(getContext(),true);
//        ServiceSettings.updatePrivacyShow(getContext(),true,true);
        // 创建底部弹出对话框
        bottomDialog = new Dialog(getContext());
        bottomDialog.setContentView(R.layout.rich_text_edit);

        // 关闭按钮的点击事件
//        Button btnClose = bottomDialog.findViewById(R.id.btnClose);
//        btnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomDialog.dismiss();
//            }
//        });







//


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
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
        Log.e("qqq",root+"");
        return root;
    }

    private void showBottomDialog() {
        // 设置对话框的样式和显示位置
        bottomDialog.setCancelable(true);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);

        // 显示对话框
        bottomDialog.show();
    }


// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.

    /**
     * 定位回调监听器
     */



    public void onClick(View v){
        switch (v.getId()){
            case R.id.next_ready:
                updat_tools();
                PublicData.post_order += 1;
                PublicData.mItems.add(0,new MyItem(bitmap,bitmaps,"Title " + PublicData.post_order, binding.text.getText()+"", "Author "+PublicData.post_order, "Date "+PublicData.post_order, "Comment "+PublicData.post_order));
                binding.text.setText("");

                intent = new Intent(this.getContext(), MainActivity.class);
                intent.putExtra("post_order",PublicData.post_order);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.picture:
                select_picture();
                updat_tools();
                break;
            case R.id.text_html:
                richTextDialog = new RichTextDialog(getContext(),R.style.rich_text);
                richTextDialog.show();
                break;
            case R.id.get_position:
                intent = new Intent(this.getContext(), GetPositionActivity.class);
                startActivity(intent);
                break;
            case R.id.tags:
                TagsDialog tagsDialog = new TagsDialog(getContext(),R.style.tags);
                tagsDialog.show();
                break;
            case R.id.exit:
                updat_tools();
                intent = new Intent(this.getContext(), MainActivity.class);
                PopupWindow popupWindow = new PopupWindow(this.getContext(),R.style.dialog);
                popupWindow.show(this.getContext(), "是否保存到草稿箱?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        selected[0] = true;
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            // Handle Yes button click
                            save_draft();
                            binding.text.setText("");
                            if(PublicData.draft_order > 0){
                                binding.drafNumber.setText(PublicData.draft_order+"");
                                binding.draftNumView.setVisibility(View.VISIBLE);

                            }
                        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                            // Handle No button click
                        }

                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });
//                if (selected[0]){

//                }
                break;

            case R.id.draft:
                updat_tools();
                intent = new Intent(this.getContext(), DraftBoxActivity.class);
                startActivity(intent);
                break;






        }
    }
    public void save_draft(){
        PublicData.draft_order += 1;
        PublicData.drafts.add(0,new DraftItem(bitmap,"Title " + PublicData.draft_order, "Content "+binding.text.getText()+"", "Date "+PublicData.draft_order, "Comment "+PublicData.draft_order));
    }

    public void updat_tools(){

    }


    public void select_picture(){
        ImageSelector.builder()
                .useCamera(false) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected() // 把已选的图片传入默认选中。
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this, this.getTargetRequestCode()); // 打开相册
    }

    public void select_audio(){

    }
//    public void add_picture(){
//        select_picture();
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.getTargetRequestCode() && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images_path = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);

            Log.e("images", images_path + "");
//            temp_pic_path = images_path.get(0);
//            String filePath = temp_pic_path;
//            bitmap = BitmapFactory.decodeFile(filePath);
            show_pics(this.getContext(),images_path);

            bitmaps = new ArrayList<>();
            for(int i = 0;i < images_path.size();i++){
                temp_pic_path = images_path.get(i);
                String filePath = temp_pic_path;
                bitmap = BitmapFactory.decodeFile(filePath);
                bitmaps.add(bitmap);
            }


        }
    }

    public void show_pics(Context context, ArrayList path){
        Log.e("new_or_add",new_or_add + "");

        if (new_or_add == 1){
            normalAdapter.addData(path);
            new_or_add = 0;
            return;
        }
        normalAdapter = new NormalAdapter(path);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext() );
//设置布局管理器
        binding.imagesList.setLayoutManager(layoutManager);
//设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//设置Adapter
        binding.imagesList.setAdapter(normalAdapter);
//设置增加或删除条目的动画
        binding.imagesList.setItemAnimator( new DefaultItemAnimator());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
//    public boolean onTouch(View v, MotionEvent event) {
//        if (v.getId() == R.id.record_audio) {
//            float startY = 0;
//            float endY = 0;
//            boolean send = false;
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    startY = event.getY();
//                    Log.d(TAG, "audioButtonDown() MotionEvent.ACTION_DOWN");
//                    showAudioDialog();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    endY = event.getY();
//                    hideAudioDialog();
//                    send = true;
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    float moveY = event.getY();
//                    int instance = (int) Math.abs((moveY - startY));
//                    Log.d(TAG, "--action move--instance:"+instance);
//                    // Inside your Fragment or Activity
//                    FragmentManager fragmentManager = getChildFragmentManager(); // or getSupportFragmentManager() for Activity
//
//                    Fragment newFragment = new AudioFragment(); // Create an instance of the new Fragment
//
//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.post_tool, newFragment); // Replace the current Fragment with the new Fragment
//                    transaction.addToBackStack(null); // Optional: Add the transaction to the back stack
//                    transaction.commit();
//                    if (instance > 100) {
//                        changeAudioDialogCancel(true);
//                    } else {
//                        changeAudioDialogCancel(false);
//                    }
//                    break;
//                default:
//                    break;
//            }
//
//            return true;
//        }
//        return false;
//    }


    public void showAudioDialog() {
        if (mAudioDialog == null) {
            mAudioDialog = new AlertDialog.Builder(this.getContext()).create();
            mAudioDialog.show();
            mAudioDialog.getWindow().setContentView(R.layout.audio_dialog);
            mAudioDialog.getWindow().setGravity(Gravity.CENTER);
            mAudioNotify = (TextView) mAudioDialog
                    .findViewById(R.id.audio_nofity);
            mVoiceState = (ImageView) mAudioDialog
                    .findViewById(R.id.voice_state);

        } else if (!mAudioDialog.isShowing()) {
            mAudioDialog.show();
        }
        mVoiceState.setImageResource(R.drawable.move_audio);
        AnimationDrawable drawable = (AnimationDrawable) mVoiceState
                .getDrawable();
        drawable.start();
    }

    private void hideAudioDialog() {
        if (mAudioDialog != null && mAudioDialog.isShowing()) {
            mAudioDialog.dismiss();
        }
    }


    private void changeAudioDialogCancel(boolean cancel) {
        if (mCancelSend == cancel)
            return;
        if (mAudioDialog != null && mAudioDialog.isShowing()
                && mAudioNotify != null) {
            if (cancel) {
                mVoiceState.setImageResource(R.drawable.exit);
                mAudioNotify.setTextColor(Color.RED);
                mAudioNotify.setText("ahozhi");
            } else {
                mAudioNotify.setTextColor(Color.WHITE);
                mAudioNotify.setText("huhuh");
                mVoiceState.setImageResource(R.drawable.move_audio);
                AnimationDrawable drawable = (AnimationDrawable) mVoiceState
                        .getDrawable();
                drawable.start();
            }
        }
        mCancelSend = cancel;
    }
    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{
        //② 创建ViewHolder
        public class VH extends RecyclerView.ViewHolder{
            public final ImageView imageView;
            public final ImageView delete;
            public final ImageView add;
            public VH(View v) {
                super(v);
                 imageView = v.findViewById(R.id.picture);
                 delete = v.findViewById(R.id.delete);
                 add = v.findViewById(R.id.add);
            }
        }

        private List<String> mDatas;
        public NormalAdapter(List<String> data) {

            this.mDatas = data;
//            if (mDatas.size() < 9){
//                mDatas.add("last_pic_to_add");
//            }

        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(VH holder, @SuppressLint("RecyclerView") int position) {
            if (mDatas.get(position) == "last_pic_to_add"){
                holder.imageView.setBackgroundColor(Color.parseColor("#eeeeee"));
                holder.add.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.INVISIBLE);
            }
            holder.imageView.setImageURI(UriUtils.getImageContentUri(getContext(),mDatas.get(position)));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //delete 点击事件

                    notifyItemRemoved(position);
                    mDatas.remove(position);
                    notifyDataSetChanged();
//                    holder.
                }
            });

            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new_or_add = 1;
                    select_picture();

                }
            });

        }
        public void addData(List<String> new_data) {
        //   在list中新增資料，並通知條目加入一條
            int id = 0;
            while (mDatas.size() < 9 && id < new_data.size()){
                Log.e("id",id+"");
                this.mDatas.add(new_data.get(id));
                notifyItemInserted(mDatas.size() + 1);
                id += 1;
            }
            //新增動畫

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_picture, parent, false);
            return new VH(v);
        }
    }

}