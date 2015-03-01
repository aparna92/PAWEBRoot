            <script type="text/javascript" src="./js/jquery.blockUI.js" ></script>
 

 


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
  <input type="button" onClick="javascript:alert('under development')" value="Search" />
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