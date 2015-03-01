 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 

 
  <table style="width:423px">
  
  <tr style="height:35px;background:rgb(222, 205, 231);width:100px;"><th>S.No</th><th>User Id</th><th> Name </th><th>Email</th>
  
    <c:forEach items="${userList}" var="fr" varStatus="status">
 <tr style="height:35px;background:gray">  
 <td>${status.count}</td>
  <td>${fr.userName}</td>
 <td>${fr.firstName} ${userObj.lastName}</td>  
 
    
 
 
     <td>${fr.emailId}</td> </tr>
   
 
 
 
  
 </c:forEach>
 
 </table>