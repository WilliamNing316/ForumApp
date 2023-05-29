package com.example.ngm;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ngm.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.MyViewHolder> {

//    private ArrayList<MyItem> mItems;
    private Intent intent;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleTextView;
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

            mImageView = itemView.findViewById(R.id.draft_img);
            mTitleTextView = itemView.findViewById(R.id.draft_text);
//            mContent = itemView.findViewById(R.id.text_content);
//            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
//            mDateTextView = itemView.findViewById(R.id.date_text_view);
//            profile_img = itemView.findViewById(R.id.profile_pic);
            int[] imageIds = { R.drawable.add, R.drawable.answer, R.drawable.collect };
            final int[] currentIndex = {0};

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

    public DraftAdapter(ArrayList<DraftItem> items) {
//        PublicData.drafts = items;
        Log.e("temp","ddddddddddddddd");
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_draft, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DraftItem currentItem = PublicData.drafts.get(position);
//        intent = new Intent(holder.itemView.getContext(),DetailedPostActivity.class);
//        intent.putExtra("profile_img_src",mItems.get(holder.getLayoutPosition()).getMprofileImgResource());
//        intent.putExtra("img_src",mItems.get(position).getImageResource());
//        intent.putExtra("title",mItems.get(position).getTitle());
//        intent.putExtra("author",mItems.get(position).getAuthor());
//        intent.putExtra("date",mItems.get(position).getDate());
//        Toast toast = new Toast(holder.itemView.getContext());
//        toast.setText(mItems.get(holder.getLayoutPosition()).getTitle());
//        toast.show();

        holder.itemView.setOnClickListener(view -> {

//            Toast toast = new Toast(holder.itemView.getContext());
//            toast.setText(holder.getLayoutPosition() + "");
//            toast.show();

            intent = new Intent(holder.itemView.getContext(), CreateActivity.class);
            intent.putExtra("draft_order",holder.getLayoutPosition());
//            intent.putExtra("id",1);
//
//            intent.putExtra("profile_img_src",PublicData.mItems.get(holder.getLayoutPosition()).getMprofileImgResource());
//            intent.putExtra("draft_img",PublicData.drafts.get(holder.getLayoutPosition()).getImageResource());
//            intent.putExtra("draft_content",PublicData.drafts.get(holder.getLayoutPosition()).getContent());
//            intent.putExtra("draft_title",PublicData.drafts.get(holder.getLayoutPosition()).getTitle());
//            intent.putExtra("author",PublicData.mItems.get(holder.getLayoutPosition()).getAuthor());
//            intent.putExtra("date",PublicData.mItems.get(holder.getLayoutPosition()).getDate());
            holder.itemView.getContext().startActivity(intent);


        });
//        holder.profile_img.setImageBitmap(currentItem.getMprofileImgResource());
        holder.mImageView.setImageBitmap(currentItem.getImageResource());
        holder.mTitleTextView.setText(currentItem.getTitle());
//        holder.mAuthorTextView.setText(currentItem.getAuthor());
//        holder.mDateTextView.setText(currentItem.getDate());

    }

//    private void onClick(View v){
//        intent = new Intent(v.getContext(),DetailedPostActivity.class);
//        Toast toast = new Toast(v.getContext());
//        toast.setText(v. + "");
//        toast.show();
////        intent.putExtra("profile_img_src",mItems.get().getMprofileImgResource());
////        intent.putExtra("img_src",mItems.get(position).getImageResource());
////        intent.putExtra("title",mItems.get(position).getTitle());
////        intent.putExtra("author",mItems.get(position).getAuthor());
////        intent.putExtra("date",mItems.get(position).getDate());
////        v.getContext().startActivity(intent);
//
//    }



    @Override
    public int getItemCount() {
        return PublicData.drafts.size();
    }
}