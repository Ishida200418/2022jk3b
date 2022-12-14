<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>削除の確認</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table, th, td {
	border: solid 1px #000000;
}

table {
	margin: auto;
}

td {
	text-align: center;
}

h2 {
	text-align: center;
}

form {
	text-align: center;
}
th, td {
	padding: 5px;
}

h1 {

	text-align: center;
}

.formarea {
	margin-left: 30px;
}

.buttonarea {
	margin-top: 20px;
}

.linkStyle {
	display: inline-block;
	padding: 10px;
	color: #0000ff;
}

.noLinkStyle {
	display: line-block;
	padding: 10px;
	color: #999999;
}
</style>
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