<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editList</title>
<link rel = "stylesheet" href = "editList.css">
<script src="createList.js"></script>
</head>

<body>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="login.AnswersBean"%>
	<%
	request.setCharacterEncoding("UTF8");
	//問題Noを受け取る
	int editNo = (int)request.getAttribute("editNo");
	String editQ = (String) request.getAttribute("editQ");
	ArrayList<AnswersBean> editA = (ArrayList<AnswersBean>) request.getAttribute("editA");
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
	
	<label> 
		<!--EditListから受け取った問題Noをテキストボックスに表示したい-->
		No <input type = "text" class = "questionNum" readonly = "readonly" value = "<%=editNo%>">
	</label>


	<form action="EditList" method="POST" id="check">

		<div class="qBrock">
			<!--問題文を入力するテキストボックス -->
			<p>問題：</p>
			<input type="text" class="qTxt" name="qData" value="<%=editQ%>">
			<input type="hidden" class="qTxt" name="edit" value="<%=editNo%>">
			<input type="hidden" class="qTxt" name="editFlag" value="1">
		</div>

		<!--答えを入力する欄 -->
		<table id="answerTxt">
			<%
			//答えのリストを使ってループ
			for (int n = 0; n < editA.size(); n++) {
				//n行目のデータをqListに代入
				AnswersBean listA = editA.get(n);
				//答えDBのquestions_idと、問題文DBのidが一致しているかを判定
				if (listA.getQuestions_id() == editNo) {
			%>
			<div class="aBrock">
				<label> <!--i行のデータから、答えを取得。テキストボックス内に表示-->
					<p>答え：</p> 
					<input type="text" class="editAnswer" value="<%=listA.getAnswer()%>" name="data[]">

				</label>
			
			</div>

			<%}%>

			<%}%>
			
			<tr>
				<td></td>
			</tr>
			
		</table>

	</form>
	
	<div class = "bottomBtn1">
		<!--追加ボタン -->
		<input type = "button" value = "追加" id = "add" onclick = "coladd()">
	</div>

	<div class = "bottomBtn2">
		<!--確認ボタン -->
		<button type = "submit" form = "check">確認</button>
	</div>

	<div class = "bottomBtn3">
		<!--戻るボタン -->
		<input type = "button" value = "戻る">
	</div>


</body>
</html>