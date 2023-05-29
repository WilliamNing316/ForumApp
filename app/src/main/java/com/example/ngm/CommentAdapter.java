package com.example.ngm;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ngm.dialog.InputTextMsgDialog;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private ArrayList<CommentItem> answers;
    private Intent intent;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView comment_profile;
        public TextView comment_people;
        public TextView comment_time;
        public TextView praise_nums;
        public ImageView praise;
        public TextView comment_content;
//        public TextView mAuthorTextView;
//        public TextView mDateTextView;
//        public CircleImageView profile_img;

//        public TextView mContent;


        public MyViewHolder(View itemView) {
            super(itemView);
//            ImageSwitcher imageSwitcher = itemView.findViewById(R.id.image_switcher);
//            imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//                @Override
//                public View makeView() {
//                    ImageView imageView = new ImageView(itemView.getContext());
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    return imageView;
//                }
//            });

            comment_profile = itemView.findViewById(R.id.comment_profile);
            comment_people = itemView.findViewById(R.id.comment_man);
            comment_time = itemView.findViewById(R.id.comment_time);
            praise = itemView.findViewById(R.id.praise);
            praise_nums = itemView.findViewById(R.id.praise_nums);


            comment_content = itemView.findViewById(R.id.comment_content);
//            mContent = itemView.findViewById(R.id.text_content);
//            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
//            mDateTextView = itemView.findViewById(R.id.date_text_view);
//            profile_img = itemView.findViewById(R.id.profile_pic);


//            imageSwitcher.setImageResource(imageIds[currentIndex[0]]);

// Set a click listener to switch to the next image
//            imageSwitcher.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    currentIndex[0] = (currentIndex[0] + 1) % imageIds.length;
//                    imageSwitcher.setImageResource(imageIds[currentIndex[0]]);
//                }
//            });


        }
    }

    public CommentAdapter(ArrayList<CommentItem> items) {
//        PublicData.drafts = items;
        Log.e("temp","ddddddddddddddd");
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_comment, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CommentItem currentItem = PublicData.commentItems.get(position);
        holder.praise.setOnClickListener(view -> {
            if(!PublicData.commentItems.get(position).praise){
                holder.praise.setImageResource(R.drawable.heart_praise);
                PublicData.commentItems.get(position).praise_num += 1;
                PublicData.commentItems.get(position).praise = true;
                holder.praise_nums.setText(PublicData.commentItems.get(position).praise_num+"");
            }
            else{
                holder.praise.setImageResource(R.drawable.heart_no_praise);
                PublicData.commentItems.get(position).praise_num -= 1;
                PublicData.commentItems.get(position).praise = false;
                holder.praise_nums.setText(PublicData.commentItems.get(position).praise_num+"");
            }

        });

        holder.itemView.setOnClickListener(view -> {
//            InputTextMsgDialog inputTextMsgDialog;
//            inputTextMsgDialog = new InputTextMsgDialog(holder.itemView.getContext(), R.style.dialog);
//            inputTextMsgDialog.show();

        });





        if(PublicData.commentItems.get(position).praise){
            holder.praise.setImageResource(R.drawable.heart_praise);
        }
        else{
            holder.praise.setImageResource(R.drawable.heart_no_praise);
        }
        holder.praise_nums.setText(currentItem.praise_num+"");
        holder.comment_profile.setImageBitmap(currentItem.getImageResource());
        holder.comment_content.setText(currentItem.getContent());


    }

    @Override
    public int getItemCount() {
        return PublicData.commentItems.size();
    }
}