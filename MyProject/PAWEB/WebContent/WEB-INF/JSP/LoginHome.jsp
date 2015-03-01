<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Search | INFORMATION RETRIEVAL SYSTEMS</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="" />
    <link href="css/home.css" rel="stylesheet" type="text/css" />
     <link href="css/dom.css" rel="stylesheet" type="text/css" />
       <script type="text/javascript" src="./js/jquery-1.8.3.js" ></script>
</head>
<style>

.border_bottom  {
 
  border-bottom: 1px solid #ddd;
}
 
.verticalLine {
    border-left: thick solid #ff0000;
}
</style>
<script>
function test(){
	
	$('#content2').html("<p style=margin-left:193px;padding-bottom: 100px;>Plese wait page loading..<br><img src=images/page_loading.gif ></p>");
	
	
	 $.ajax({
		 type: "GET",
		 url: "results.htm",
		 data:{
			 searchKey:$('#inputField').val()
		 },
		  beforeSend: function( xhr ) {
		 alert("Sending");
		 },
		 success: function (msg){
			 $('#content2').html(msg);
		  alert("Success " + msg);
		  },
		  error:function( xhr,status,error ) {
		 alert("Error  : " + xhr.statusText);
		 }
		 });
}


function doLogin(){
 
	 $.ajax({
		 type: "GET",
		 url: "login.htm",
		 dataType : "html",
		 data:{
			 user:$('#user').val(),
			 password:$('#password').val()
		 },
		  beforeSend: function( xhr ) {
		 alert("Sending");
		 },
		 success: function (msg){
			// $('#content2').html(msg);
				  alert("Success " + msg);
	/* 		var loginInfo = $($.parseHTML(msg)).filter("#loginInfo").html();
			alert(loginInfo);
					if(loginInfo =='User name Password invalid'){
						$('#loginMsg').html(loginInfo);
			}else{
				$('#LoginInfoId').html(loginInfo);
			
			} */
	
		  },
		  error:function( xhr,status,error ) {
		 alert("Error  : " + xhr.statusText);
		 }
		 });
}


</script>
<body>
 <div id="page">
 
 <!-- Header -->
       <jsp:include page="header.jsp"></jsp:include>
       
  
        <div id="main">	
           <!-- Left Content -->  
            <div id="sidebar">
                  
     <jsp:include page="leftContent.jsp"></jsp:include>
            </div><!-- sidebar -->  
            
            <!-- Main Content -->  	              
            <div id="content">
   <jsp:include page="results.jsp"></jsp:include>							              
            </div><!-- content -->                    
            <div class="clearing">&nbsp;</div> 
            <div id="footer">
                <p>Copyright &copy; 2014, designed by Name </p>
            </div>
        </div><!-- main -->
    </div><!-- page -->









</body>
</html>