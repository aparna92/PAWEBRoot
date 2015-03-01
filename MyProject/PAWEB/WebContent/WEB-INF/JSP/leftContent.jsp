     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     

 
          <c:choose>
     <c:when test="${userObj eq null}"> 
  
 
 
 
              <div id="login">
                    <h2>Account Login</h2>
                    
                    <div  id="LoginInfoId" class="box2">
                    <form method="post" action="login.htm">
                            <label id="loginMsg" style="color:#d9463d">${loginmsg}</label>
                            <p class="txtleft"><label for="user">User Name:</label></p>
                            <input type="text" name="user" id="user" class="text" /><br />
                            <p class="txtleft"><label for="password">Password:</label></p>
                            <input type="password" name="password" id="password" class="password" /><br />
                            <p class="rem">
                            <input type="checkbox" checked="checked" name="remember" id="remember" />      <label for="remember">Remember me</label></p>
                            <input type="submit" name="submit" id="submit" class="submit" onClick="doLogin()" value="Submit" />
                            <p><a href="javascript:forgetpwdFun()">Forgot your password?</a></p>
                            <p><a href="javascript:userRegFun()">Create an account</a></p>
                        </form>
                    </div>
                </div><!-- login -->
                
         </c:when>
         <c:otherwise>       
                
                 <div id="login">
                   
           <h2>My Account <div style="margin-left: 177px;margin-top: -35px;"><a href="logout.htm">Logout <img src="images/1412442069_exit.png" height="20" style="margin-top:10xp;border:none" width="20" title="logout"></a></div></h2>
                    <div  id="LoginInfoId" class="box2">
                     <ul>
                            <li><a href="javascript:myProfile()">My Profile</a></li>
                       <li><a href="javascript:changePWd()">Change Password</a></li>
                       
                         <c:if test="${userObj ne null}">
                          <c:if test="${userObj.roleMaster.roleMasterId eq 1}">
                           <li><a href="javascript:userListIDFun()">Users List</a></li>
                          </c:if>
                          </c:if>
                        </ul>
                        
                    </div>
                    

              
                </div><!-- login -->
                <div class="box">
                    <h2>Saved Search List</h2>
                    <div class="box2">
                        <ul>
                              <c:choose> 
     <c:when test="${savedresult ne null and fn:length(savedresult) ne 0}"> 
      <c:forEach items="${savedresult}" var="fr" >
         <li><a href="savedResults.htm?savedName=${fr.savedName}&userId=${userId}">${fr.savedName}</a></li>
      </c:forEach>
     </c:when>
     <c:otherwise>
       <li><a href="#">No Data</a></li>
     </c:otherwise>
     </c:choose>
     
     
                          
                    
                        </ul>
                    </div>
                </div><!-- box -->				
                <div class="box">
                    <h2>Reports</h2>
                    <div class="box2">
                        <ul>
                                                       <c:choose> 
     <c:when test="${savedresult ne null and fn:length(savedresult) ne 0}"> 
      <c:forEach items="${savedresult}" var="fr" >
         <li><a href="reports.htm?savedName=${fr.savedName}&userId=${userId}">${fr.savedName}</a></li>
      </c:forEach>
     </c:when>
     <c:otherwise>
       <li><a href="#">No Data</a></li>
     </c:otherwise>
     </c:choose>
                      
                       
                        </ul>
                    </div>
                </div><!-- box -->	
                
          </c:otherwise>
          </c:choose>      