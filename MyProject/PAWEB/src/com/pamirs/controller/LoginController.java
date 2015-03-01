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
 import com.pamirs.service.LoginService;
import com.pamirs.util.CommonUtil;
 

@Controller
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
 
	
	
	 
	
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

 
	

}
