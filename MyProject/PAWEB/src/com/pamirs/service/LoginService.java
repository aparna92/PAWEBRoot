package com.pamirs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pamirs.dao.UsersDAO;
import com.pamirs.model.Users;

@Service
@Transactional
public class LoginService {
	
	
	
	@Autowired
	private UsersDAO usersDAO;
	
	public Users getLogin(String userName,String pwd){
		return getUsersDAO().doLogin(userName,pwd);
	}
	
	public Users getUser(String userName){
		return getUsersDAO().getUser(userName);
	}
	
	public Users checkSecurity(String userName,String secAns){
		return getUsersDAO().checkSecurity(userName,secAns);
	}
	
	public boolean changePwd(String pwd,Integer userId){
		
		Integer in = getUsersDAO().updatePassword(pwd,"userId",userId);
		
 
		
		if(in>0)
			return true;
		else
			return false;
		
	}
	
	
	public boolean updateProfile(String firstName,String lastName,String email,Integer prid){
		Integer in =	getUsersDAO().updateProfile(firstName, lastName, email, "userId", prid);
		
		if(in>0)
			return true;
		else
			return false;
	}
	public List getAllUsers(){
		return getUsersDAO().findByProperty("roleMaster.roleMasterId", 2);
	}
	
	
	public boolean doRegisterInfo(Users usrs){
		
		Integer in = (Integer)getUsersDAO().save(usrs);
		
		if(in>0)
			return true;
		else
			return false;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	
	
	

}
