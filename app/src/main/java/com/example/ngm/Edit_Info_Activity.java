package com.example.ngm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.ngm.ui.home.HomeFragment;
import com.example.ngm.ui.notifications.NotificationsFragment;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Info_Activity extends AppCompatActivity {

    private TextView nickname;
    private TextView gender;
    private TextView birthday;
    private TextView identity;
    private TextView brief_intro;
    private CircleImageView profile;
    private TextView camera;
    private TextView album;
    private TextView cancel;

    private ImageButton nickname_edit;
    private ImageButton gender_edit;
    private ImageButton birthday_edit;
    private ImageButton identity_edit;
    private ImageButton brief_intro_edit;
    private ImageButton profile_edit;

    private ImageButton back;

    private int temp_gender;
    private int temp_identity;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        bindViews();
        initialize();

    }
    public void select_picture(){
        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected() // 把已选的图片传入默认选中。
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this,getRequestedOrientation()); // 打开相册
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.getRequestedOrientation() && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images_path = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);

            String temp_pic_path;
            Bitmap bitmap;
            Log.e("images", images_path + "");
            temp_pic_path = images_path.get(0);
            String filePath = temp_pic_path;
            bitmap = BitmapFactory.decodeFile(filePath);
            PublicData.user.profileImgResource = bitmap;
            profile.setImageBitmap(bitmap);
//            show_pics(this.getContext(),images_path);


        }
    }
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.nick_name_edit:
                intent = new Intent(Edit_Info_Activity.this, HomeFragment.class);
                final EditText nick_name = new EditText(this);
                new AlertDialog.Builder(this).setTitle("昵称")
                        .setView(nick_name)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String input = nick_name.getText().toString();
                                if (input.equals("")) {
                                    Toast.makeText(getApplicationContext(), "昵称不能为空" + input, Toast.LENGTH_LONG).show();
                                }
                                else {
                                    String NewNickname = nick_name.getText().toString();
                                    nickname.setText(NewNickname);
                                    PublicData.user.username = NewNickname;
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                FormBody.Builder builder = new FormBody.Builder()
//                                                        .add("user_code", user.ID.toString())
//                                                        .add("change", "nickname")
//                                                        .add("content", NewNickname);
//                                                OkHttpClient client = new OkHttpClient();
//                                                Request request = new Request.Builder()
//                                                        .url(Global.SERVER_URL + "/user")
//                                                        .post(builder.build())
//                                                        .build();
//                                                client.newCall(request).enqueue(new Callback() {
//                                                    @Override
//                                                    public void onFailure(Call call, IOException e) {
//                                                        Log.e("返回值", e.toString());
//                                                    }
//
//                                                    @Override
//                                                    public void onResponse(Call call, Response response) throws IOException {
//                                                        //Gson gson = new Gson();
//                                                        String responseData = response.body().string();
//                                                        System.out.println("---------------------");
//                                                        System.out.println(responseData);
//                                                        Log.e("返回值", responseData);
//                                                        user.nickname = NewNickname;
//                                                        nickname.post(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//                                                                update_info();
//                                                            }
//                                                        });
//                                                    }
//                                                });
//
//
//                                            }catch (Exception e)
//                                            {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }).start();


//                                    update_info();
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                send_bundle();
//                intent.putExtras(bundle);
//                update_info();
                break;

            case R.id.back:
                intent = new Intent(Edit_Info_Activity.this, MainActivity.class);
                send_bundle();
//                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.gender_edit:
                intent = new Intent(Edit_Info_Activity.this, HomeFragment.class);
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(Edit_Info_Activity.this);

                final String items[] = {"男", "女","保密"};

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("性别")//设置对话框的标题
                        .setSingleChoiceItems(items, temp_gender, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                temp_gender = which;
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                user.gender = items[temp_gender];

//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            FormBody.Builder builder = new FormBody.Builder()
//                                                    .add("user_code", user.ID.toString())
//                                                    .add("change", "gender")
//                                                    .add("content", user.gender);
//                                            OkHttpClient client = new OkHttpClient();
//                                            Request request = new Request.Builder()
//                                                    .url(Global.SERVER_URL + "/user")
//                                                    .post(builder.build())
//                                                    .build();
//                                            client.newCall(request).enqueue(new Callback() {
//                                                @Override
//                                                public void onFailure(Call call, IOException e) {
//                                                    Log.e("返回值", e.toString());
//                                                }
//
//                                                @Override
//                                                public void onResponse(Call call, Response response) throws IOException {
//                                                    //Gson gson = new Gson();
//                                                    String responseData = response.body().string();
//                                                    System.out.println("---------------------");
//                                                    System.out.println(responseData);
//                                                    Log.e("返回值", responseData);
//
//                                                    nickname.post(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//                                                            update_info();
//                                                        }
//                                                    });
//                                                }
//                                            });
//
//
//                                        }catch (Exception e)
//                                        {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }).start();

//                                update_info();
                                dialog.dismiss();

                            }
                        }).create();
                dialog.show();
                send_bundle();
//                intent.putExtras(bundle);
                break;

            case R.id.identity_edit:
                intent = new Intent(Edit_Info_Activity.this, HomeFragment.class);
//                AlertDialog.Builder identity_dialog = new AlertDialog.Builder(EditInfoActivity.this);

                final String identities[] = {"本科生", "研究生","教工","校友"};

                AlertDialog identity_dialog = new AlertDialog.Builder(this)
                        .setTitle("性别")//设置对话框的标题
                        .setSingleChoiceItems(identities, temp_identity, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                temp_identity = which;
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                user.identity = identities[temp_identity];
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            FormBody.Builder builder = new FormBody.Builder()
//                                                    .add("user_code", user.ID.toString())
//                                                    .add("change", "identity")
//                                                    .add("content", user.identity);
//                                            OkHttpClient client = new OkHttpClient();
//                                            Request request = new Request.Builder()
//                                                    .url(Global.SERVER_URL + "/user")
//                                                    .post(builder.build())
//                                                    .build();
//                                            client.newCall(request).enqueue(new Callback() {
//                                                @Override
//                                                public void onFailure(Call call, IOException e) {
//                                                    Log.e("返回值", e.toString());
//                                                }
//
//                                                @Override
//                                                public void onResponse(Call call, Response response) throws IOException {
//                                                    //Gson gson = new Gson();
//                                                    String responseData = response.body().string();
//                                                    System.out.println("---------------------");
//                                                    System.out.println(responseData);
//                                                    Log.e("返回值", responseData);
//
//                                                    nickname.post(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//                                                            update_info();
//                                                        }
//                                                    });
//                                                }
//                                            });
//
//
//                                        }catch (Exception e)
//                                        {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }).start();

                                dialog.dismiss();

                            }
                        }).create();
                identity_dialog.show();
                send_bundle();
//                intent.putExtras(bundle);
                break;

            case R.id.birthday_edit:
//                initCalendar();
                break;

            case R.id.profile_image_edit:
                select_picture();
//                change_profile();




                break;

        }

    }

    private void send_bundle(){
//        bundle = new Bundle();
//        bundle.putSerializable("user",user);
    }

    public void initialize(){
        nickname.setText(PublicData.user.username);
        profile.setImageBitmap(PublicData.user.profileImgResource);
    }
    public void bindViews(){

        nickname = findViewById(R.id.nick_name);
        gender = findViewById(R.id.gender);
        birthday = findViewById(R.id.birthday);
        identity = findViewById(R.id.identity);
        brief_intro = findViewById(R.id.brief_intro);
        back = findViewById(R.id.back);
        profile = findViewById(R.id.profile_image);

        nickname_edit = findViewById(R.id.nick_name_edit);
        gender_edit = findViewById(R.id.gender_edit);
        birthday_edit = findViewById(R.id.birthday_edit);
        identity_edit = findViewById(R.id.identity_edit);
        brief_intro_edit = findViewById(R.id.brief_intro_edit);
        profile_edit = findViewById(R.id.profile_image_edit);

        nickname_edit.setOnClickListener(this::onClick);
        gender_edit.setOnClickListener(this::onClick);
        birthday_edit.setOnClickListener(this::onClick);
        identity_edit.setOnClickListener(this::onClick);
        brief_intro_edit.setOnClickListener(this::onClick);
        back.setOnClickListener(this::onClick);
        profile_edit.setOnClickListener(this::onClick);

//        displayImage("http://192.168.1.9:8081/qq_result001/");
    }

}