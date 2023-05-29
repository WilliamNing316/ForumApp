package com.example.ngm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<SliderItem> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context,ArrayList<SliderItem> sliderItems) {
        this.context = context;
        this.mSliderItems = sliderItems;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);

        viewHolder.textViewDescription.setText("");
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
//        int maxWidth = 800; // set the maximum width to 500 pixels
//        int maxHeight = 800; // set the maximum height to 500 pixels
//        int width = mSliderItems.get(position).getImg_bitmap().getWidth();
//        int height = mSliderItems.get(position).getImg_bitmap().getHeight();
//        float ratio = Math.min((float) maxWidth / width, (float) maxHeight / height);
//        int newWidth = (int) (width * ratio);
//        int newHeight = (int) (height * ratio);
//        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mSliderItems.get(position).getImg_bitmap(), newWidth, newHeight, false);
//
//        viewHolder.imageViewBackground.setImageBitmap(resizedBitmap);
        viewHolder.imageViewBackground.setImageBitmap(mSliderItems.get(position).getImg_bitmap());
//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getImageUrl())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(viewHolder.itemView.getContext(),CheckPhotoActivity.class);
                intent.putExtra("post_order",DetailedPostActivity.post_order);
                intent.putExtra("current_page",position);
                if(PublicData.currentActivity != CheckPhotoActivity.class.hashCode()){
                    viewHolder.itemView.getContext().startActivity(intent);
                    PublicData.currentActivity = CheckPhotoActivity.class.hashCode();
                }


//                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

}