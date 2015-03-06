package com.pamirs.util;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 

public class RestClient {

	static final String  key="QIvyRI6ovOfnMTVTHyhaGywmFjAi4x9fNIC4dOP5qfZFM6XPsAAQLi1vsl5DpuiAQ7GIxnXI36Be8PI5";
	
	
	public static String  searchDocument(String searchKey,Integer start,Integer count){
		try{
			searchKey = searchKey.trim();
		ClientConfig config = new DefaultClientConfig();
		  
		   Client client = Client.create(config);
		  	    
		    WebResource service = client.resource(getBaseURI());
		    
		    String subPath="opensearch?q="+searchKey+"&wskey="+key;
		    if(start!=0 && count!=0){
		    	subPath="opensearch?q="+searchKey+"&start="+start+"&count="+10+"&wskey="+key;
		    }
		    
		    String str = service.path(subPath).accept(MediaType.APPLICATION_XML).get(String.class);
		    
		    
		    return str;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String  getChildDocumnet(String searchKey){
		try{
			searchKey = searchKey.trim();
		ClientConfig config = new DefaultClientConfig();
		  
		   Client client = Client.create(config);
		  	    
		    WebResource service = client.resource(getBaseURI1());
		    
		    String subPath="content/"+searchKey+"?wskey="+key;
		    
		   
		    String str = service.path(subPath).accept(MediaType.APPLICATION_XML).get(String.class);
		    
		    
		    return str;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	  private static URI getBaseURI() {
		//    return UriBuilder.fromUri("http://localhost:8080/RestServer/").build();
			  return UriBuilder.fromUri("http://www.worldcat.org/webservices/catalog/search/worldcat").build();
			  
		  }
	  
	  private static URI getBaseURI1() {
		//    return UriBuilder.fromUri("http://localhost:8080/RestServer/").build();
			  return UriBuilder.fromUri("http://www.worldcat.org/webservices/catalog").build();
			  
		  }
	  
	  
	 
}
