package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.ngm.databinding.FragmentHomeBinding;

public class DraftBoxActivity extends AppCompatActivity {




    private DraftAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_box);
        recyclerView = findViewById(R.id.drafts_recycle);
        Log.e("huhu",PublicData.drafts+"");
        adapter = new DraftAdapter(PublicData.drafts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}