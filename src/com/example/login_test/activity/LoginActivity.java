
package com.example.login_test.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.login_test.R;
import com.example.login_test.R.id;
import com.example.login_test.R.layout;
import com.example.login_test.R.menu;
import com.example.login_test.common.BaseActivity;
import com.example.login_test.common.RequestHandler;
import com.example.login_test.common.StaticVariable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {

    private EditText username;
    private EditText password;
    private Button submit;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setTitle("登录中……");
                pDialog.show();
                new LoginThread().start();
            }
        });

    }

    private class LoginThread extends Thread {
        @Override
        public void run() {
            try {
                login();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private Handler mHandler = new Handler() {  
        public void handleMessage (Message msg) {//此方法在ui线程运行

            pDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this,CheckLoginActivity.class);
            
            intent.putExtra("username", username.getText().toString());
            intent.putExtra("password", password.getText().toString());
            startActivity(intent);
        }  
    };
    
    private int login() throws Exception {

        List<HashMap<String, Object>> data = new ArrayList();
    
        Message msg = mHandler.obtainMessage(); //获取msg
        RequestHandler rh = new RequestHandler(this);
/*        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("username", username.getText().toString()));  
        nvps.add(new BasicNameValuePair("password", password.getText().toString()));
        
        
        rh.httpPost(url, nvps);*/
        String casUrl = StaticVariable.CAS_ROOT+"/testlogin?username="+username.getText().toString()+"&password="+password.getText().toString()+"&service="+StaticVariable.WWW_ROOT+"/sso";
        /*msg.obj = rh.httpPost(url, nvps);*/
        msg.obj = rh.httpGet(casUrl);
        mHandler.sendMessage(msg);
        return 1;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
