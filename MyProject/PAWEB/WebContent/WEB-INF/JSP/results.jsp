    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     
         <style>
  .select-wrapper{  
    	float: left;
		display: inline-block;
		border: 1px solid #d8d8d8;            
	 background:#36322F;
		cursor: pointer;
    color:#FFFFFF;
   }
   .select-wrapper, .select-wrapper select{
		width: 130px;
		height: 26px;
		line-height: 26px;
		 margin-left: 3px;
	}
	.select-wrapper:hover{
background:#EF5548;
		border-color: #239fdb;
	}
	.select-wrapper .holder{
		display: block;
		margin: 0 35px 0 5px;
		white-space: nowrap;            
		overflow: hidden;
		cursor: pointer;
		position: relative;
		z-index: -1;
	}
	.select-wrapper select{
		margin: 0;
		position: absolute;
		z-index: 2;            
		cursor: pointer;
		outline: none;
		opacity: 0;
		/* CSS hacks for older browsers */
		_noFocusLine: expression(this.hideFocus=true); 
		-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
		filter: alpha(opacity=0);
		-khtml-opacity: 0;
		-moz-opacity: 0;
	}
     
    
    
    
    
    
 

 

.af_pager {
 float: left;
    font-size: 1.2em;
    margin-left: 366px;
    margin-top: -21px;
}
 
    </style> 
     
     
      <c:choose>
 <c:when test="${result eq null}"> 
       <div id="contheader">
                    <h2>Welcome to my Site!</h2>
                </div>		
                <div id="content2">	
                    <div class="post">
                        <h2><a href="#">Documents Search</a></h2>
                 
                        <div class="entry">
                            <p>
 This is a main module were in the user can search for any document.
User can also search the documents without login, but the User cannot save his list of searched documents.
The searched document will be provided along with following details
1)	Hyperlink
2)	Name of the Book
3)	Name of Author
4)	Rating
5)	Date of Publication
                            
                            
                            </p>
                        </div>
                    </div><!-- post -->	
                    <div class="post">
                        <h2><a href="#">Saved Search List</a></h2>
                  
                        <div class="entry">
                            <p>
This is the module where in the users searched documents will be displayed.Once the user searches for any document the list of documents available based on this search is displayed.
Maximum per page 10 documents are displayed.In order to see the next set of documents the user needs to click on the next button available at the bottom.
A check box is provided against each document.User can check more than one check box and click on Saved button in order to save his list.User can give a name to the saved list.
At Left the Saved List will be displayed.Next time when the user login, he can view his previously searched documents.
                            </p>
                        </div>
                    </div><!-- post -->	
                    <div class="post">
                        <h2><a href="#">Reports</a></h2>
                     
                        <div class="entry">
                           <p>
This module mainly focuses on Graphical representation.When the user saves his list as mentioned above, the same list will also be displayed in Report List at Left just below the Saved list.
Clicking on any of the name of saved list, a report will be displayed indicating how many Authors have written the document on same searched topic.
                           
                           
                           </p>
                        </div>
                    </div><!-- post -->	
                </div><!-- content2 -->								              
      
      
      </c:when>
      <c:otherwise>
      <c:choose> 
     <c:when test="${result ne null and fn:length(result) ne 0}"> 
      <div id="contheader">
      
    
                    <h2>Search Result</h2>
                    
                 
                </div>		
            
                   <div  style="margin-top:0px;background:#36322F;height:25px;color:#FFFFFF">
                      <c:if test="${page ne 'savedhistory'}">
                   <select class="select-wrapper" id="selectSort" onChange="getSort('${searchKey}','${pageNumber}','${count}')"><option value="null">Select Sorting</option>
                   <option value="title">&nbsp;Title</option>
                   <option value="author">&nbsp;Author</option>
                   <option value="rank">&nbsp;Rank</option>
                   
                   </select> <a href="javascript:saveRec()" style="color:#FFFFFF" class="confirm" id="alert">  <img src="images/1412447613_stock_save-as.png" title="save"  style="border:none; margin-left: -1px;" height="23" width="23">   <div style="float: left; margin-left: 65px;margin-top: 4px;font-size: 1.2em;">&nbsp;Save List&nbsp; </div></a>
                   
                 <div class="af_pager">  
                   <a class=".first" href="javascript:getPage('${pageNumber}','${count}','${searchKey}','first')"><span>First</span></a>
                  <a class=".previous" href="javascript:getPage('${pageNumber}','${count}','${searchKey}','previous')"><span>Previous</span></a>
                  ${pageNumber} .. ${count}
                                  <a class=".next" href="javascript:getPage('${pageNumber}','${count}','${searchKey}','next')"><span>Next</span></a>
                 <a class=".last" href="javascript:getPage('${pageNumber}','${count}','${searchKey}','last')"><span>Last</span></a>
                 
                 </div>
                   </c:if>  
                        <c:if test="${page eq 'savedhistory'}">
                        &nbsp;&nbsp;&nbsp;Name :&nbsp;&nbsp;    <label id="loginMsg" style="color:#d9463d">${savedName}</label>    &nbsp;&nbsp;&nbsp;   Date :&nbsp;&nbsp;<label id="loginMsg" style="color:#d9463d">${savedDate}</label>
                        </c:if>
                   </div>  
                   
               
                <div id="content2">	
                
 <div id="maxsize">
  <table class="border_bottom">

 <c:forEach items="${result}" var="fr" varStatus="status">
<tr align="center">
<td> <label id="loginMsg" style="color:#d9463d">${status.count}  </label>&nbsp;</td>
<td><input type="checkbox" class="chkNumber" id="${fr.documentId}" value="${fr.articleNumber}"  /></td> 

<td></td>

<td><img src="images/defaultDoc.png" style="border:none" width="100" height="100"></td>


<td align="left">
<hr>
<table>
<tr><td><a href="javascript:viewDocFun('${fr.articleNumber}');"><u>${fr.title}</u></a></td></tr>
<tr><td>Author   : <label id="loginMsg" style="color:#d9463d">${fr.author}</label></td></tr>
<tr><td>Record No     : <label id="loginMsg" style="color:#d9463d">${fr.articleNumber}</label></td></tr>
<tr><td>Language : <label id="loginMsg" style="color:#d9463d">${fr.bookLanguage}</label></td></tr>
<tr><td>  Date   :<label id="loginMsg" style="color:#d9463d"><fmt:formatDate pattern='dd/MM/yyyy' value='${fr.date}' /></label></td></tr>
<tr><td>Ranking   :
 <c:forEach  begin="1" end="${fr.rank}" varStatus="loop" >
<img src="images/rating.png" width="14" height="14" style="border:none">
</c:forEach>

</td></tr>



</table>



 
</td>
</tr>
</c:forEach>
 
 
 
 

 
</table>
</div>
</div>
</c:when>
   <c:otherwise>
      <div id="contheader">
      
    
                    <h2>Search Result</h2>
                      <div id="content2">	
<div style="margin-top:30px;margin-left:177px;"> <font color="red" size="4"> No Records Found </font></div>
</div>
</div>
 
 </c:otherwise>
  </c:choose>
  </c:otherwise>
    </c:choose>
 