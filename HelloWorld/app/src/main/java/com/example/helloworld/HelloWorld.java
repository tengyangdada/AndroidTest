package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends AppCompatActivity {
    private TextView textView;
    private Button button;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);
    }


    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            count++;
            textView.setText(count);
        }
    }
}
