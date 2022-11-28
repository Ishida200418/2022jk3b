<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<a href="displayall" class="buttonImage">一覧へ戻る</a>
</body>
</html>