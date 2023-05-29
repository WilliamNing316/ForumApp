package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.UriUtils;
import com.example.ngm.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private Intent intent;
    private EditText editText;
    private RecyclerView recyclerView;
    private ImageView nextview;
    private ImageView next_readyview;
    private LinearLayout picture;
    private Bitmap bitmap;
    private ArrayList<Bitmap> bitmaps;
    private ImageView exit;

    private String temp_pic_path;
    private CreateActivity.NormalAdapter normalAdapter;

    int draft_order;
    String draft_content;
    Bitmap draft_bitmap;
    String draft_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        intent = getIntent();

        picture = findViewById(R.id.picture);
        editText = findViewById(R.id.text);
        recyclerView = findViewById(R.id.images_list);
        nextview = findViewById(R.id.next);
        exit = findViewById(R.id.exit);
        next_readyview = findViewById(R.id.next_ready);

        next_readyview.setOnClickListener(this::onClick);
        picture.setOnClickListener(this::onClick);
        exit.setOnClickListener(this::onClick);

        InitialView();

    }

    public void InitialView(){

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
        bitmap = PublicData.drafts.get(draft_order).getImageResource();

        draft_order = intent.getIntExtra("draft_order",-1);
        if (draft_order == -1)
            return;
        editText.setText(PublicData.drafts.get(draft_order).getContent());
        show_pics(this,null,bitmap);


    }
    public void select_picture(){
        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected() // 把已选的图片传入默认选中。
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this,getChangingConfigurations()); // 打开相册
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getChangingConfigurations() && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images_path = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);

            Log.e("images", images_path + "");
//            temp_pic_path = images_path.get(0);
//            String filePath = temp_pic_path;
//            bitmap = BitmapFactory.decodeFile(filePath);

            bitmaps = new ArrayList<>();
            for(int i = 0;i < images_path.size();i++){
                temp_pic_path = images_path.get(i);
                String filePath = temp_pic_path;
                bitmap = BitmapFactory.decodeFile(filePath);
                bitmaps.add(bitmap);
            }


            show_pics(this,images_path,null);


        }
    }


    public void show_pics(Context context, ArrayList path, Bitmap bitmap){
//        Log.e("new_or_add",new_or_add + "");
//        if (new_or_add == 1){
//            normalAdapter.addData(path);
//            new_or_add = 0;
//            return;
//
        normalAdapter = new CreateActivity.NormalAdapter(path,bitmap);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
//设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//设置Adapter
        recyclerView.setAdapter(normalAdapter);
//设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());

    }

    // ① 创建Adapter
    public class NormalAdapter extends RecyclerView.Adapter<CreateActivity.NormalAdapter.VH>{
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
        private Bitmap temp_bitmap;
        public NormalAdapter(List<String> data,Bitmap bitmap) {


            this.mDatas = data;
            this.temp_bitmap = bitmap;
//            if (mDatas.size() < 9){
//                mDatas.add("last_pic_to_add");
//            }

        }

        //③ 在Adapter中实现3个方法
        @Override
        public void onBindViewHolder(CreateActivity.NormalAdapter.VH holder, @SuppressLint("RecyclerView") int position) {
            if(mDatas != null){
                if (mDatas.get(position) == "last_pic_to_add"){
                    holder.imageView.setBackgroundColor(Color.parseColor("#eeeeee"));
                    holder.add.setVisibility(View.VISIBLE);
                    holder.delete.setVisibility(View.INVISIBLE);
                }
            }

            if(temp_bitmap != null){
                holder.imageView.setImageBitmap(temp_bitmap);
            }
            else {


            holder.imageView.setImageURI(UriUtils.getImageContentUri(holder.imageView.getContext(),mDatas.get(position)));
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
//                    new_or_add = 1;
                    select_picture();

                }
            }); }

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
            if (mDatas != null){
                return mDatas.size();
            }
            else return 1;
        }

        @Override
        public CreateActivity.NormalAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_picture, parent, false);
            return new NormalAdapter.VH(v);
        }
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.next_ready:
                PublicData.post_order += 1;
                PublicData.draft_order -= 1;
                PublicData.mItems.add(0,new MyItem(bitmap,bitmaps,"Title " + PublicData.post_order, "Content "+editText.getText()+"", "Author "+PublicData.post_order, "Date "+PublicData.post_order, "Comment "+PublicData.post_order));
                PublicData.drafts.remove(draft_order);
                editText.setText("");
                intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

                //                NavController navController = Navigation.findNavController(binding.nextReady);
//                navController.navigate(R.id.nav_host_fragment_activity_main);
                //                intent = new Intent(this.getContext(), HomeFragment.class);
//                startActivity(intent);
//                Toast toast = Toast.makeText(this.getContext(),"huhu",Toast.LENGTH_LONG);
//                toast.show();
                break;
            case R.id.picture:
//                intent = new Intent(this.getContext(), PostImageActivity.class);
//                startActivity(intent);
                select_picture();
                break;
            case R.id.exit:
//                Toast toast1 = Toast.makeText(this.getContext(),"huhu",Toast.LENGTH_LONG);
//                toast1.show();
                PopupWindow popupWindow = new PopupWindow(this,R.style.dialog);
                popupWindow.show(this, "是否保存到草稿箱?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            // Handle Yes button click
                            save_draft();
                            editText.setText("");
//                            if(PublicData.draft_order > 0){
//                                binding.drafNumber.setText(PublicData.draft_order+"");
//                                binding.draftNumView.setVisibility(View.VISIBLE);
//
//                            }
                        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                            // Handle No button click
                        }
                    }
                });
                break;

            case R.id.draft:
                intent = new Intent(this, DraftBoxActivity.class);
                startActivity(intent);
                break;



        }
    }
    public void save_draft(){
        PublicData.draft_order += 1;
        PublicData.drafts.add(0,new DraftItem(bitmap,"Title " + PublicData.draft_order, "Content "+editText.getText()+"", "Date "+PublicData.draft_order, "Comment "+PublicData.draft_order));
    }

}