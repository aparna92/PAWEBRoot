package com.pamirs.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.pamirs.model.DocumentsInfo;
import com.pamirs.model.DocumentsInfoBean;

 
public class DOMParserImpl {
	
	 
	
	
	public  static DocumentsInfoBean parseDocumentsAll(String inputFile,String searchKey){
		
	
		  DocumentsInfoBean docs = new DocumentsInfoBean();
		try{
		
	 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(inputFile)));
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		String totalResults="0";
		 String startingIndex="1";
		
		if(doc.getDocumentElement().getElementsByTagName("opensearch:totalResults").item(0)!=null)
	     totalResults =doc.getDocumentElement().getElementsByTagName("opensearch:totalResults").item(0).getTextContent();
		if(doc.getDocumentElement().getElementsByTagName("opensearch:startIndex").item(0)!=null)
		  startingIndex  = doc.getDocumentElement().getElementsByTagName("opensearch:startIndex").item(0).getTextContent();
		
	 
	   
	   docs.setItemsCount(Integer.parseInt(CommonUtil.trimStr(totalResults)));
	   docs.setStartingIndex(Integer.parseInt(CommonUtil.trimStr(startingIndex)));
		
	 
	 
		NodeList nList = doc.getElementsByTagName("entry");
		List ls = new ArrayList();
	if(nList.getLength()>0){
	 
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
		 
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	         DocumentsInfo ds = new DocumentsInfo();
	     	NodeList nList1 = eElement.getElementsByTagName("author");
	     	Node authNode = nList1.item(0);
	     	Element authElement = (Element) authNode;
	     	String authName =authElement.getElementsByTagName("name").item(0).getTextContent();
	 
				ds.setArticleNumber(CommonUtil.trimStr(eElement.getElementsByTagName("oclcterms:recordIdentifier").item(0).getTextContent()));
				ds.setAuthor(CommonUtil.trimStr(authName));
				ds.setTitle(CommonUtil.trimStr(eElement.getElementsByTagName("title").item(0).getTextContent()));
				ds.setBookLanguage("English");
				ds.setBookName("");
				ds.setSearchKey(searchKey);
				ds.setDate(CommonUtil.parseDate(eElement.getElementsByTagName("updated").item(0).getTextContent()));
				ds.setTitleHref(CommonUtil.trimStr(eElement.getElementsByTagName("id").item(0).getTextContent()));
				ds.setRank(CommonUtil.performRating(authName));
				ls.add(ds);
			}
		
		
		} 
	}
		
		docs.setDocumentsInfoList(ls);

	}catch(Exception e){
		 e.printStackTrace();
	}
		
		return docs;
	}	
	
	
	public  static Map parseSubDocument(String inputFile,String searchKey){
		
 
		Map keyMap = new HashMap();
	 
		try{
		
		 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(inputFile)));
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		NodeList nodeList =  doc.getElementsByTagName("datafield");
 
		
	if(nodeList.getLength()>0){
	 

		 
		 
		  for (int i = 0; i < nodeList.getLength(); i++) {
	 
		 
	 
		      //We have encountered an <employee> tag.
		 
		      Node node = nodeList.item(i);
	 
		      if (node instanceof Element) {
		 
		 
	 
		       String id = node.getAttributes().
	 
		            getNamedItem("tag").getNodeValue();
		 
		  
	 
		        NodeList childNodes = node.getChildNodes();
		        StringBuffer sb = new StringBuffer();
		        
		 
		        for (int j = 0; j < childNodes.getLength(); j++) {
		 
		          Node cNode = childNodes.item(j);
		 
		 
	 
		          //Identifying the child tag of employee encountered.
		 
		          if (cNode instanceof Element) {
		 
		            String content = cNode.getLastChild().
		 
		                getTextContent().trim();
	 
		            	sb.append(content);
		          }
		 
		        }//end of sub for loop
		        
		        if(keyMap.get(id)!=null){
		        	String val =(String)keyMap.get(id);
		        	
		        	keyMap.put(id, val+"<br>"+sb);
		        	
		        }else{
		        	keyMap.put(id, sb.toString());
		        }
		        
		    
		        
		        
		        
		        
		        
		 
		   //     empList.add(emp);
		 
		      }     
		      
		  
		  }//end of main loop
		  
		 

	}
		
		 

	}catch(Exception e){
		 e.printStackTrace();
	}
		
		return keyMap;
	}	
}
