package com.pamirs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.pamirs.dao.DocumentsInfoDAO;
import com.pamirs.dao.SavedHistroyDAO;
import com.pamirs.model.DocumentsInfo;
import com.pamirs.model.DocumentsInfoBean;
import com.pamirs.model.SavedHistroy;
import com.pamirs.model.Users;
import com.pamirs.util.CommonUtil;
import com.pamirs.util.DOMParserImpl;
import com.pamirs.util.RestClient;
import com.pamirs.util.SortByAuthor;
import com.pamirs.util.SortByRating;
import com.pamirs.util.SortyByTitle;

 

@Service
@Transactional
public class DocumentsService {
	
	@Autowired
	private DocumentsInfoDAO documentsInfoDAO;
	@Autowired
	private SavedHistroyDAO savedHistroyDAO;
	
	public static List globalResult = new ArrayList(); 
	public DocumentsInfoBean getResults(String searchKey,String orderBy){
		
	 
	//	List ls = getDocumentsInfoDAO().searchKey("searchKey", searchKey,orderBy);
		
		DocumentsInfoBean doc = searchAPI(searchKey,0,0);
		
		return doc;
	}
	
	public DocumentsInfoBean getResultsByPage(String searchKey,Integer start,Integer count){
		
		 
	//	List ls = getDocumentsInfoDAO().searchKey("searchKey", searchKey,orderBy);
		
		DocumentsInfoBean doc = searchAPI(searchKey,start,count);
		
		return doc;
	}
	
	public List getResults(String searchKey){
		
		 
		List ls = getDocumentsInfoDAO().searchKey("documentId", searchKey);
		
	
		
		return ls;
	}
	
	public List sortResults(String sortBy){
		
		if("author".equals(sortBy))
		Collections.sort(globalResult, new SortByAuthor());
		
		if("title".equals(sortBy))
			Collections.sort(globalResult, new SortyByTitle());
		
		if("rank".equals(sortBy))
			Collections.sort(globalResult, new SortByRating());
		
		return globalResult;
		
	}
	
	
	public List getGlobalResult(String key){
	 //			globalResult(key);
			
		 
		
			return globalResult;
			 
	}
	
	public DocumentsInfoBean searchAPI(String searchKey,Integer start,Integer count){
		
		String responseXml = RestClient.searchDocument(searchKey,start,count);
		
      DocumentsInfoBean docs = DOMParserImpl.parseDocumentsAll(responseXml,searchKey);
	
	if(globalResult!=null){
		globalResult.clear();
		globalResult.addAll(docs.getDocumentsInfoList());
	}
	return docs;
	
		
	}
	
	public Map  getSubDocumentDes(String key){
		
		String responseXml = RestClient.getChildDocumnet(key);
		
		Map m = DOMParserImpl.parseSubDocument(responseXml, null);
		return m;
	}
	
	
	
	
	public List getResultsCount(String resultsBy,String searchKey){
		
		return getDocumentsInfoDAO().getResults(resultsBy, searchKey);
	}
	
	
	public boolean saveRecord(String name , String wishList,Integer userId){
		
		for(int i=0;i<globalResult.size();i++){
			DocumentsInfo doc = (DocumentsInfo)globalResult.get(i);
			List getList =  getDocumentsInfoDAO().findByProperty("articleNumber", doc.getArticleNumber());
		 if(getList.size()==0){
			 String tile = doc.getTitle();
			 if(tile!=null && tile.length()>495){
				 tile = tile.substring(0,495);
				 doc.setTitle(tile);
			 }
			getDocumentsInfoDAO().save(doc);
		 }
		}
		List ls = getDocumentsInfoDAO().searchKey("articleNumber", wishList);
		if(ls.size()==0)
			return false;
		
		String wishListKey="";
		for(int j=0;j<ls.size();j++){
			DocumentsInfo ds = (DocumentsInfo)ls.get(j);
			wishListKey = wishListKey + ds.getDocumentId().toString();
			
			if(j<ls.size())
				wishListKey = wishListKey+",";
			
		}
		if(wishListKey.endsWith(","))
			wishListKey = wishListKey.substring(0,wishListKey.lastIndexOf(','));
		
		SavedHistroy saved = new SavedHistroy();
		saved.setSavedName(name);
		saved.setDocsHistory(wishListKey);
		Users user = new Users();
		user.setUserId(userId);
		saved.setUsers(user);
		saved.setDateTime(CommonUtil.getTodayDate());
		
	Integer res = getSavedHistroyDAO().save(saved);
	if(res==0)
		return false;
	else 
		return true;
		
	}
	
	
	public List  getRecords(String name,Integer userId){
		
		List ls = getSavedHistroyDAO().getRecordsByName("savedName" , name,userId);
		
		return ls;
	}
	
	public List  getAllSavedRecords(Integer userId,ModelMap map){
		
		List ls = getSavedHistroyDAO().findByProperty("users.userId" ,userId);
		map.addAttribute("savedresult",ls);
		
		return ls;
	}

	public DocumentsInfoDAO getDocumentsInfoDAO() {
		return documentsInfoDAO;
	}

	public void setDocumentsInfoDAO(DocumentsInfoDAO documentsInfoDAO) {
		this.documentsInfoDAO = documentsInfoDAO;
	}


	public SavedHistroyDAO getSavedHistroyDAO() {
		return savedHistroyDAO;
	}


	public void setSavedHistroyDAO(SavedHistroyDAO savedHistroyDAO) {
		this.savedHistroyDAO = savedHistroyDAO;
	}
	
	
	
	
	

}
