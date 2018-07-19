package com.example.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageFragment messageFragment;
    private ContactsFragment contactsFragment;
    private View messageLayout;
    private View contactsLayout;
    private TextView messageText;
    private TextView contactsText;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        fragmentManager=getFragmentManager();
        setTabSelection(0);
    }
    private void initViews(){
        messageLayout=findViewById(R.id.message_layout);
        contactsLayout=findViewById(R.id.contacts_layout);
        messageText=(TextView) findViewById(R.id.message_text);
        contactsText=(TextView) findViewById(R.id.contacts_text);
        messageLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.message_layout:
                setTabSelection(0);
                break;
            case R.id.contacts_layout:
                setTabSelection(1);
                break;
            default:
                break;
        }
    }
    private void setTabSelection(int index){
        clearSelection();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index){
            case 0:
                messageText.setTextColor(Color.WHITE);
                if(messageFragment==null){
                    messageFragment=new MessageFragment();
                    transaction.add(R.id.content,messageFragment);
                }else{
                    transaction.show(messageFragment);
                }break;
            case 1:
                contactsText.setTextColor(Color.WHITE);
                if(contactsFragment==null){
                    contactsFragment=new ContactsFragment();
                    transaction.add(R.id.content,contactsFragment);
                }else{
                    transaction.show(messageFragment);
                }break;
        }
        transaction.commit();
    }

    private void clearSelection(){
        messageText.setTextColor(Color.parseColor("#82858b"));
        contactsText.setTextColor(Color.parseColor("#82858b"));
    }
    private void hideFragments(FragmentTransaction transaction){
        if(messageFragment!=null){
            transaction.hide(messageFragment);
        }
        if (contactsFragment!=null){
            transaction.hide(contactsFragment);
        }
    }
}
