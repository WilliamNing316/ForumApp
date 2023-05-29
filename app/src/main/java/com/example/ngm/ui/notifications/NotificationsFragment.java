package com.example.ngm.ui.notifications;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ngm.Edit_Info_Activity;
import com.example.ngm.PublicData;
import com.example.ngm.R;
import com.example.ngm.SettingActivity;
import com.example.ngm.SpacePageActivity;
import com.example.ngm.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private int width = 0;
    private int height = 0;
    private Intent intent;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;


//
//        binding.backgroundImg.getLayoutParams().height = 7*height/24;
//        binding.backgroundImg.getLayoutParams().width = width;
        binding.userAccount.setText("ID: "+ PublicData.account);
        binding.nickName.setText(PublicData.user.username);
        binding.profileImage.setImageBitmap(PublicData.user.profileImgResource);


        binding.profileImage.getLayoutParams().width = width/4;
        binding.profileImage.getLayoutParams().height = width/4;
        binding.followersBar.getLayoutParams().width = width/5;
        binding.followingBar.getLayoutParams().width = width/5;
        binding.postBar.getLayoutParams().width = width/5;
        binding.followersBar.getLayoutParams().height = 2*width/5;
        binding.followingBar.getLayoutParams().height = 2*width/5;
        binding.postBar.getLayoutParams().height = 2*width/5;
        binding.infomations.getLayoutParams().height = 7*height/24;
        binding.followBtn.getLayoutParams().height = width/15;
        binding.followBtn.getLayoutParams().width = width/3;
        binding.space.setOnClickListener(this::onClick);

        binding.editBtn.getLayoutParams().height = width/15;
        binding.editBtn.getLayoutParams().width = width/15;
        binding.editBtn.setOnClickListener(this::onClick);

        binding.scroll.getLayoutParams().height = 17*height/24 - 100;
//        binding.skin.getLayoutParams().height = width/12;
//        binding.skin.getLayoutParams().width = width/12;
//        binding.following1.getLayoutParams().height = width/24;
//        binding.following1.getLayoutParams().width = width/24;

        binding.settings.setOnClickListener(this::onClick);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.space:
                intent = new Intent(this.getContext(), SpacePageActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_btn:
                intent = new Intent(this.getContext(), Edit_Info_Activity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(this.getContext(), SettingActivity.class);
                startActivity(intent);
                break;

        }
    }
}