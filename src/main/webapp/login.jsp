<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
   <form action="${pageContext.request.contextPath}/user/login" method="post">
       userName:<input type="text" name="username" /><br/> 
       passWord:<input type="password" name="password" /><br/> 
       <input type="submit" value="登录"><br/>
   </form>
</body>
</html>