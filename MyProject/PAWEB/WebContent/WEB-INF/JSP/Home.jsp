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
      <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
       <script type="text/javascript" src="./js/jquery-1.8.3.js" ></script>
         <script type="text/javascript" src="./js/jquery-ui.js" ></script>
    
 
</head>
<style>

.border_bottom  {
  text-align: left;
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

 
function saveRec(){
	
 
	
	 var ids="";
	 var flag=false;
	   var chkId = '';
	/* $("input:checkbox[name='checkbox']").each(function (i) {        
        if($(this).checked){
        	flag=true;
        	ids = ids + $(this).attr('id');
        	ids = ids +",";
        } 
         });
        */
        
        
        if ($('.chkNumber:checked').length) {
         
            $('.chkNumber:checked').each(function () {
              chkId += $(this).val() + ",";
            });
            chkId = chkId.slice(0, -1);
           	  flag=true;
          }
          else {
            alert('Nothing Selected.Please select checkbox');
          }
        
   
 
	if(flag){
	
 	 //   if($(this).val() === 'approve') {
	    	 $('#dialog').dialog({					
	 			width: 380,
				//modal: true,
				close: function(event, ui) {
				 
				//	$("#dialog").remove();
					}
				});
	    	  	$('#prodBtn').click(function() {
	    	    	
	            		var  dkey = $('#digiKey').val();
	        		if(dkey==''){
	        			$('#msg').css("display","block");
	        		}else{
	        			
	        			
	        			 $.ajax({
		        			 type: "GET",
		        			 url: "save.htm",
		        			 data:{
		        				 saveList:chkId,
		        				 savedName:dkey,
		        				 userId:$('#userId').val()
		        				 
		        			 },
		        			  beforeSend: function( xhr ) {
		       // 				  $("#dialog").html("Please wait......");
		        			 },
		        			 success: function (msg){
		        				//  $("#dialog").html(msg);
		        				
		        				$("#dialog").dialog('destroy');
		        				  
		        			  alert(msg);
		        			  if(msg == 'Please login'){
		        				  $('#user').focus();
		        			  }else if(msg=='Your wish list saved successfully'){
		        			 
		        				  window.location.reload(true);
		        				  //Clear All check boxes
		        				  
		        			  }else{
		        				  
		        			  }
		        			  },
		        			  error:function( xhr,status,error ) {
		        			 alert("Error  : " + xhr.statusText);
		        			 }
		        			 });
	        			
	        		}
	        		 
	        
	        	
	        		
	        		
	        
	        	});
		 
	 
		

	 
//	} 
		
	}
}

function myProfile(){
	
	 $('#myprofile').dialog({					
			width: 450,
			//modal: true,
			close: function(event, ui) {
			 
			//	$("#dialog").remove();
				}, buttons: [
								{
									text: "Close",
									click: function() {
									$( this ).dialog( "close" );
									}
									},
									
									{
										text: "Update",
										click: function() {

											var res = checkupdateProfile();
									  		if(res=='Success'){
											
											$.ajax({
												 type: "GET",
												 url: "updateProf.htm",
												 data: {
													 profileId :$('#userId').val(),
													 FirstName :$('#upFirstName').val(),
													 LastName :$('#upLastName').val() ,
													 Email :$('#upEmailId').val() 
													 
												   },
												  beforeSend: function( xhr ) {
													
													  $('#mylogingRes').html("&nbsp;&nbsp;<img src='images/ajax-loader1.gif' />");
													  $('#mylogingRes1').html("");
												 },
												 success: function (msg){
														if(msg=='Success'){
															$('#mylogingRes').html("Record updated successfully");
														}else{
															$('#mylogingRes').html("");
															$('#mylogingRes1').html(msg);
														}
												  },
												  error:function( xhr,status,error ) {
													  $('#mylogingRes').html("");
														$('#mylogingRes1').html("");
												 alert("Error  : " + xhr.statusText);
											 
												 }
												 });			
									  		}else{
									  			$('#mylogingRes1').html(res);
									  			
									  		}
											
											
											
		 
		
}
							}
						
					]
	});

	
	
	
}


function viewDocFun(dkey){
	
	
	 $.ajax({
		 type: "GET",
		 url: "searchSubDoc.htm",
		 data:{
		   key:dkey
		 },
		  beforeSend: function( xhr ) {
// 				  $("#dialog").html("Please wait......");
		 },
		 success: function (msg){
			 $('#viewDoc').html(msg);
		  },
		  error:function( xhr,status,error ) {
		 alert("Error  : " + xhr.statusText);
		 }
		 });
	 
	
	 $('#viewDoc').dialog({					
			width: 700,
			//modal: true,
			close: function(event, ui) {
			 
			//	$("#dialog").remove();
				}
			});
	 
	
	
	
}


 

function changePWd(){
	
	  $('#pwd2').val("");
  $('#repwd').val("");
	
	 $('#changepwd').dialog({					
			width: 450,
			//modal: true,
			close: function(event, ui) {
			 
			//	$("#dialog").remove();
				},
				 buttons: [
							{
								text: "Close",
								click: function() {
								$( this ).dialog( "close" );
								}
								},
								
								{
									text: "Change Password",
									click: function() {
						 
										var res = checkupdatePassword();
								  		if(res=='Success'){
	  		
			 $.ajax({
    			 type: "GET",
    			 url: "changepwd.htm",
    			 data:{
    				 pwd:$('#pwd2').val(),
    				 repwd:$('#repwd').val(),
    				 userId :$('#userId').val()
    			 },
    			  beforeSend: function( xhr ) {
   		 			  $('#chlogingRes').html("");
					  $('#chlogingRes1').html("");
    			 },
    			 success: function (msg){
    				//  $("#dialog").html(msg);
    				
    					if(msg=='Password changed successfully'){
							$('#chlogingRes').html("Password changed successfully");
						}else{
							$('#chlogingRes1').html(msg);
						}
    				  
    			 
    			  },
    			  error:function( xhr,status,error ) {
    			 alert("Error  : " + xhr.statusText);
    			 $("#changepwd").dialog('destroy');
    			 }
    			 });
	  		
								  		}else{
								  			$('#chlogingRes1').html(res);
								  		}
	  		
	  		
		}
									}
								
							]
			});
	
}

function userRegFun(){
	
	$('#FirstName').val("");
	$('#LastName').val("");
	$('#Email').val("");
	$('#pwd').val("");
	$('#secQue').val("");
	$('#secAnswer').val("");
	
	
	 $('#userReg').dialog({					
			width: 450,
			//modal: true,
			close: function(event, ui) {
			 
			//	$("#dialog").remove();
				},				 buttons: [
						{
							text: "Close",
							click: function() {
							$( this ).dialog( "close" );
							}
							},
							
							{
								text: "Create Account",
								click: function() {
									var res = checkRegister();
							  		if(res=='Success'){
									$.ajax({
										 type: "GET",
										 url: "register.htm",
										 data: {
											 FirstName :$('#FirstName').val(),
											 LastName :$('#LastName').val() ,
											 Email :$('#Email').val() ,
											 password :$('#pwd').val() ,
											 secQue :$('#secQue').val(),
											 secAnswer :$('#secAnswer').val(),
											 userName : $('#userName').val()
										 },
										  beforeSend: function( xhr ) {
					 
											  $('#relogingRes').html("&nbsp;&nbsp;<img src='images/ajax-loader1.gif' />");
											  $('#relogingRes1').html("");
										 },
										 success: function (msg){
										 
												if(msg=='Success'){
													$('#relogingRes').html("User Creation Successfull.");
												}else{
													$('#relogingRes').html("");
													$('#relogingRes1').html(msg);
												}
										  },
										  error:function( xhr,status,error ) {
												$('#relogingRes').html("");
												$('#relogingRes1').html("");
										 alert("Error  : " + xhr.statusText);
									 
										 }
										 });	
							  		}else{
							  			
							  			$('#relogingRes1').html(res);
							  		}
	  		
	  		
	  		
		}
									}
								
							]
			});
	 
}

function forgetpwdFun(){
	
  $('#userNamefr').val("");
 $('#secAns').val("");
	
	 $('#forgetpwd').dialog({					
			width: 450,
			//modal: true,
			close: function(event, ui) {
			 
			//	$("#dialog").remove();
				}, buttons: [
								{
									text: "Close",
									click: function() {
									$( this ).dialog( "close" );
									}
									},
									{
										text: "Submit",
										click: function() {
							 
											var res = checkForgetPassword();
	  		if(res=='Success'){
	  		
	  		 $.ajax({
    			 type: "GET",
    			 url: "forgetpwd.htm",
    			 data:{
    				 userName:$('#userNamefr').val(),
    				 secAns:$('#secAns').val()
    			 },
    			  beforeSend: function( xhr ) {
    				  
    			 
    				  $('#frlogingRes').html("&nbsp;&nbsp;<img src='images/ajax-loader1.gif' />");
					  $('#frlogingRes1').html("");
    			 },
    			 success: function (msg){
    				//  $("#dialog").html(msg);
    				if(msg =='Success : Password will be sent to registed email'){
    					  $('#frlogingRes').html("Password will be sent to registed email");
						
    				 
    				}
    				else{
    					  $('#frlogingRes').html('');
    					  $('#frlogingRes1').html(msg);
    					 
    				}
    			
    			  },
    			  error:function( xhr,status,error ) {
    			 alert("Error  : " + xhr.statusText);
    			 $("#forgetpwd").dialog('destroy');
    			 }
    			 });
	  		 
	  		}else{
	  		  $('#frlogingRes1').html(res);
	  		}
	  		 
		}
										}
								]
			});
}
 
 
function userListIDFun(){
	  		
	  		
	  		 $.ajax({
   			 type: "GET",
   			 url: "getusers.htm",
   		 
   			  beforeSend: function( xhr ) {
  // 				  $("#dialog").html("Please wait......");
   			 },
   			 success: function (msg){
   				 
   				 
   				 $('#userListID').html(msg);
   				 
   				 $('#userListID').dialog({					
   					width: 450,
   					//modal: true,
   					close: function(event, ui) {
   					 
   					//	$("#dialog").remove();
   						}
   					});
 
   					
   			
   			  },
   			  error:function( xhr,status,error ) {
   			 alert("Error  : " + xhr.statusText);
   			 $("#userListID").dialog('destroy');
   			 }
   			 });
	  		 
	  		 
	  		 
	 	  	$('#ucloseId').click(function() {
	 	  		$("#userListID").dialog('destroy');
	 	  	});
	  		 
	  		 
	  	 
	
}

function checkForgetPassword(){
	
	var  userName = $('#userNamefr').val();
	 var secAns = $('#secAns').val();
	 
	 if(isEmpty(userName))
		 return 'User Name is mandatory';
	 if(isEmpty(secAns))
		 return 'Security Answer is mandatory';
	 return 'Success';
	
}

function checkRegister(){
	 
	var  FirstName =  $('#FirstName').val();
	var  LastName =  $('#LastName').val() ;
	var  Email = $('#Email').val() ;
	var  password = $('#pwd').val() ;
	var  secQue =  $('#secQue').val();
	var  secAnswer = $('#secAnswer').val();
	var  userName=  $('#userName').val();
	var repassword =  $('#pwd1').val();
	
	 if(isEmpty(FirstName))
		 return 'FirstName is mandatory';
	 if(isEmpty(LastName))
		 return 'LastName is mandatory';
 
	 if(isEmpty(Email))
		 return 'Email is mandatory';
	 if(!IsEmail(Email))
			 return 'Invalid Email Address';
 
	 if(isEmpty(secQue))
		 return 'Security Question is mandatory';
	 if(isEmpty(secAnswer))
		 return 'Security Answer is mandatory';
	 
	 if(isEmpty(userName))
		 return 'User Name is mandatory';
	 
	 if(isEmpty(password))
		 return 'Password is mandatory';
	 if(!passLength(password))
		 return 'The password must have at least 8 characters ';
	 
	 if(!password ==repassword)
		 return ' Password and Re-type Password doest not match';
	 return 'Success';
	 
}
function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}
function checkupdatePassword(){
	
	var  pwd = $('#pwd2').val();
	var  repwd = $('#repwd').val();
	 
	 if(isEmpty(pwd))
		 return 'Password is mandatory';
	 if(isEmpty(repwd))
		 return 'Re-type Password is mandatory';
	 if(!pwd ==repwd)
		 return ' Password and Re-type Password doest not match';
	 return 'Success';
	
}
function checkupdateProfile(){
	
	var  FirstName  = $('#upFirstName').val();
	var  LastName  = $('#upLastName').val() ;
	var  Email  = $('#upEmailId').val() ;
	
	
	 if(isEmpty(FirstName))
		 return 'FirstName is mandatory';
	 if(isEmpty(LastName))
		 return 'LastName is mandatory';

	 if(isEmpty(Email))
		 return 'Email is mandatory';
	 
	 if(!IsEmail(Email))
		 return 'Invalid Email Address';
	 
	 return 'Success';
	
}

function isEmpty(val){
	
	
	if(val=='')
		return true
		else 
			return false;
}
function passLength(val){
	
	
	if(val.length==8)
		return true
		else 
			return false;
}



 

</script>
<body>
 <div id="page">
    <jsp:include page="header.jsp"></jsp:include>
                
        <div id="main">	
            <div id="sidebar">
   
     <jsp:include page="leftContent.jsp"></jsp:include>
     
            </div><!-- sidebar -->    	              
            <div id="content">
               <jsp:include page="results.jsp"></jsp:include>
               </div><!-- content -->  
                                 
            <div class="clearing">&nbsp;</div> 
            
            <div id="footer">
                <jsp:include page="footer.jsp"></jsp:include>
               
            </div>
        </div><!-- main -->
    </div><!-- page -->





<div id="dialog" title="Save List Name" style="display:none;width:150px;">
 
 Enter Name&nbsp;<input type="text" id="digiKey"  name="digiKey" style="width: 234px;"/>
 <br>
 <label id="msg" style="display:none;color:red;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name is mandatory</label>
 <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="prodBtn" value="Procced"/>
 
 
 
 

</div>

<div id="myprofile" title="My Profile" style="display:none;width:150px;">
 
 <table  style="height:240px;">
  <tr><td><input type="hidden" id="profileId" value="${userObj.userName}"></td> </tr>
 
 
 <tr style="background:rgb(222, 205, 231)"><td><b>&nbsp;First Name<font color=red>*</font></b></td> <td><input style="width: 234px;" type="text" id="upFirstName" value="${userObj.firstName}"></td> </tr>
 
 
  <tr style="background:rgb(127, 132, 127)"><td><b>&nbsp;Last Name<font color=red>*</font></b></td> <td><input style="width: 234px;" type="text" id="upLastName" value="${userObj.lastName}"></td> </tr>
 
 
   <tr style="background:rgb(222, 205, 231)"><td><b>&nbsp;Email Id<font color=red>*</font></b></td> <td><input style="width: 234px;" type="text" id="upEmailId" value="${userObj.emailId}"></td> </tr>
   
     <tr style="background:rgb(127, 132, 127)"><td><b>&nbsp;Security Question<font color=red>*</font></b></td> <td style="height:19px;">${userObj.securityQuestion}</td> </tr>
     
       <tr style="background:rgb(222, 205, 231)"><td><b>&nbsp;Answer<font color=red>*</font></b></td> <td style="height:35px;">${userObj.securityAnswer}</td> </tr>
       
         <tr style="background:rgb(127, 132, 127)"><td><b>&nbsp;Role Name</b></td> <td style="height:35px;" >${userObj.roleMaster.roleDes}</td> </tr>
 
 
   <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="mylogingRes" style=" color: green;font-size: 16px; margin-left: 45px; width: 288ppx;"></div></td></tr>
  <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="mylogingRes1" style=" color:red;font-size: 16px; margin-left: 45px; width: 288ppx;"></div></td></tr>
 
 
 </table>
 

</div>


<div id="changepwd" title="Change Password" style="display:none;width:150px;">
 
 <table cellspacing="4" cellpadding="4">
 <tr><td>New Password<font color=red>*</font></td> <td><input type="password" id="pwd2"  name="pwd2" style="width: 234px;"/></td> </tr>
 <tr><td>Reenter Password<font color=red>*</font></td> <td><input type="password" id="repwd"  name="repwd" style="width: 234px;"/></td> </tr>
 
  <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="chlogingRes" style=" color: green;font-size: 16px; margin-left: 45px; width: 288ppx;"></div></td></tr>
  <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="chlogingRes1" style=" color:red;font-size: 16px; margin-left: 45px; width: 288ppx;"></div></td></tr>
 
 
 </table>

</div>

<div id="userReg" title="Create Account" style="display:none;width:150px;">
<form>
 <table>
 <tr><td>&nbsp;</td></tr>
 <tr style="height:70%">
 <td><h3>First Name: <font color=red>*</font></h3></td><td><input type="text" id="FirstName"  name="name" style="width: 234px;"></td>
</tr>
<tr style="height:70%">
 <td><h3>Last Name:<font color=red>*</font></h3></td><td><input type="text" id="LastName" name="empid" style="width: 234px;"></td>
</tr>
<tr style="height:70%">
    <td><h3>Email ID:<font color=red>*</font></h3> </td><td><input type="text" id="Email" name="email" style="width: 234px;"></td>
</tr>
<tr style="height:70%">
    <td><h3>Security Question:<font color=red>*</font></h3> </td><td><select id="secQue" style="width: 234px;height:39px;">
      <option> -- select --</option>
  <option> What was the name of your elementary / primary school?</option>
  <option>In what city or town does your nearest sibling live?</option>
  <option>What was the name of your first stuffed animal or doll or action figure?</option>
  <option>What were the last four digits of your childhood telephone number?</option>
  <option>What time of the day were you born? (hh:mm)</option>
  <option>What was your favorite place to visit as a child?</option>
  <option>What is the country of your ultimate dream vacation?</option>
  <option>What is the name of your favorite childhood teacher?</option>
    
       
    
    </select></td>
</tr>
<tr style="height:70%">
    <td><h3>Security Answer:<font color=red>*</font></h3> </td><td><input id="secAnswer" type="text" name="phno" style="width: 234px;"></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr style="height:70%"> 
<td><h3> User Name:<font color=red>*</font></h3></td><td><input type="text" name="userName" id="userName" style="width: 234px;"></td>
</tr> 

<tr style="height:70%"> 
<td><h3> Password:<font color=red>*</font></h3></td><td><input type="password" name="pwd" id="pwd" style="width: 234px;"></td>
</tr> 
<tr style="height:70%"> 
<td><h3>Retype Password:<font color=red>*</font></h3></td><td><input type="password" name="pwd1" id="pwd1" style="width: 234px;"></td>
</tr> 
 <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="relogingRes" style=" color: green;font-size: 16px; margin-left: 45px; width: 288px;"></div></td></tr>
  <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="relogingRes1" style=" color:red;font-size: 16px; margin-left: 45px; width: 288px;"></div></td></tr>
 
 
 
</table>
 </form>
 

</div>


<div id="forgetpwd" title="Forget Password" style="display:none;width:150px;">
 
 <table cellspacing="4" cellpadding="4">
<tr>
    <td><h3>User Id:<font color=red>*</font></h3> </td><td><input type="text" id="userNamefr"  name="userNamefr" style="width: 234px;"> </td>
</tr>
<tr>
    <td ><h3>Security Answer:<font color=red>*</font></h3> </td><td><input type="text"  id="secAns" name="phno" style="width: 234px;"></td>
</tr>
 
 <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="frlogingRes" style=" color: green;font-size: 16px; margin-left: 45px; width: 330px;"></div></td></tr>
  <tr><td colspan="2" align="center" id="frmsg" style="color:#d9463d"><div id="frlogingRes1" style=" color:red;font-size: 16px; margin-left: 45px; width: 295px;"></div></td></tr>
 
 
 </table>
</div>


<div id="userListID" title="Users List " style="display:none;width:150px;">
 
 
</div>

<div id="viewDoc" title="Document Quick View" style="display:none;width:150px;">
 
 &nbsp;&nbsp;&nbsp;&nbsp; Please Wait.....
</div>

</body>


<script>
function getPage(pageNum,count,dkey,operation){
 
	var form = document.createElement('form');
	form.setAttribute('action', 'results.htm');
	form.setAttribute('method', 'POST');
 
 

	var input1 = document.createElement('input');
	input1.setAttribute('type', 'text');
	input1.setAttribute('name', 'searchKey');
	input1.setAttribute('id', 'searchKey');
	input1.setAttribute('value', dkey);

	var input2 = document.createElement('input');
	input2.setAttribute('type', 'text');
 	input2.setAttribute('name', 'pageNumber');
	input2.setAttribute('id', 'pageNumber');
	input2.setAttribute('value', pageNum);
	
	var input3 = document.createElement('input');
	input3.setAttribute('type', 'text');
 	input3.setAttribute('name', 'operation');
	input3.setAttribute('id', 'operation');
	input3.setAttribute('value', operation);

	var input4 = document.createElement('input');
	input4.setAttribute('type', 'text');
	input4.setAttribute('name', 'page');
	input4.setAttribute('id', 'page');
	input4.setAttribute('value', 'Home');
 
 
	var input5 = document.createElement('input');
	input5.setAttribute('type', 'text');
	input5.setAttribute('name', 'count');
	input5.setAttribute('id', 'count');
	input5.setAttribute('value', count);
	
	var input6 = document.createElement('input');
	input6.setAttribute('type', 'hidden');
	input6.setAttribute('name', 'userId');
	input6.setAttribute('value', $('#userId').val());

	form.appendChild(input1);
	form.appendChild(input2);
	form.appendChild(input3);
	form.appendChild(input4);
	form.appendChild(input5);
	form.appendChild(input6);
	document.body.appendChild(form);
	form.submit();
	
}


function getSort(dkey,pageNum,count){
	 
	
	var operation = $('#selectSort').val();
	var form = document.createElement('form');
	form.setAttribute('action', 'results.htm');
	form.setAttribute('method', 'POST');
 
 

	var input1 = document.createElement('input');
	input1.setAttribute('type', 'text');
	input1.setAttribute('name', 'searchKey');
	input1.setAttribute('id', 'searchKey');
	input1.setAttribute('value', dkey);

 
	var input2 = document.createElement('input');
	input2.setAttribute('type', 'text');
 	input2.setAttribute('name', 'pageNumber');
	input2.setAttribute('id', 'pageNumber');
	input2.setAttribute('value', pageNum);
	
	var input3 = document.createElement('input');
	input3.setAttribute('type', 'text');
 	input3.setAttribute('name', 'orderBy');
	input3.setAttribute('id', 'orderBy');
	input3.setAttribute('value', operation);

	var input4 = document.createElement('input');
	input4.setAttribute('type', 'text');
	input4.setAttribute('name', 'page');
	input4.setAttribute('id', 'page');
	input4.setAttribute('value', 'Home');
 
	var input5 = document.createElement('input');
	input5.setAttribute('type', 'text');
	input5.setAttribute('name', 'count');
	input5.setAttribute('id', 'count');
	input5.setAttribute('value', count);
 
	
	var input6 = document.createElement('input');
	input6.setAttribute('type', 'hidden');
	input6.setAttribute('name', 'userId');
	input6.setAttribute('value', $('#userId').val());

	form.appendChild(input1);
	form.appendChild(input2);
	form.appendChild(input3);
	form.appendChild(input4);
	form.appendChild(input5);
	form.appendChild(input6);
	
	document.body.appendChild(form);
	form.submit();
	
}
</script>

</html>