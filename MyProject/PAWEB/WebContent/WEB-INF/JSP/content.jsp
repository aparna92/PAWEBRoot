     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     
     
     
     
     
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body> 
     <div id="loginInfo">
         <c:choose>
     <c:when test="${msg eq 'success'}"> 
         <table>
     <tr><td>Welcome </td> </tr>
       
       <tr><td>First Name </td> <td>LastName</td> </tr>
     
      <tr><td><a href="home.htm"><input type="button" value="Logout"></a></td> </tr>
     </table>
     </c:when>
     <c:otherwise>
     User name Password invalid
     </c:otherwise>
     </c:choose>
 
     </div>
     
     </body>
     </html>