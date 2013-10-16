package com.example.login_test.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.login_test.R;
import com.example.login_test.adapter.MyCourseListAdapter;
import com.example.login_test.common.AsyncImageLoader;
import com.example.login_test.common.BaseActivity;
import com.example.login_test.common.RequestHandler;
import com.example.login_test.common.StaticVariable;

public class MyCourseActivity extends BaseActivity{

    private ListView myCourses;
    private List<Map> data = new ArrayList();
    private AsyncImageLoader imageLoader = new AsyncImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycourse);
        
        
/*        MyCourseTask task = new MyCourseTask();
        task.setActivity(this);
        task.execute();*/
        new readTask(this).execute();
        
    }
    
    
/*    public class MyCourseTask extends AsyncTask<String, Integer, String>
    {
        private BaseActivity activity;
        
        @Override
        protected String doInBackground(String... params) {
            String url = StaticVariable.WWW_ROOT+"/android/mycourse";
            String res = "";
            RequestHandler handler = new RequestHandler(activity);
            try {
                res = handler.httpGet(url);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            return res;
        }
        
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            try {
                JSONArray ja = new JSONArray(result);
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject obj = ja.getJSONObject(i);
                    Map<String,String> item = new HashMap(); 
                    item.put("courseCover", obj.getString("imgUrl"));
                    item.put("courseNo", obj.getString("courseNo"));
                    item.put("courseName", obj.getString("courseName"));
                    data.add(item);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SimpleAdapter adapter = new SimpleAdapter(MyCourseActivity.this, data, R.layout.mycourse_item, new String[]{"courseName"}, new int[]{R.id.courseName});
            myCourses = (ListView) findViewById(R.id.mycourses);
            myCourses.setAdapter(adapter);
        }
        

        public BaseActivity getActivity() {
            return activity;
        }

        public void setActivity(BaseActivity activity) {
            this.activity = activity;
        }
        
    }*/
    
    public class readTask extends AsyncTask<Object, Void, Void> {
        
        private BaseActivity activity;
        
        public readTask(BaseActivity activity) {
            super();
            this.activity = activity;
        }

        
        @Override
        protected Void doInBackground(Object... arg0) {
/*            users = new stringGetJson().getJson();
            return null;*/
            String url = StaticVariable.WWW_ROOT+"/android/mycourse";
            String res = "";
            RequestHandler handler = new RequestHandler(activity);
            try {
                res = handler.httpGet(url);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            try {
                JSONArray ja = new JSONArray(res);
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject obj = ja.getJSONObject(i);
                    Map<String,String> item = new HashMap(); 
                    item.put("courseCover", obj.getString("imgUrl"));
                    item.put("courseNo", obj.getString("courseNo"));
                    item.put("courseName", obj.getString("courseName"));
                    data.add(item);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MyCourseListAdapter myCourseListAdapter = new MyCourseListAdapter(activity, data);
            myCourses = (ListView) findViewById(R.id.mycourses);
            myCourses.setAdapter(myCourseListAdapter);
        }
    }
}
