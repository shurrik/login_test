package com.example.login_test.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.login_test.R;
import com.example.login_test.common.BaseActivity;
import com.example.login_test.common.RequestHandler;
import com.example.login_test.common.StaticVariable;

public class CheckLoginActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_login);
		Intent intent=getIntent();
		String username = intent.getStringExtra("username");
		
        ShowLoginTask task = new ShowLoginTask();
        task.setActivity(this);
        task.execute();
	}
	
	
    public class ShowLoginTask extends AsyncTask<String, Integer, String>
    {
        private BaseActivity activity;
        
        @Override
        protected String doInBackground(String... params) {
            String url = StaticVariable.WWW_ROOT+"/test";
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
            //Toast.makeText(getApplication(), result, 1).show();
            if(result.equals("success"))
            {
                Intent intent = new Intent(CheckLoginActivity.this,MyCourseActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplication(), "登录失败", 1).show(); 
            }
        }

        public BaseActivity getActivity() {
            return activity;
        }

        public void setActivity(BaseActivity activity) {
            this.activity = activity;
        }
        
    }
}
