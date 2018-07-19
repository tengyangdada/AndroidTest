package com.example.contentprovider;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    Button getImage;
    Button getVideo;
    Button getAudio;
    Button getFiles;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        myTextView = (TextView) this.findViewById(R.id.myTextView);

        getAudio = ((Button) this.findViewById(R.id.getAudio));
        getAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] projection = {MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE};
                String orderBy = MediaStore.Audio.Media.DISPLAY_NAME;
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                getContentProvider(uri, projection, orderBy);
            }
        });

        getVideo = ((Button) this.findViewById(R.id.getVideo));
        getVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] projection = {MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DATA};
                String orderBy = MediaStore.Video.Media.DISPLAY_NAME;
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                getContentProvider(uri, projection, orderBy);
            }
        });

        getImage = ((Button) this.findViewById(R.id.getImage));
        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] projection = {MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATA};
                String orderBy = MediaStore.Images.Media.DISPLAY_NAME;
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                getContentProvider(uri, projection, orderBy);
            }
        });


        getFiles = ((Button) this.findViewById(R.id.getFiles));
        getFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 获取ContentProvider
     *
     * @param projection
     * @param orderBy
     */
    public void getContentProvider(Uri uri, String[] projection, String orderBy) {
        List<HashMap<String, String>> listImage = new ArrayList<HashMap<String, String>>();
        Cursor cursor = getContentResolver().query(uri, projection, null, null, orderBy);
        if (null == cursor) {
            return;
        }
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < projection.length; i++) {
                map.put(projection[i], cursor.getString(i));
                System.out.println(projection[i] + ":::::::" + cursor.getString(i) + "\n");
            }
            listImage.add(map);
        }
        myTextView.setText(listImage.toString());
    }

}