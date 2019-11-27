package com.internousdev.radish.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.radish.dao.UserInfoDAO;
import com.internousdev.radish.util.CommonUtility;
import com.internousdev.radish.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware{

	private String userId;
	private String password;
	private String newPassword;
	private String reConfirmationPassword;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> newPasswordErrorMessageList;
	private List<String> reConfirmationNewPasswordErrorMessageList;
	private String passwordIncorrectErrorMessage;
	private String newPasswordIncorrectErrorMessage;

	private String concealedPassword;
	private Map<String,Object> session;

	public String execute(){

		String result=ERROR;

		InputChecker inputChecker=new InputChecker();

		session.put("userIdForResetPassword",userId);

		//true,falseはどんな書体でいけるかを○✕判定
		userIdErrorMessageList=inputChecker.doCheck("ユーザーID", userId, 1, 8, true, false, false, true, false, false);
		passwordErrorMessageList=inputChecker.doCheck("現在のパスワード", password,1, 16, true, false, false, true, false, false);
		newPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード", newPassword,1, 16, true, false, false, true, false, false);
		reConfirmationNewPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード(再確認)",reConfirmationPassword,1,16,true,false,false,true,false,false);

		//上の中にエラーが一個でもあればERRORメッセージが出る設定
		if(userIdErrorMessageList.size()>0
	    ||passwordErrorMessageList.size()>0
	    ||newPasswordErrorMessageList.size()>0
	    ||reConfirmationNewPasswordErrorMessageList.size()>0){
			return result;
		}

		//新しいパスワードと再確認パスワードが一致しているかの確認
		newPasswordIncorrectErrorMessage = inputChecker.doPasswordCheck(newPassword,reConfirmationPassword);
		if(newPasswordIncorrectErrorMessage !=null){
			return result;
		}

		//データベース内にIDが存在しているかどうかを判断する
		UserInfoDAO userInfoDAO = new UserInfoDAO();

		int i=userInfoDAO.loginCheck(userId,password);
		if(i==1){
			CommonUtility commonUtility=new CommonUtility();
			concealedPassword = commonUtility.concealPassword(newPassword);
			session.put("newPassword", newPassword);
			result =SUCCESS;
		} else {
			passwordIncorrectErrorMessage ="ユーザーIDまたは現在のパスワードが異なります。";
		}
		return result;
	}

	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getNewPassword(){
		return newPassword;
	}
	public void setNewPassword(String newPassword){
		this.newPassword=newPassword;
	}
	public String getReConfirmationPassword(){
		return reConfirmationPassword;
	}
	public void setReConfirmationPassword(String reConfirmationPassword){
		this.reConfirmationPassword=reConfirmationPassword;
	}
	public List<String>getUserIdErrorMessageList(){
		return userIdErrorMessageList;
	}
	public void setUserIdErrorMessageList(List<String>userIdErrorMessageList){
		this.userIdErrorMessageList=userIdErrorMessageList;
	}
	public List<String> getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList=passwordErrorMessageList;
	}
	public List<String>getNewPasswordErrorMessageList(){
		return newPasswordErrorMessageList;
	}
	public void setNewPasswordErrorMessageList(List<String>newPasswordErrorMessageList){
		this.newPasswordErrorMessageList=newPasswordErrorMessageList;
	}
	public List<String>getReConfirmationNewPasswordErrorMessageList(){
		return reConfirmationNewPasswordErrorMessageList;
	}
	public void setReConfirmationNewPasswordErrorMessageList(List<String>reConfirmationNewPasswordErrorMessageList){
		this.reConfirmationNewPasswordErrorMessageList=reConfirmationNewPasswordErrorMessageList;
	}
	public String getPasswordIncorrectErrorMessage(){
		return passwordIncorrectErrorMessage;
	}
	public void setPasswordIncorrectErrorMessage(String passwordIncorrectErrorMessage){
		this.passwordIncorrectErrorMessage=passwordIncorrectErrorMessage;
	}
	public String getNewPasswordIncorrectErrorMessage(){
		return newPasswordIncorrectErrorMessage;
	}
	public void setNewPasswordIncorrectErrorMessage(String newPasswordIncorrectErrorMessage){
		this.newPasswordIncorrectErrorMessage=newPasswordIncorrectErrorMessage;
	}
	public String getConcealedPassword(){
		return concealedPassword;
	}
	public void setConcealedPassword(String concealedPassword){
		this.concealedPassword=concealedPassword;
	}
	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session =session;
	}
}