package com.example.login_test.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login_test.R;
import com.example.login_test.common.AsyncImageLoader;
import com.example.login_test.common.StaticVariable;

public class MyCourseListAdapter extends ArrayAdapter<Map>{

    private List<Map> dataSource ;
    private AsyncImageLoader imageLoader = new AsyncImageLoader();
    public MyCourseListAdapter(Activity activity, List<Map> data) {
        super(activity, 0, data);
        dataSource = data;
    }
    
    
    private Map<Integer, View> viewMap = new HashMap<Integer, View>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = this.viewMap.get(position);

        if (rowView == null) {
            Map<String,String> map = dataSource.get(position);
            LayoutInflater inflater = ((Activity) this.getContext())
                    .getLayoutInflater();
            rowView = inflater
                    .inflate(R.layout.mycourse_item, null);

            TextView courseName = (TextView)rowView.findViewById(R.id.courseName);
            ImageView courseCover = (ImageView)rowView.findViewById(R.id.courseCover);
            courseName.setText(map.get("courseName").toString());
            String url = StaticVariable.IMG_ROOT+ "/" + map.get("courseCover").toString();
            imageLoader.loadDrawable(url, courseCover);
            viewMap.put(position, rowView);
        }
        return rowView;
    }  
}
