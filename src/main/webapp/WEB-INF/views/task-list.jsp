<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--  <form action="/searchBy" method="post">
<h4> Your Tasks</h4>
<input type="text" name="description" />
<p> </p>
<input type="date" name="dueDate" value="date"  pattern="yyyy-mm-dd"/>
<p></p>
<button type="submit">Search</button>
</form>-->
<h3>My Tasks</h3>
<table class=table>

<tr><th>Description</th><th>Due Date</th><th>Mark Complete</th><th>Remove</th></tr>

<c:forEach var="task" items="${tasks}">
<tr>
<td><c:out value="${task.description}"></c:out></td>
<td><c:out value="${task.dueDate}"></c:out></td>


<c:choose>
<c:when test="${task.complete eq false}">
 <td><input type="button" value = "Complete" onclick="javascript:location.href = '/changeTasktoComplete?id=${task.id}';"/></td>
</c:when>
<c:otherwise>
 <td><input type="button" value = "Completed" disabled="disabled" onclick=""/></td>
</c:otherwise>
</c:choose>
 <td><input type="button" value="Remove" onclick="javascript:location.href = '/removeATask?id=${task.id}';"/>
</tr>



</c:forEach>


</table>
<a href="/add-task">Add a task</a>

</body>
</html>