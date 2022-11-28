<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a {
	text-decoration: none;
	color: #000000;
}

.button {
	display: block;
	font-size: 16px;
	text-align: center;
	width: 100px;
	height: 30px;
	padding-top: 10px;
	border: solid 1px #777777;
	background-color: #cccccc;
	border-radius: 10px;
}

.button:hover {
	background-color: #eeeeee;
}
</style>
</head>
<body>
	<h1>削除処理</h1>
	<%
	//---- メッセージを取得します
	String message = (String) request.getAttribute("message");
	%>
	<p><%=message%></p>
	<a href="displayall"><span class="button">一覧へ戻る</span></a>
</body>
</html>