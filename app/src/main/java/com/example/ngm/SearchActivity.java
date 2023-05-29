package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.android.material.snackbar.Snackbar;

public class SearchActivity extends AppCompatActivity {

    private androidx.appcompat.widget.SearchView searchView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search);

//        frameLayout = findViewById(R.id.search_result);
        // 设置搜索文本监听
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // 当点击搜索按钮时触发该方法
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Snackbar.make(frameLayout, "搜索内容===" + query, Snackbar.LENGTH_SHORT).show();
//
//                //伪搜索
//                searchView.setVisibility(View.VISIBLE);
//
//                //清除焦点，收软键盘
//                //mSearchView.clearFocus();
//
//                return false;
//            }
//
//            // 当搜索内容改变时触发该方法
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //do something
//                //当没有输入任何内容的时候清除结果，看实际需求
//                AbsActionBarView mSearchResult;
//                if (TextUtils.isEmpty(newText)) mSearchResult.setVisibility(View.INVISIBLE);
//                return false;
//            }
//        });

    }
}