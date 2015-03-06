package com.pamirs.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pamirs.model.DocumentsInfoBean;
import com.pamirs.model.SavedHistroy;
import com.pamirs.model.Users;
import com.pamirs.service.DocumentsService;
import com.pamirs.util.CommonUtil;


@Controller
public class DocumentsController {
	
	@Autowired
	private DocumentsService documentsService;
	
	@RequestMapping(value="results.htm",method=RequestMethod.POST)
	public String getResults(@RequestParam String searchKey,@RequestParam(required=false) String orderBy,ModelMap mv,@RequestParam(required=false) Integer pageNumber,@RequestParam(required=false) String page,@RequestParam(required=false) String operation,@RequestParam(required=false) Integer count,HttpServletRequest request){
			
		List ls = new ArrayList();
		
	 
		 
/*		if(ls.size()<=10){
			mv.addAttribute("result", ls);
			count = ls.size();
		}else{
			count = ls.size();
			List subList = new ArrayList();
			if(operation!=null){
				if("first".equals(operation)){
					pageNumber=1;
				}
				if("previous".equals(operation)){
					pageNumber = pageNumber-1;			
				}
				if("next".equals(operation)){
					pageNumber = pageNumber+1;
				}
				if("last".equals(operation)){
					pageNumber = count/10+1;
					 
				}
							

				
				
				
				
			}
			
			if(pageNumber==null || pageNumber==0 || pageNumber==1){
				pageNumber = 1;
				startCount =0;
			}else{
				startCount = (pageNumber-1)*10;
			}
					
			endCount = pageNumber*10;
			if(endCount>ls.size())
				endCount = ls.size();
			
			
			System.out.println("pageNumber : " + pageNumber + " count :"+count);
			System.out.println("startCount : " + startCount + " count :"+endCount);
			for(int i=startCount; i<endCount;i++){
				subList.add(ls.get(i));
			}
			
		
		
			mv.addAttribute("result", subList);
			
			
		}
		
		if(pageNumber==null || pageNumber==0){
			pageNumber = 1;
		}
	 */
		
	
		if(operation!=null){
			
			if(pageNumber==null || pageNumber==0){
				pageNumber = 1;
			}
			
			
			if("first".equals(operation)){
				pageNumber=1;
			}
			if("previous".equals(operation)){
				pageNumber = pageNumber-1;			
			}
			if("next".equals(operation)){
				pageNumber = pageNumber+1;
			}
			if("last".equals(operation)){
				pageNumber = count;
				 
			}
			
		 
			DocumentsInfoBean doc  = documentsService.getResultsByPage(searchKey, pageNumber,count);
			ls = doc.getDocumentsInfoList();
			mv.addAttribute("pageNumber", doc.getStartingIndex());
			mv.addAttribute("count", doc.getItemsCount());
								
		}else if(orderBy!=null){
			
			 ls = documentsService.sortResults(orderBy);
			 mv.addAttribute("pageNumber", pageNumber);
		     mv.addAttribute("count", count);
		}else{
			DocumentsInfoBean doc  = documentsService.getResults(searchKey, orderBy);
			ls = doc.getDocumentsInfoList();
			mv.addAttribute("pageNumber", 1);
			mv.addAttribute("count", doc.getItemsCount());
		}
		mv.addAttribute("result", ls);

		mv.addAttribute("searchKey", searchKey);
	 
		
		Users users = CommonUtil.getUsers(request);
		if(users!=null){
		CommonUtil.storeUserDetails(users,mv);
		} 
		
	 
		 
		if(users!=null){
			getDocumentsService().getAllSavedRecords(users.getUserId(), mv);
		}
		
		return page;
	}
	
	@RequestMapping("save.htm")
	
	@ResponseBody
	public String saveRes(@RequestParam String saveList,@RequestParam String savedName, HttpServletRequest request,ModelMap mv){
		Users users = CommonUtil.getUsers(request);
		if(users!=null){
		CommonUtil.storeUserDetails(users,mv);
		} 
		 
		if(users==null){
			return "Please login";
		}else{
			List ls = getDocumentsService().getRecords(savedName, users.getUserId());
			if(ls.size()>0){
				return "Name already exitst,Please try with diffrent name";
			}else{
			boolean res = getDocumentsService().saveRecord(savedName, saveList, users.getUserId());
			if(res==false)
				return "Opeation falied,Please try again later";	
			}
		}
		
		
		return "Your wish list saved successfully";
	}
	
	@RequestMapping("reports.htm")
	public String getreports(@RequestParam String savedName, HttpServletRequest request,ModelMap mv){
		List ls = new ArrayList();
		Users users = CommonUtil.getUsers(request);
		if(users!=null){
		CommonUtil.storeUserDetails(users,mv);
		}
		if(users !=null){
		 List subList = getDocumentsService().getRecords(savedName, users.getUserId());
		  
		  if(subList.size()>0){
			  SavedHistroy sd = (SavedHistroy)subList.get(0);
			ls =  getDocumentsService().getResultsCount("author",sd.getDocsHistory());
			 mv.addAttribute("savedName",sd.getSavedName());
			 mv.addAttribute("savedDate",sd.getDateTime());
		  }
		  
		 mv.addAttribute("page", "reports") ;
		
		getDocumentsService().getAllSavedRecords(users.getUserId(), mv);
		}
		String res="[";
		for(int i=0;i<ls.size();i++){
			
			Object[] obj = (Object[])ls.get(i);
			res = res + "['"+obj[0]+"',"+obj[1]+"]";
			if(ls.size()-1!=i)
				res = res+",";
		}
		res =res+"]";
		
		 
		mv.addAttribute("res", res);

		return "Reports";
		
	}
	
	@RequestMapping("savedResults.htm")
	public String getSavedResults(@RequestParam String savedName, HttpServletRequest request,ModelMap mv){
		List ls = new ArrayList();
		Users users = CommonUtil.getUsers(request);
		if(users!=null){
		CommonUtil.storeUserDetails(users,mv);
		}
		if(users !=null){
		 List subList = getDocumentsService().getRecords(savedName, users.getUserId());
		  
		  if(subList.size()>0){
			  SavedHistroy sd = (SavedHistroy)subList.get(0);
			ls =  getDocumentsService().getResults(sd.getDocsHistory());
			 mv.addAttribute("savedName",sd.getSavedName());
			 mv.addAttribute("savedDate",sd.getDateTime());
		  }
		  
		 mv.addAttribute("page", "savedhistory") ;
		
		getDocumentsService().getAllSavedRecords(users.getUserId(), mv);
		}
		mv.addAttribute("result", ls);

		return "Home";
		
	}
	
	
	@RequestMapping("searchSubDoc.htm")
	 
	public String searchSubDoc(@RequestParam String key,ModelMap mv){
		
		Map m = documentsService.getSubDocumentDes(key);
	 
		
		Set s = m.keySet();
		Iterator it = s.iterator();
		while(it.hasNext()){
			
			String key1 = (String)it.next();
			if("700".equalsIgnoreCase(key1))
				mv.addAttribute("author", m.get(key1));
			
			if("246".equalsIgnoreCase(key1))
				mv.addAttribute("booktitle", m.get(key1));
			
			if("650".equalsIgnoreCase(key1))
				mv.addAttribute("othertitle", m.get(key1));
			
			if("10".equalsIgnoreCase(key1))
				mv.addAttribute("oclc", m.get(key1));
			
			if("300".equalsIgnoreCase(key1))
				mv.addAttribute("desc", m.get(key1));
			
			if("260".equalsIgnoreCase(key1))
				mv.addAttribute("form", m.get(key1));
		 
			
 
			 
		}
 
		 
		mv.addAttribute("oclc",key);
		return "viewSubDoucment";
	}

	public DocumentsService getDocumentsService() {
		return documentsService;
	}

	public void setDocumentsService(DocumentsService documentsService) {
		this.documentsService = documentsService;
	}

	
	
	
}
