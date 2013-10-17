package com.example.login_test.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.login_test.common.BaseActivity;
import com.example.login_test.common.GlobalApp;
import com.example.login_test.common.RequestHandler;
import com.example.login_test.common.StaticVariable;

public class CheckLoginActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.check_login);
		
        CheckLoginTask task = new CheckLoginTask(this);
        task.execute();
	}
	
	
    public class CheckLoginTask extends AsyncTask<String, Integer, String>
    {
        
        public CheckLoginTask(BaseActivity activity) {
            super();
            this.activity = activity;
        }

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
                GlobalApp ga = (GlobalApp) getApplication();
                ga.setIsLogin(true);
                Intent intent = new Intent(CheckLoginActivity.this,MyCourseActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplication(), "登录失败", 1).show(); 
            }
        }
        
    }
}
