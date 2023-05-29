package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MessageMainActivity extends AppCompatActivity {

    private Intent intent;

    private ImageView follow_followed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);

        follow_followed = findViewById(R.id.follow_followed);
        follow_followed.setOnClickListener(this::onClick);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.follow_followed:
                intent = new Intent(this,MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
}