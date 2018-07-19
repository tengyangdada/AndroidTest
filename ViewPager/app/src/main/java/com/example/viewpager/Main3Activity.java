package com.example.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {


    public static void actionStart1(Context context, Fruit data) {
        Intent intent = new Intent(context, Main3Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data1", data);
        intent.putExtra("data1", bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Fruit fruit = (Fruit) getIntent().getBundleExtra("data1").getSerializable("data1");

    }
}
