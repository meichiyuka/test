<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
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
	ArrayList<QuestionsBean> qlist = (ArrayList<QuestionsBean>) request.getAttribute("qList");
	%>
	
	<%
	request.setCharacterEncoding("UTF8");
	//答えのリストを受け取る
	ArrayList<AnswersBean> alist = (ArrayList<AnswersBean>) request.getAttribute("aList");
	%>
	
	<%
	//問題文リストを使ってループ
	for (int i = 0; i < qlist.size(); i++) {
		//i行目のデータをqListに代入
		QuestionsBean qList = qlist.get(i);
	%>
	<div class="qBrock">
		<label> 
			<!--i行のデータから、問題番号を取得。テキストボックス内に表示-->
			問題 <input type = "text" class = "questionNum" readonly = "readonly" value = "<%=qList.getId()%>">
			<!--i行のデータから、問題文を取得。テキストボックス内に表示-->
			<input type = "text" class = "questionTxt" readonly ="readonly" value = "<%=qList.getQuestion()%>">
			<input type = "button" value="編集">
			<input type = "button" value="削除">

		</label>
	</div>
	
	<%
	//答えのリストを使ってループ
	for (int n = 0; n < alist.size(); n++) {
		//n行目のデータをqListに代入
		AnswersBean aList = alist.get(n);
		//答えDBのquestions_idと、問題文DBのidが一致しているかを判定
		if(aList.getQuestions_id() == qList.getId()){%>
		<div class="aBrock">
		<label> 
			<!--i行のデータから、問題DBと一致する解答番号を取得。テキストボックス内に表示-->
			答え<input type = "text" class = "questionNum" readonly = "readonly" value = "<%=aList.getQuestions_id()%>">
			<!--i行のデータから、答えを取得。テキストボックス内に表示-->
			<input type = "text" class = "answerTxt" readonly = "readonly" value = "<%=aList.getAnswer()%>">
			<input type = "button" value="編集">
			<input type = "button" value="削除">
		</label>
		</div>	
	
		<%}%>
	
	<%}%>

	<%}%>
	
</body>
</html>