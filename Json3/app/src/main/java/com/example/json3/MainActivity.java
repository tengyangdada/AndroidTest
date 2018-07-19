package com.example.json3;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    String TAG = "Json message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detectJSON();
    }

    private void detectJSON() {
        String str = "{"+

                "\"日期\" : \"2011-06-06\","+

                //Like 是 JSONObject
                "\"Like\" : {"+
                "\"Name\" : \"加内特\","+
                "\"Height\" : \"2.11cm\","+
                "\"Age\" : 35"+
                "},"+

                //LikeList 就是一个 JSONObject
                "\"LikeList\":" +
                "{\"List\": " +
                "["+
                //这里也是JSONObject
                "{"+
                "\"Name\" : \"Rose\","+
                "\"Height\" : \"190cm\","+
                "\"Age\" : 23"+
                "},"+
                //这里也是JSONObject
                "{"+
                "\"Name\" : \"科比\","+
                "\"Height\" : \"198cm\","+
                "\"Age\" : 33"+
                "}"+
                "]"+
                "}"+
                "}";

        try {
            JSONObject dataJson = new JSONObject(str);
            Log.d(TAG, dataJson.getString("日期"));

            JSONObject nbaJson = dataJson.getJSONObject("Like");

            Log.d(TAG, nbaJson.getString("Name"));
            Log.d(TAG, nbaJson.getString("Height"));
            Log.d(TAG, nbaJson.get("Age").toString());

            JSONObject listJson = dataJson.getJSONObject("LikeList");
            JSONArray arrayJson = listJson.getJSONArray("List");

            for(int i=0;i<arrayJson.length();i++) {

                JSONObject tempJson = arrayJson.optJSONObject(i);

                Log.d(TAG, tempJson.getString("Name"));
                Log.d(TAG, tempJson.getString("Height"));
                Log.d(TAG, tempJson.getString("Age").toString());
            }


        } catch (JSONException e) {
            System.out.println("Something wrong...");
            e.printStackTrace();
        }
    }
}