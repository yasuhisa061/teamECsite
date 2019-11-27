package com.internousdev.radish.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware{

	private String  backFlag;
	private Map<String,Object> session;

	//もしbackflag内がnullの場合セッション（ユーザーID）を削除する。それ以外の場合はSUCCESSを返す。
	public String execute(){

		if(backFlag == null){
			session.remove("userIdForResetPassword");
		}
		return SUCCESS;

	}

	public String getBackFlag(){
		return backFlag;
	}
	public void setBackFlag(String backFlag){
		this.backFlag=backFlag;
	}
	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session=session;
	}

}