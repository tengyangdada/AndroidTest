package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;


    public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

        private MediaPlayer mediaPlayer=new MediaPlayer();
        private VideoView videoView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video);

            videoView=(VideoView)findViewById(R.id.video_view);

            Button play1=(Button)findViewById(R.id.play1);
            Button pause1=(Button)findViewById(R.id.pause1);
            Button replay1=(Button)findViewById(R.id.replay1);


            play1.setOnClickListener(this);
            pause1.setOnClickListener(this);
            replay1.setOnClickListener(this);
            //这是获取权限所必要的方法：
            if(ContextCompat.checkSelfPermission(VideoActivity.this, Manifest.permission.
                    WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(VideoActivity.this,new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else{

                initVideoPath();
            }
        }

        private void initVideoPath(){
            File file=new File(Environment.getExternalStorageDirectory(),"video.mp4");
            videoView.setVideoPath(file.getPath());

        }

        //这是获取权限所必要的方法：
        @Override
        public void onRequestPermissionsResult(int requestCode,String[] permissions,
                                               int[] grantResults){
            switch (requestCode){
                case 1:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        initVideoPath();
                    }else{
                        Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                default:
            }
        }

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.play1:
                    if(!videoView.isPlaying()){
                        videoView.start();
                    }break;
                case R.id.pause1:
                    if(videoView.isPlaying()){
                        videoView.pause();
                    }break;
                case R.id.replay1:
                    if(videoView.isPlaying()){
                        videoView.resume();
                    }break;
                default:
                    break;
            }
        }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            if(videoView!=null){
                videoView.suspend();
            }
        }
    }
