<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="header.jsp" flush="true" />
<head>
<meta charset="UTF-8">
<title>TopPeage</title>
<link rel = "stylesheet" href = "top.css">
</head>

<body>

	<%
	request.setCharacterEncoding("UTF8");
	//usernameを受け取る
	String user_name = (String)request.getAttribute("user_name");
	%>

	<form action="login.jsp" method = "POST" class = "logout">
	<p><input type = "submit" value ="logout"></p>
	</form>
	
	<div class = "select">

		<form action = "./createList.jsp"  method = "POST" class = "create">
		<p><input type = "submit" value = "問題と答えを登録する >" class = "btn"></p>
		</form>

		<form action = "./Questions"  method = "POST" class = "questions">
		<p><input type = "submit" value = "問題と答えを確認する >" class = "btn"></p>
		</form>
			
		<form action="./IndicateTest" method = "POST" class = "doTest">
		<p><button type="submit" name="user_name" value="<%=user_name%>" class = "btn">テストをする ></button></p>
		</form>

		<form action="./Histories" method = "POST" class = "result">
		<p><button type = "submit" name="user_name" value="<%=user_name%>" class = "btn">過去の採点結果を見る ></button></p>
		</form>

	</div>

</body>

</html>