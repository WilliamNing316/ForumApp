package com.example.ngm;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

//    private ArrayList<MyItem> mItems;
    private Intent intent;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mAuthorTextView;
        public TextView mDateTextView;
        public CircleImageView profile_img;
        public CardView cardView;
        public TextView follow_text;
        public ImageView up;
        public ImageView collect;
        public TextView praise_num;
        public TextView collect_num;
        public TextView transmit_num;

        private ImageView transmit;

        private int height;
        private int width;

        public TextView comment_num;


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

            mImageView = itemView.findViewById(R.id.image_view);
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            cardView = itemView.findViewById(R.id.follow);
            follow_text = itemView.findViewById(R.id.followed);
            up = itemView.findViewById(R.id.up);
            collect = itemView.findViewById(R.id.collect);
            praise_num = itemView.findViewById(R.id.praise_num);
            collect_num = itemView.findViewById(R.id.collect_num);
            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
            profile_img = itemView.findViewById(R.id.profile_pic);

            transmit = itemView.findViewById(R.id.transmit);
            transmit_num = itemView.findViewById(R.id.transmit_num);
            comment_num =  itemView.findViewById(R.id.comment_num);
//            transmit.setOnClickListener(this::onClick);


            cardView.setOnClickListener(this::onClick);
            up.setOnClickListener(this::onClick);
//            collect.setOnClickListener(this::onClick);


        }
//        @SuppressLint("ResourceAsColor")
        public void onClick(View v){
            switch (v.getId()){
                case R.id.follow:
//                    cardView.setCardBackgroundColor(R.color.white);
                    switch (follow_text.getText().toString()){
                        case "关 注":
                            follow_text.setText("已关注");
                            follow_text.setBackgroundResource(R.color.light_gray);
                            break;
                        case "已关注":
                            follow_text.setText("关 注");
                            follow_text.setBackgroundResource(R.color.maintheme);
                            break;
                    }

                    break;
//                case R.id.transmit:


            }
        }
    }

//    public void transmit(){
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_TEXT, "这是我想要分享的内容");
//        shareIntent.setType("text/plain");
//
//// 验证系统中是否存在能够接受这个 Intent 的应用
//        if (shareIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(Intent.createChooser(shareIntent, "分享到："));
//        }
//    }

    public MyAdapter(ArrayList<MyItem> items) {
//        PublicData.mItems = items;
        Log.e("temp","bbbbbbbbbbbbbbbbbbbbbbbb");
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_post, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyItem currentItem = PublicData.mItems.get(position);
//        intent = new Intent(holder.itemView.getContext(),DetailedPostActivity.class);
//        intent.putExtra("profile_img_src",mItems.get(holder.getLayoutPosition()).getMprofileImgResource());
//        intent.putExtra("img_src",mItems.get(position).getImageResource());
//        intent.putExtra("title",mItems.get(position).getTitle());
//        intent.putExtra("author",mItems.get(position).getAuthor());
//        intent.putExtra("date",mItems.get(position).getDate());
//        Toast toast = new Toast(holder.itemView.getContext());
//        toast.setText(mItems.get(holder.getLayoutPosition()).getTitle());
//        toast.show();
        holder.up.setOnClickListener(view -> {
            if (!(PublicData.mItems.get(holder.getLayoutPosition()).up_not)) {
                PublicData.mItems.get(holder.getLayoutPosition()).up_not = true;
                PublicData.mItems.get(holder.getLayoutPosition()).mPraise += 1;
                holder.praise_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getPraise()+"");
                holder.up.setImageResource(R.drawable.up_ed);
            } else if (PublicData.mItems.get(holder.getLayoutPosition()).up_not) {
                PublicData.mItems.get(holder.getLayoutPosition()).up_not = false;
                PublicData.mItems.get(holder.getLayoutPosition()).mPraise -= 1;
                holder.praise_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getPraise()+"");
                holder.up.setImageResource(R.drawable.up);
            }

        });

        holder.collect.setOnClickListener(view -> {
            if (!(PublicData.mItems.get(holder.getLayoutPosition()).collect_not)) {
                PublicData.mItems.get(holder.getLayoutPosition()).collect_not = true;
                PublicData.mItems.get(holder.getLayoutPosition()).mCollect += 1;
                holder.collect_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getmCollect()+"");
                holder.collect.setImageResource(R.drawable.collect_ed);
            } else if (PublicData.mItems.get(holder.getLayoutPosition()).collect_not) {
                PublicData.mItems.get(holder.getLayoutPosition()).collect_not = false;
                PublicData.mItems.get(holder.getLayoutPosition()).mCollect -= 1;
                holder.collect_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getmCollect()+"");
                holder.collect.setImageResource(R.drawable.collect);
            }

        });

        holder.transmit.setOnClickListener(view -> {


            if (!(PublicData.mItems.get(holder.getLayoutPosition()).transmit_not)) {
                PublicData.mItems.get(holder.getLayoutPosition()).transmit_not = true;
                PublicData.mItems.get(holder.getLayoutPosition()).mTransmit += 1;
                holder.transmit_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getmTransmit()+"");
                holder.transmit.setImageResource(R.drawable.transmit_ed);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, PublicData.mItems.get(holder.getLayoutPosition()).getTitle()+"");
                shareIntent.setType("text/plain");

// 验证系统中是否存在能够接受这个 Intent 的应用
                if (shareIntent.resolveActivity(holder.itemView.getContext().getPackageManager()) != null) {
                    holder.itemView.getContext().startActivity(Intent.createChooser(shareIntent, "分享到："));
                }
            } else if (PublicData.mItems.get(holder.getLayoutPosition()).transmit_not) {
                PublicData.mItems.get(holder.getLayoutPosition()).transmit_not = false;
                PublicData.mItems.get(holder.getLayoutPosition()).mTransmit -= 1;
                holder.transmit_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getmTransmit()+"");
                holder.transmit.setImageResource(R.drawable.transmit);
            }
        });

//        holder.down.setOnClickListener(view -> {
//            if (!(PublicData.mItems.get(holder.getLayoutPosition()).down_not)) {
//                PublicData.mItems.get(holder.getLayoutPosition()).down_not = true;
//                PublicData.mItems.get(holder.getLayoutPosition()).mPraise -= 1;
//                holder.praise_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getPraise()+"");
//                holder.down.setImageResource(R.drawable.down_ed);
//            } else if (PublicData.mItems.get(holder.getLayoutPosition()).down_not) {
//                PublicData.mItems.get(holder.getLayoutPosition()).down_not = false;
//                PublicData.mItems.get(holder.getLayoutPosition()).mPraise += 1;
//                holder.praise_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getPraise()+"");
//                holder.down.setImageResource(R.drawable.down);
//            }

//        });

        holder.itemView.setOnClickListener(view -> {
//            Toast toast = new Toast(holder.itemView.getContext());
//            toast.setText(holder.getLayoutPosition() + "");
//            toast.show();
            intent = new Intent(holder.itemView.getContext(),DetailedPostActivity.class);
            intent.putExtra("post_order",holder.getLayoutPosition());

//            intent.putExtra("profile_img_src",PublicData.mItems.get(holder.getLayoutPosition()).getMprofileImgResource());
//            intent.putExtra("img_src",PublicData.mItems.get(holder.getLayoutPosition()).getImageResource());
//            intent.putExtra("title",PublicData.mItems.get(holder.getLayoutPosition()).getTitle());
//            intent.putExtra("content",PublicData.mItems.get(holder.getLayoutPosition()).getContent());
//            intent.putExtra("author",PublicData.mItems.get(holder.getLayoutPosition()).getAuthor());
//            intent.putExtra("date",PublicData.mItems.get(holder.getLayoutPosition()).getDate());
            holder.itemView.getContext().startActivity(intent);


        });

        holder.profile_img.setOnClickListener(view -> {
            intent = new Intent(view.getContext(),SpacePageActivity.class);
            holder.itemView.getContext().startActivity(intent);

        });
//        holder.mImageView.setMaxHeight(1/3*PublicData.height_screen);
//        holder.profile_img.setImageBitmap(currentItem.getMprofileImgResource());
        holder.profile_img.setImageBitmap(PublicData.user.profileImgResource);
        holder.praise_num.setText(PublicData.mItems.get(holder.getLayoutPosition()).getPraise()+"");
        if (!(PublicData.mItems.get(holder.getLayoutPosition()).up_not)) {
            holder.up.setImageResource(R.drawable.up);
        } else if (PublicData.mItems.get(holder.getLayoutPosition()).up_not) {
            holder.up.setImageResource(R.drawable.up_ed);
        }

//        if (!(PublicData.mItems.get(holder.getLayoutPosition()).down_not)) {
//            holder.down.setImageResource(R.drawable.down);
//        } else if (PublicData.mItems.get(holder.getLayoutPosition()).up_not) {
//            holder.down.setImageResource(R.drawable.down_ed);
//        }





        Bitmap originalBitmap = currentItem.getImageResource().get(0);
        if(originalBitmap != null){
            int maxWidth = 800; // set the maximum width to 500 pixels
            int maxHeight = 800; // set the maximum height to 500 pixels
            int width = originalBitmap.getWidth();
            int height = originalBitmap.getHeight();
            float ratio = Math.min((float) maxWidth / width, (float) maxHeight / height);
            int newWidth = (int) (width * ratio);
            int newHeight = (int) (height * ratio);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, false);

            holder.mImageView.setImageBitmap(resizedBitmap);
        }

        holder.mTitleTextView.setText(currentItem.getTitle());
        holder.mAuthorTextView.setText(currentItem.getAuthor());
        holder.mDateTextView.setText(currentItem.getDate());

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
        return PublicData.mItems.size();
    }
}