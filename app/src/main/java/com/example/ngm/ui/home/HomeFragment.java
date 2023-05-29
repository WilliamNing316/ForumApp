package com.example.ngm.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ngm.MainActivity;
import com.example.ngm.MessageMainActivity;
import com.example.ngm.MyAdapter;
import com.example.ngm.PublicData;
import com.example.ngm.R;
import com.example.ngm.SearchActivity;
import com.example.ngm.databinding.FragmentHomeBinding;
import com.example.ngm.dialog.MoreRankDialog;
import com.example.ngm.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Intent intent;
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private int height;
    private int width;

//    private PostAdapter postAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.posts;
        final ImageView message_view = binding.message;
        message_view.setOnClickListener(this::onClick);
        binding.search.setOnClickListener(this::onClick);
        adapter = new MyAdapter(PublicData.mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;

        binding.morerank.setOnClickListener(this::onClick);



        locationPermissionRequest.launch(new String[] {
//                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
//        postAdapter = new PostAdapter();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext() );
////设置布局管理器
//        binding.imagesList.setLayoutManager(layoutManager);
////设置为垂直布局，这也是默认的
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
////设置Adapter
//        binding.imagesList.setAdapter(normalAdapter);
////设置增加或删除条目的动画
//        binding.imagesList.setItemAnimator( new DefaultItemAnimator());
        return root;
    }


    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                        }
                        Boolean coarseLocationGranted = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                        }
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.

                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                        } else {
                            // No location access granted.
                        }
                    }
            );

    public void onClick(View v){
        switch (v.getId()){
            case R.id.message:
                intent = new Intent(this.getContext(), MessageMainActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                intent = new Intent(this.getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.morerank:
                MoreRankDialog moreRankDialog = new MoreRankDialog(this.getContext(),R.style.more_rank);
                moreRankDialog.show();
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewholder>{
//        private List<String> mDatas;
//        public class viewholder extends RecyclerView.ViewHolder{
////            public final TextView post_text;
//            public viewholder(@NonNull View itemView) {
//                super(itemView);
////                post_text = itemView.findViewById(R.id.post_text);
//            }
//
//        }
//        public PostAdapter(){
//            mDatas = new ArrayList<>();
//        }
//        @Override
//        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_post, parent, false);
//            return new PostAdapter.viewholder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull viewholder holder, int position) {
//            for (int i = 0;i < 10;i ++){
////                holder.post_text.setText("" + i);
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }


//    }
}