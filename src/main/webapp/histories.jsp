<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="header.jsp" flush="true" />
<head>
<meta charset="UTF-8">
<title>histories</title>
<link rel = "stylesheet" href = "histories.css">
</head>
<body>
	
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="login.HistoriesBean"%>
	
	<%
	request.setCharacterEncoding("UTF8");
	//履歴のリストを受け取る
	ArrayList<HistoriesBean> historiesList = (ArrayList<HistoriesBean>) request.getAttribute("historiesList");
	//ユーザーIDを受け取る
	int users_id = (int)request.getAttribute("users_id");
	//ユーザー名を受け取る
	String user_name = (String)request.getAttribute("user_name");
	%>
	
	<h3>履歴</h3>
	
	<table class = "result">
		<tr>
			<th>氏名</th>
			<th>得点</th>
			<th>採点時間</th>
		</tr>
	
	<%
	//履歴リストを使ってループ
	for (int i = 0; i < historiesList.size(); i++) {
		//i行目のデータをhistoriesBeanに代入
		HistoriesBean historiesBean = historiesList.get(i);
		if(users_id == historiesBean.getUserId()){
		
	%>
	
		<tr>
			<td><%=user_name%></td>
			<td><%=historiesBean.getPoint()%></td>
			<td><%=historiesBean.getCreatedAt()%></td>		
		</tr>
		
	<%}%>
		<%}%>
		
	</table>


</body>
</html>