<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
<form action="LoginServlet" method="post">
	用户名:<input type="text" name="UserId">
	密码:<input type="password" name="Password">
	<input type="submit" name="sumbit" value="登陆">
</form>
</body>
</html>