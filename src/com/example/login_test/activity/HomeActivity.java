package com.example.login_test.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.login_test.common.BaseActivity;
import com.example.login_test.common.GlobalApp;

public class HomeActivity extends BaseActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GlobalApp ga = (GlobalApp) getApplication();
        if(ga.getSessionId()==null&&!ga.isLogin())
        {
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(HomeActivity.this,CheckLoginActivity.class);
            startActivity(intent);
        }
    }

}
