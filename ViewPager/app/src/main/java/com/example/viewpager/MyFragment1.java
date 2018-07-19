package com.example.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyFragment1 extends Fragment {

    private List<Fruit> fruitList ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page1, null);


        fruitList = new ArrayList<>();
        view();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener1() {
            @Override
            public void onItemClick(View view, int position, String str) {

                    Main2Activity.actionStart(getActivity(), fruitList.get(position));
                    //Main3Activity.actionStart1(getActivity(), fruitList.get(position));

            }
        });

        return view;
    }

    private void view() {
        int x = 1;
        for (int i = 0; i < 15; i++) {
            Fruit q = new Fruit("联系人" + x + "号", R.drawable.meizi6);
            fruitList.add(q);
            x++;
        }
    }
}
