package com.example.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MyFragment3 extends Fragment {
    String b, c, d, e, f, g, h, b1, b2, b3;
    String[] a = {b, c, d, e, f, g, h, b1, b2, b3};
    private TextView textview;
    public static final int UPDATA_TEXT = 1;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page3, null);

        listView = (ListView) view.findViewById(R.id.list_view);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://gank.io/api/data/Android/10/1")
                            .build();
                    Response response = client.newCall(request).execute();
                    String JSONString = response.body().string();

                    Gson gson = new Gson();

                    Result r = gson.fromJson(JSONString, Result.class);

                    for (int i = 0; i < 10; i++) {
                        a[i] = r.results.get(i).desc;
                        Log.e("TAG", a[i]);
                    }
                    Message message = new Message();
                    message.what = UPDATA_TEXT;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return view;
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATA_TEXT:
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getContext(), android.R.layout.simple_list_item_1, a);
                    listView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };
}
