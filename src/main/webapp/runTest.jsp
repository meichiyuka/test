<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="header.jsp" flush="true" />
<head>
<meta charset="UTF-8">
<title>runTest</title>
<link rel = "stylesheet" href = "runTest.css">
</head>
<body>

	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="login.QuestionsBean"%>

	<%
	request.setCharacterEncoding("UTF8");
	//問題文のリストを受け取る
	ArrayList<QuestionsBean> questionList = (ArrayList<QuestionsBean>) request.getAttribute("qList");
	//usernameを受け取る
	String user_name = (String)request.getAttribute("user_name");
	//useridを受け取る
	String user_id = (String)request.getAttribute("user_id");
	%>
	
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
	
	<form action = "RunTest" method = "POST" id="check">
	
	<%
	//問題文リストを使ってループ
	for (int i = 0; i < questionList.size(); i++) {
		//i行目のデータをqListに代入
		QuestionsBean qList = questionList.get(i);
	%>
	<div class="qBrock">
	
	

		<!--i行のデータから、問題番号を取得。テキストボックス内に表示-->
		<input type = "text" class = "questionNum" readonly = "readonly" name = "question_id[]" value = "<%=qList.getId()%>">
			<label> 	
				<!--i行のデータから、問題文を取得。テキストボックス内に表示-->
				<input type = "text" class = "questionTxt" readonly ="readonly" value = "<%=qList.getQuestion()%>">
			</label>

			<p>回答</p>
			<input type = "text" class = "AnswerTxt" name = "answer[]">
			
	
	
	</div>
	
	<%}%>

	</form>
	
	<div class = "bottomBtn">
		<!-- 採点ボタン -->
			<button type="submit" form="check" name = "user_name" value="<%=user_name%>">採点</button>
	</div>

</body>
</html>