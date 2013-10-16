
package com.example.login_test.common;


import android.app.Activity;

public class BaseActivity extends Activity {

    public String getSession()
    {
        GlobalApp ga = (GlobalApp) getApplication();
        
        String sessionId = ga.getSessionId();
        if(sessionId==null||(sessionId!=null&&sessionId.length()==0))
        {
            sessionId = new GetSessionTask().getSession();
        }
        ga.setSessionId(sessionId);
        return sessionId;
    }
}
