<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>データ削除処理</title>
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
h1 {
	text-align: center;
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
</style>
</head>
<body>
	<h1>データ削除処理</h1>
	<%
	//---- メッセージを取得します
	String message = (String) request.getAttribute("message");
	%>
	<p><%=message%></p>
	<a href="displayall"><span class="button">一覧へ戻る</span></a>
</body>
</html>