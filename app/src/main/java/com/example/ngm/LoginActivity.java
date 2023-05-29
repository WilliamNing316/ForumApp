package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText account;
    EditText password;
    Button signup;
    Button signin;
    TextView forget;
    String account_num;
    String pass_num;
    Intent intent;
    String jsonString;
    Boolean log_state;

    String temp_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        forget = findViewById(R.id.forget_pass);

        signup.setOnClickListener(this::onClick);
        signin.setOnClickListener(this::onClick);
        forget.setOnClickListener(this::onClick);
        intent = getIntent();

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(!intent.getBooleanExtra("logout",false)){
            log_state = sharedPreferences.getBoolean("log_state",true);
            if(log_state)
                auto_login();
        }
        else {
            sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putBoolean("log_state", false);
            editor.apply();
        }




//        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
//            public void onSwipeTop() {
//            }
//
//            public void onSwipeRight() {
//                if (count == 0) {
//                    imageView.setImageResource(R.drawable.good_night_img);
//                    textView.setText("Night");
//                    count = 1;
//                } else {
//                    imageView.setImageResource(R.drawable.good_morning_img);
//                    textView.setText("Morning");
//                    count = 0;
//                }
//            }
//
//            public void onSwipeLeft() {
//                if (count == 0) {
//                    imageView.setImageResource(R.drawable.good_night_img);
//                    textView.setText("Night");
//                    count = 1;
//                } else {
//                    imageView.setImageResource(R.drawable.good_morning_img);
//                    textView.setText("Morning");
//                    count = 0;
//                }
//            }
//
//            public void onSwipeBottom() {
//            }
//
//        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.signup:
                sign_up();
                break;
            case R.id.signin:
                sign_in();
                break;
            case R.id.forget_pass:
                Toast toast = new Toast(this);
                toast.setText("功能尚未实现");
                toast.show();
                break;
        }
    }

    public void sign_up(){
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        account_num = account.getText().toString();
        pass_num = password.getText().toString();
        editor.putString("account", account_num);
        editor.putString("password", pass_num);
        editor.apply();
        try {
            // Create a JSON object
            JSONObject jsonObject = new JSONObject();
            // Add key-value pairs to the JSON object
            jsonObject.put("account", account_num);
            jsonObject.put("password", pass_num);
            // Convert the JSON object to a string
            jsonString = jsonObject.toString();
            // Use the jsonString as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }
        upload_data(jsonString);

    }

    public Boolean check(String acct, String pass){
        try {
            // Create a JSON object
            JSONObject jsonObject = new JSONObject();
            // Add key-value pairs to the JSON object
            jsonObject.put("account", acct);
            jsonObject.put("password", pass);
            // Convert the JSON object to a string
            jsonString = jsonObject.toString();
            // Use the jsonString as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }
        temp_account = acct;
        check_data(jsonString);
        return false;
    }

    protected void check_data(String data){
        FormBody.Builder builder = new FormBody.Builder()
                .add("sign_in", data);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(PublicData.SERVER_URL + "/sign_in")
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("返回值",e.toString());
                System.out.println("保存失败XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//                Looper.prepare();
//                Toast toast= new Toast(getBaseContext());
//                toast.setText("账户或密码错误");
//                toast.show();
//                Looper.loop();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("返回值",responseData);
                if (responseData.equals("1")) { // 登录成功
//                                    Global.user_id = my_info.message;
                    System.out.println("保存成功**************************************************");
                    PublicData.account = temp_account;
                    Looper.prepare();
                    intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }
                else {
                    System.out.println("保存失败XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    Looper.prepare();
                    Toast toast= new Toast(getBaseContext());
                    toast.setText("账户或密码错误");
                    toast.show();
                    Looper.loop();

                }
            }
        });
    }

    protected void upload_data(String data){
        FormBody.Builder builder = new FormBody.Builder()
                .add("sign_up", data);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(PublicData.SERVER_URL + "/sign_up")
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("返回值",e.toString());
                System.out.println("保存失败XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//                Looper.prepare();
//                Toast toast=Toast.makeText(TestActivity.this, "数据上传失败", Toast.LENGTH_SHORT);
//                toast.show();
//                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("返回值",responseData);
                if (responseData.equals("1")) { // 登录成功
//                                    Global.user_id = my_info.message;
                    System.out.println("保存成功**************************************************");
                    Looper.prepare();
                    Toast toast= new Toast(getBaseContext());
                    toast.setText("注册成功");
                    toast.show();
                    Looper.loop();
                    return;

                }
                else {
                    System.out.println("保存失败XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

                }
            }
        });
    }
    public void auto_login(){

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedAccount = sharedPreferences.getString("account", "");
        String savedPassword = sharedPreferences.getString("password", "");
        PublicData.account = savedAccount;
        Log.e("saved:",savedAccount + "" + savedPassword);
        if(check(savedAccount,savedPassword)){
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void sign_in(){
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("log_state", true);
        editor.apply();
        account_num = account.getText().toString();
        pass_num = password.getText().toString();
        check(account_num,pass_num);

//        if(check(account_num,pass_num)){
//            intent = new Intent(this,MainActivity2.class);
//            startActivity(intent);
//            finish();
//        }

    }
}
