<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TopPeage</title>
<link rel = "stylesheet" href = "top.css">
</head>

<body>
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

		<form action="greeting" method = "POST" class = "doTest">
		<p><input type = "submit" value = "テストをする >"  class = "btn"></p>
		</form>

		<form action="greeting" method = "POST" class = "result">
		<p><input type = "submit" value = "過去の採点結果を見る >"  class = "btn"></p>
		</form>

	</div>

</body>

</html>