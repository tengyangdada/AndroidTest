package com.example.contentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button music,photo,video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      photo=(Button)findViewById(R.id.photo);
        music=(Button)findViewById(R.id.music);
        video=(Button)findViewById(R.id.video);
        photo.setOnClickListener(this);
        video.setOnClickListener(this);
        music.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.music:
                Intent intent=new Intent(this,MusicActivity.class);
                startActivity(intent);
                break;
            case R.id.video:
                Intent intent1=new Intent(this,VideoActivity.class);
                startActivity(intent1);
                break;
            case R.id.photo:
                Intent intent2=new Intent(this,PhotoActivity.class);
                startActivity(intent2
                );
                break;
        }
    }
}