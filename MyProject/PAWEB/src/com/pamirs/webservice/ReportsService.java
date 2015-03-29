package com.pamirs.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.pamirs.model.SavedHistroy;
import com.pamirs.model.Users;
import com.pamirs.service.DocumentsService;
import com.pamirs.util.CommonUtil;
@Path("/")
public class ReportsService {
	@Autowired
	private DocumentsService documentsService;
	
	@Path("savedResults.htm")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public List<SavedHistroy> getReports(@QueryParam("savedName") String savedName,@QueryParam("userId") String userId,HttpServletRequest request){
		
		
		List<SavedHistroy> ls = new ArrayList<SavedHistroy>();
	 
		if(userId !=null){
			Integer userIdIn = Integer.parseInt(userId);
		 List subList = documentsService.getRecords(savedName, userIdIn);
		  if(subList.size()>0){
			  SavedHistroy sd = (SavedHistroy)subList.get(0);
			ls =  documentsService.getResults(sd.getDocsHistory());
		
	         }
	
		}
		  return ls;
		}
	
	
	
	@Path("reports.htm")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getReportsWise(@QueryParam("savedName") String savedName,@QueryParam("userId") String userId,HttpServletRequest request){
		
		
		List ls = new ArrayList();
		if(userId !=null){
			Integer userIdIn = Integer.parseInt(userId);
		 List subList = documentsService.getRecords(savedName, userIdIn);
		  if(subList.size()>0){
			  SavedHistroy sd = (SavedHistroy)subList.get(0);
			ls =  documentsService.getResults(sd.getDocsHistory());
		
	         }
	
		}
		String res="[";
		for(int i=0;i<ls.size();i++){
			
			Object[] obj = (Object[])ls.get(i);
			res = res + "['"+obj[0]+"',"+obj[1]+"]";
			if(ls.size()-1!=i)
				res = res+",";
		}
		res =res+"]";
		return res;
		}
	
}
