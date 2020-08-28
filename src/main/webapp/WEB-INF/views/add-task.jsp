<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Add a Task</h3>
<form action="/add-task" method="post">
Description:
<p><input type="text" name="description"/></p>

<p>Due Date:</p><input type="date" name="dueDate" required pattern="mm/dd/yyyy"/>
<p> </p>
<button type="submit">Add</button>&nbsp;&nbsp;&nbsp;
<a href="/my-tasks">Never mind</a>
</form>
</body>
</html>