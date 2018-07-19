package com.example.musicopen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (Button) findViewById(R.id.start);
        b = (Button) findViewById(R.id.pause);
        c = (Button) findViewById(R.id.stop);
a.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MyService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }
});




    public void pauseMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MyService.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void stopMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MyService.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
        //或者是直接如下调用
        //Intent intent = new Intent(this, MyService.class);
        //stopService(intent);
    }
}
}