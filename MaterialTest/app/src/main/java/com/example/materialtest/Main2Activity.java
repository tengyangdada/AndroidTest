package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {



    public static void actionStart(Context context, Fruit data) {
        Intent intent = new Intent(context, Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtra("data", bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
