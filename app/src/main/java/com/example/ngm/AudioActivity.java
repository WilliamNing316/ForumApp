package com.example.ngm;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngm.R;


public class AudioActivity extends Activity implements OnTouchListener{
    private AlertDialog mAudioDialog;
    private TextView mAudioNotify;
    private ImageView mVoiceState;
    private TextView mAudioSend;
    private boolean mCancelSend;
    private final String TAG = "AudioSendActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        mAudioSend = (TextView) findViewById(R.id.audio_send);
        mAudioSend.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.audio_send) {
            float startY = 0;
            float endY = 0;
            boolean send = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = event.getY();
                    Log.d(TAG, "audioButtonDown() MotionEvent.ACTION_DOWN");
                    showAudioDialog();
                    break;
                case MotionEvent.ACTION_UP:
                    endY = event.getY();
                    hideAudioDialog();
                    send = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveY = event.getY();
                    int instance = (int) Math.abs((moveY - startY));
                    Log.d(TAG, "--action move--instance:"+instance);
                    if (instance > 100) {
                        changeAudioDialogCancel(true);
                    } else {
                        changeAudioDialogCancel(false);
                    }
                    break;
                default:
                    break;
            }

            return true;
        }
        return false;
    }


    public void showAudioDialog() {
        if (mAudioDialog == null) {
            mAudioDialog = new AlertDialog.Builder(this).create();
            mAudioDialog.show();
            mAudioDialog.getWindow().setContentView(R.layout.audio_dialog);
            mAudioDialog.getWindow().setGravity(Gravity.CENTER);
            mAudioNotify = (TextView) mAudioDialog
                    .findViewById(R.id.audio_nofity);
            mVoiceState = (ImageView) mAudioDialog
                    .findViewById(R.id.voice_state);

        } else if (!mAudioDialog.isShowing()) {
            mAudioDialog.show();
        }
        mVoiceState.setImageResource(R.drawable.move_audio);
        AnimationDrawable drawable = (AnimationDrawable) mVoiceState
                .getDrawable();
        drawable.start();
    }

    private void hideAudioDialog() {
        if (mAudioDialog != null && mAudioDialog.isShowing()) {
            mAudioDialog.dismiss();
        }
    }


    private void changeAudioDialogCancel(boolean cancel) {
        if (mCancelSend == cancel)
            return;
        if (mAudioDialog != null && mAudioDialog.isShowing()
                && mAudioNotify != null) {
            if (cancel) {
                mVoiceState.setImageResource(R.drawable.exit);
                mAudioNotify.setTextColor(Color.RED);
                mAudioNotify.setText("ahozhi");
            } else {
                mAudioNotify.setTextColor(Color.WHITE);
                mAudioNotify.setText("huhuh");
                mVoiceState.setImageResource(R.drawable.move_audio);
                AnimationDrawable drawable = (AnimationDrawable) mVoiceState
                        .getDrawable();
                drawable.start();
            }
        }
        mCancelSend = cancel;
    }
}
