package com.pamirs.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.ModelMap;

 

import com.pamirs.model.Users;

public class CommonUtil {
	
	 private static Map userMap;
	 
	   public static Users getUser(Integer key){
		   
		   if(userMap!=null)
		   return (Users)userMap.get(key);
		   else
			   return null;
	   }
	   
	   public static Map userdMap(){
		   return userMap;
	   }
	   public static void loadUser(Integer key,Users users){
		   if(userMap==null)
		   userMap = new HashMap();
		   
		   userMap.put(key,users);
	   }
	   
	   public static void clearCache(){
		   if(userMap!=null)
			   userMap.clear();
	   }
	   
	   
	   public static Users getUsers(HttpServletRequest request){
			  
	         String userID = request.getParameter("userId");
		      if(userID!=null && userID.trim().length()>0){
		    	  System.out.println("userID" + userID);
		    	 	  Integer i_userID = Integer.parseInt(userID);
		    	  return getUser(i_userID);
		      }
		     
		     return null;
		  
	  }
	   
 
	  
	  public static ModelMap storeUserDetails(Users users,ModelMap mv){
	 	 mv.addAttribute("userId", users.getUserId());
	 	 mv.addAttribute("userName", users.getFirstName());
	 	 mv.addAttribute("userObj", users);
	 
	 	 mv.addAttribute("role_id", users.getRoleMaster().getRoleMasterId());
	 	 return mv;
	  }
	  public static void removeRecord(HttpServletRequest request){
		  
	      String userID = request.getParameter("userId");
		      if(userID!=null){
		    	 	  Integer i_userID = Integer.parseInt(userID);
		    	 	  if(userMap!=null)
		    	  userMap.remove(i_userID);
		      }
		     
		    
		  
	}
	  public static HttpSession getMySession(HttpServletRequest request){
		  
			HttpSession session = request.getSession(false);
		      
		     
		     return session;
		  
	  }
	
	public static String getTodayDate(){
		
		
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date d = new Date();
		
		String today = sdf.format(d);
		
		return today;
	}
	
	
	public static Timestamp parseDate(String date){
		Date d= new Date();
		try{
		//	2014-08-15T15:51:01Z
			String getDate = date.substring(0,date.indexOf('T'));
			String getTime = date.substring(date.indexOf('T')+1,date.indexOf('Z'));
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Date ds = sdf.parse(getDate+" "+getTime);
			   
			  if(ds==null)
				  ds = d;
			  
			   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   java.sql.Timestamp sq = new java.sql.Timestamp((sdf1.parse(sdf1.format(ds))).getTime());
			  return sq;
			  
		}catch(Exception e){
		e.printStackTrace();
			  return  null;
		}
	}	
		
		public static Integer performRating(String name){
			
			Integer returnValue=1;
			if(name!=null)
			{
				
				String sp[] = name.split(",");
				switch(sp.length){
				case 1 : returnValue=1;
				break;
				
				case 2 : returnValue=2;
				break;
				
				case 3 : returnValue=3;
				break;
				
				case 4 : returnValue=4;
				break;
				
				case 5 : returnValue=5;
				break;
				
				default:returnValue=5;
				break;
				}
				
				
			}
				
			
			
			
			
				return returnValue;
		}
		
 
	public static String trimStr(String str){
		
		if(str==null)
			return "";
		
		str = str.trim();
		return str;
		
		
		
	}
	
	
public static boolean sendMail(JavaMailSender mailSender,String from,String to,String subject,String messge){
		
		try{
			InternetAddress ip = new InternetAddress(to);
			ip.validate();
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper email = new MimeMessageHelper(message, true);
			   email.setFrom(from);
			    email.setTo(to);
				email.setSubject(subject);
				email.setText(messge, true);
		        
		        mailSender.send(message);
		        
		        return true;
			
		}catch(Exception e){
			e.printStackTrace();
			
			return false;
		}
		
		
		
	}
	
	
	
	public static String getFooter(){
	 
		StringBuffer sb = new StringBuffer();
	  
		 
		sb.append("<br> ");
		sb.append("<h1 style='color:#0094ff'>Thank you for Using PAWEB</h1>");
		sb.append("<br> ");
		sb.append("<br> ");
		sb.append("<p>This message was sent from a notification-only email address that does not accept incoming email. Please do not reply to this message.</p>");
		sb.append("<br> ");
		sb.append("Thanks");
		sb.append("<br> ");
		sb.append("PAWEB.com");
		
		
		
		return sb.toString();
	}
 

}
