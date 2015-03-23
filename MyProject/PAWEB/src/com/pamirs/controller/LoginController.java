package com.pamirs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hsqldb.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.pamirs.model.RoleMaster;
import com.pamirs.model.Users;
import com.pamirs.service.DocumentsService;
import com.pamirs.service.LoginService;
import com.pamirs.util.CommonUtil;
 

@Controller
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private DocumentsService documentsService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	@Value("${application.mail.userName}")
	private String fromAddress;
	
	@RequestMapping("home.htm")
	public String getHome(){
		return "Home";
	}
	
	@RequestMapping("login.htm")
	 
	public String login(@RequestParam String user,@RequestParam String password,ModelMap mv,HttpServletRequest request){
		
		 
		Users users = getLoginService().getLogin(user, password);
		if(users!=null){
			
			CommonUtil.loadUser(users.getUserId(), users);
			CommonUtil.storeUserDetails(users,mv);
			mv.addAttribute("user", users);
		 	mv.addAttribute("msg", "success");
			getDocumentsService().getAllSavedRecords(users.getUserId(), mv);
		}else{
			mv.addAttribute("loginmsg", "Invalid Username and Password");
			
		}
		
		//List ls = documentsService.globalResult;
		//if(ls.size()>0)
		//mv.addAttribute("result", ls);
		
		return "Home";
	}
	
	@RequestMapping("logout.htm")
	public String logout(HttpServletRequest request){
		HttpSession session = CommonUtil.getMySession(request);
		if(session!=null)
		session.invalidate();
		
		CommonUtil.removeRecord(request);
		return "Home";
	}
	
	
	@RequestMapping("changepwd.htm") 
	@ResponseBody
	public String chnagePwd(@RequestParam String pwd,@RequestParam String repwd,HttpServletRequest request,ModelMap mv){
		
		 
		
		Users users = CommonUtil.getUsers(request);
		if(users!=null){
		CommonUtil.storeUserDetails(users,mv);
		} 
		
		pwd = pwd.trim();
		repwd = repwd.trim();
 
		if(!pwd.equals(repwd))
			return "Password and Retype Password doest not match";
		 
		if(users!=null){
		boolean ff=  getLoginService().changePwd(pwd,users.getUserId());
		getDocumentsService().getAllSavedRecords(users.getUserId(), mv);
		if(ff)
			return "Password changed successfully";
		}
		
		
		return "Operation failed";
	}

	
	@RequestMapping("forgetpwd.htm")
	@ResponseBody
	public String getPwd(@RequestParam String userName,@RequestParam String secAns){		
		if(getLoginService().getUser(userName)==null)
			return "Error : Username does not exits";
		
		else{
			Users us = getLoginService().checkSecurity(userName, secAns);
			if(us==null)
				return "Error : Security answer incorrect";
			else{
				boolean res = CommonUtil.sendMail(mailSender,fromAddress , us.getEmailId() , "PAMIRS Registration Password", getResetMessage(us.getFirstName(),us.getPassword()));
		 
				return "Success : Password will be sent to registed email";
			}
		}
		 
	}
	
	private String getResetMessage(String userName,String refNo){
		String ref = "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("&nbsp;&nbsp;Dear ").append(userName+",");
		sb.append("<br> ");
		sb.append("&nbsp;&nbsp;<h2> Your Password is ").append(refNo).append("</h2>");
		 
		   sb.append(CommonUtil.getFooter());
		ref = sb.toString();
		return ref;
	}
	
	@RequestMapping("getusers.htm")
	public String getUsersList(ModelMap mv){
		
		List ls = getLoginService().getAllUsers();
		
		mv.addAttribute("userList", ls);
		return "UsersList";
	}
	
	@RequestMapping("register.htm")
	@ResponseBody
	public String getRegister(@RequestParam String FirstName,@RequestParam String LastName,@RequestParam String Email,@RequestParam String password,@RequestParam String secQue,@RequestParam String secAnswer,@RequestParam String userName){	
		
		if(getLoginService().getUser(userName)!=null)
			return "User Name already exits.";
		
		Users userInfo = new Users();
		
		userInfo.setUserName(userName);
		userInfo.setPassword(password);
		
		
	 
		userInfo.setFirstName(FirstName);
		userInfo.setLastName(LastName);
		userInfo.setEmailId(Email);
		userInfo.setSecurityAnswer(secAnswer);
		userInfo.setSecurityQuestion(secQue);
		
		RoleMaster rr =new RoleMaster();
		rr.setRoleMasterId(2);
		userInfo.setRoleMaster(rr);
	 
		
	boolean b =	getLoginService().doRegisterInfo(userInfo);
		if(b)
		return "Success";
		else
			
			return "Registration failed,Please try again";
		
	}
	
	
	@RequestMapping("updateProf.htm")
	@ResponseBody
	public String updateProf(@RequestParam String FirstName,@RequestParam String LastName,@RequestParam String Email,@RequestParam Integer profileId){	
		
		boolean b=	getLoginService().updateProfile(FirstName,LastName,Email,profileId);
		if(b)
		return "Success";
		else
			
			return "Update failed,Please try again";
	}
	
	
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public DocumentsService getDocumentsService() {
		return documentsService;
	}

	public void setDocumentsService(DocumentsService documentsService) {
		this.documentsService = documentsService;
	}
	
	
	

}
