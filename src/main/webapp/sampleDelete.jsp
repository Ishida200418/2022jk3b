<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除の確認</title>
</head>
<body>
	<h1>削除処理</h1>
	<%
	SampleDataBean bean = (SampleDataBean) request.getAttribute("data");
	%>
	<p>
		ID
		<%=bean.getStudent_ID_Number()%></p>
	<p>
		氏名
		<%=bean.getStudent_Name()%></p>
	<p>
		氏名
		<%=bean.getStudent_Pronunciation()%></p>
	<p>このデータを削除しますか？</p>
	<form method="get" action="deletego">
		<input type="hidden" name="id"
			value="<%=bean.getStudent_ID_Number()%>">
		<button type="submit" name="check" value="1">削除する</button>
		<button type="submit" name="check" value="2">キャンセル</button>
	</form>
</body>
</html>