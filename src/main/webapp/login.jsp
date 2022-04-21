<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel = "stylesheet" href = "login.css">
</head>

<body>

<%//Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String strServlet = (String) request.getAttribute("fromServlet");
	
	if(strServlet == "1"){
		out.write("ユーザーが存在しません");
	}else if(strServlet == "3"){
		out.write("パスワードが違います");
	}%>

	<form action = "./Login2" method = "POST" class = "user_input">	
		<p class = "ID">ID:<input type = "text" name = "ID"></p>
		<p class = "PW">PW:<input type = "text" name = "PW"></p>	
		<input type = "submit" value = "login">
	</form>	
</body>
</html>