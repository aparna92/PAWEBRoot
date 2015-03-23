            <script type="text/javascript" src="./js/jquery.blockUI.js" ></script>
<script>

function submitFunAction(){
	 
	var form = document.createElement('form');
	form.setAttribute('action', 'results.htm');
	form.setAttribute('method', 'POST');
 
 

	var input1 = document.createElement('input');
	input1.setAttribute('type', 'hidden');
	input1.setAttribute('name', 'page');
	input1.setAttribute('id', 'page');
	input1.setAttribute('value', 'Home');

	var input2 = document.createElement('input');
	input2.setAttribute('type', 'hidden');
 	input2.setAttribute('name', 'searchKey');
	input2.setAttribute('id', 'searchKey');
	input2.setAttribute('value', $('#inputField').val());
	
	var input3 = document.createElement('input');
	input3.setAttribute('type', 'hidden');
	input3.setAttribute('name', 'userId');
 	input3.setAttribute('value', $('#userId').val());
 
 

	form.appendChild(input1);
	form.appendChild(input2);
	form.appendChild(input3);
	 
	document.body.appendChild(form);
 	form.submit();
	popup();
}
function popup(){
	$.blockUI({ message: $('#ajaxloaderDiv'), css: { width: '450px' } }); 
}


$(document).ready(function() {
$('#inputField').keypress(function(e) {
    if (e.which == '13') {
    	submitFunAction();
    }
});
   

   
$('#searchForm').submit(function(event){

	  event.preventDefault();
	});
	
});
</script>


 


          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <div  style=" float: left;    margin-left: 45%;    margin-top: 21px;" id="welcomenote"> <c:if test="${userObj ne null}"> Welcome : ${userObj.firstName} ${userObj.lastName} </c:if>&nbsp;</div>
      <div id="header">
           <h1><a href="home.htm">Web Search</a></h1>
            <p id="subtitle">SEARCHING AND SORTING DOCUMENTS USING INFORMATION RETRIEVAL SYSTEMS.</p>
        </div> 
        
        <div id="menu">
		<div style="padding-top:26px;">
		   <form method="post" action="#" id="searchForm">
   <input id="inputField" type="text" value="" name="searchKey" >
  <input type="button" onClick="submitFunAction()" value="Search" />
  <input type="hidden" name="page" value="Home">
  </form>
  </div>
        </div>
        
          <div id="ajaxloaderDiv" style="display:none; cursor: default; padding: 5px 5px 5px 5px"> 
          <font color="red" size="3"> <b>We are processing your request.  Please be patient </b></font><br/>
          <br/>
            <img src="././images/ajax-loader1.gif" />
         </div>
         	<input type="hidden" id="userId" name="userId" value="${userId}">