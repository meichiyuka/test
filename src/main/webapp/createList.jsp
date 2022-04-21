<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>createList</title>
<link rel = "stylesheet" href = "createList.css">

<script src="createList.js"></script>
</head>

<body>

	<div class = "topBtn">
		<!-- topに戻るボタン -->
		<form action="top.jsp" method = "POST" class = "top">
			<input type = "submit" value = "top">
		</form>
		
		<!-- logoutボタン -->
		<form action="login.jsp" method = "POST" class = "logout">
			<input type = "submit" value = "logout">
		</form>
	</div>




	<form action="CreateList" method="POST" id="check">

		<div class="qBrock">
			<!--問題文を入力するテキストボックス -->
			<p>問題：</p>
			<input type="text" class="qTxt" name="qData">
		</div>

		<!--答えを入力する欄 -->
		<table id="answerTxt">
			<tr>
				<td></td>
			</tr>
		</table>


	</form>
			<div class="bottomBtn1">
			<!--追加ボタン -->
			<input type="button" value="追加" id="add" onclick="coladd()">
		</div>

		<div class="bottomBtn2">
			<!--確認ボタン -->
			<button type="submit" form = "check">確認</button>
		</div>
		
		<div class="bottomBtn3">
			<!--戻るボタン -->
			<input type="button" value="戻る">
		</div>

</body>
</html>