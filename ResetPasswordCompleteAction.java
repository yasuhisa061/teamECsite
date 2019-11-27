package com.internousdev.radish.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.radish.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware{

	private Map<String,Object>session;

	public String execute(){

		//全て成功していた場合、SUCCESSを返し、それ以外の場合はユーザーIDとnewPasswordをリセットしてエラーを返す
		String result=ERROR;
		UserInfoDAO userInfoDAO=new UserInfoDAO();
		int count=userInfoDAO.updatePassword(session.get("userIdForResetPassword").toString(),session.get("newPassword").toString());

		if(count>0){
			result=SUCCESS;

		}
		session.remove("userIdForResetPassword");
		session.remove("newPassword");

		return result;
	}

	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session=session;
	}
}