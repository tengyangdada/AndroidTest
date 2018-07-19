package com.example.broadcasts;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滕扬 on 2018/4/23.
 */

public class ActivityCollector {
    public static List<Activity>activities=new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finshAll(){
        for(Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
