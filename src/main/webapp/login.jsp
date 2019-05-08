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
       userName:<input type="text" name="username"  autocomplete="new-password" /><br/> 
       passWord:<input type="password" name="password"  autocomplete="new-password"/><br/> 
       <input type="submit" value="登录"><br/>
   </form>
   ${requestScope.loginError}
</body>
</html>