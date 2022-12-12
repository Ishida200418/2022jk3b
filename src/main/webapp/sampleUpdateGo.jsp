<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>追加の確認</title>
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
<style type="text/css">
a {
	text-decoration: none;
}

.buttonImage {
	display: inline-block;
	font-size: 0.8em;
	background-color: #eeeeee;
	border: solid 1px #333333;
	border-radius: 3px;
	color: #000000;
	width: fit-content;
	padding: 2px 5px;
	text-align: center;
	text-decoration: none;
	cursor: arrow;
}

.buttonImage:hover {
	background-color: #dddddd;
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
p {
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
	<%
	List<String> list = (ArrayList<String>) request.getAttribute("message");
	if (list != null) {
		for (String message : list) {
	%>
	<p><%=message%></p>
	<%
	} //--- for を閉じるカッコ
	} //--- if を閉じるカッコ
	%>
	<p><a href="displayall" class="buttonImage">一覧へ戻る</a></p>
</body>
</html>