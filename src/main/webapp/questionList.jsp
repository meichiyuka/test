<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <jsp:include page="header.jsp" flush="true" />
<head>
<meta charset="UTF-8">
<title>QuestionList</title>
<link rel = "stylesheet" href = "question.css">
</head>
<body>

	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="login.QuestionsBean"%>
	<%@ page import="login.AnswersBean"%>

	<%
	request.setCharacterEncoding("UTF8");
	//問題文のリストを受け取る
	ArrayList<QuestionsBean> questionsList = (ArrayList<QuestionsBean>) request.getAttribute("qList");
	%>
	
	<%
	request.setCharacterEncoding("UTF8");
	//答えのリストを受け取る
	ArrayList<AnswersBean> answerList = (ArrayList<AnswersBean>) request.getAttribute("aList");
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
	
	<%
	//問題文リストを使ってループ
	for (int i = 0; i < questionsList.size(); i++) {
		//i行目のデータをqListに代入
		QuestionsBean qList = questionsList.get(i);
	%>
	<div class="qBrock">
	
		<label> 
			<!--i行のデータから、問題番号を取得。テキストボックス内に表示-->
			問題 <input type = "text" class = "questionNum" readonly = "readonly" value = "<%=qList.getId()%>">
			<!--i行のデータから、問題文を取得。テキストボックス内に表示-->
			<input type = "text" class = "questionTxt" readonly ="readonly" value = "<%=qList.getQuestion()%>">
		</label>
		
		<div class = "editBtn">
			<form action = "EditList" method = "POST" id = "edit">
				<button type="submit" name="edit" value="<%=qList.getId()%>">編集</button>
			</form>
	
			<form action="DeleatList" method="POST" id="del">
				<button type="submit" name="edit" value="<%=qList.getId()%>">削除</button>
			</form>
		</div>
	</div>
	
	<%
	//答えのリストを使ってループ
	for (int n = 0; n < answerList.size(); n++) {
		//n行目のデータをqListに代入
		AnswersBean aList = answerList.get(n);
		//答えDBのquestions_idと、問題文DBのidが一致しているかを判定
		if(aList.getQuestions_id() == qList.getId()){%>
		<div class="aBrock">
		<label> 
			<!--i行のデータから、問題DBと一致する解答番号を取得。テキストボックス内に表示-->
			答え<input type = "text" class = "questionNum" readonly = "readonly" value = "<%=aList.getQuestions_id()%>">
			<!--i行のデータから、答えを取得。テキストボックス内に表示-->
			<input type = "text" class = "answerTxt" readonly = "readonly" value = "<%=aList.getAnswer()%>">

		</label>
		</div>	
	
		<%}%>
	
	<%}%>

	<%}%>
	
</body>
</html>