package com.example.login_test.common;

import android.app.Application;

public class GlobalApp extends Application {
	private boolean isLogin = false;
	private String sessionId;

	public boolean isLogin(){
	    return isLogin;
	  }

	public void setIsLogin(boolean b) {
		isLogin = b;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
