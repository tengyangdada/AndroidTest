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

    public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

        private MediaPlayer mediaPlayer=new MediaPlayer();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_music);

            Button play=(Button)findViewById(R.id.play);
            Button pause=(Button)findViewById(R.id.pause);
            Button stop=(Button)findViewById(R.id.stop);


            play.setOnClickListener(this);
            pause.setOnClickListener(this);
            stop.setOnClickListener(this);


            //这是获取权限所必要的方法：
            if(ContextCompat.checkSelfPermission(MusicActivity.this, Manifest.permission.
                    WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MusicActivity.this,new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else{
                initMediaPlayer();
            }
        }


        private void initMediaPlayer(){
            try{
                File file=new File(Environment.getExternalStorageDirectory(),
                        "初见初恋.mp3");
                mediaPlayer.setDataSource(file.getPath());
                mediaPlayer.prepare();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //这是获取权限所必要的方法：
        @Override
        public void onRequestPermissionsResult(int requestCode,String[] permissions,
                                               int[] grantResults){
            switch (requestCode){
                case 1:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        initMediaPlayer();
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
                case R.id.play:
                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                    }break;
                case R.id.pause:
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }break;
                case R.id.stop:
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.reset();
                        initMediaPlayer();
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }