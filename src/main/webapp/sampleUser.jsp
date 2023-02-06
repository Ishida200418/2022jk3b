

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>データの詳細</title>
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

p {
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

	<div
		style="background: linear-gradient(45deg, rgb(56, 40, 104), rgb(252, 102, 28)),
		radial-gradient(rgba(255, 0, 0,0.9), rgba(0, 0, 0,0.9));">
<br>
	<%
	//--- データの取得
	SampleDataBean bean = (SampleDataBean) request.getAttribute("data");
	if (bean == null) {
		response.sendRedirect("displayall");
		return;
	}
	%>
	<%String status = (String) request.getAttribute("status");%>
	
		<header
				style="text-align:center;
  padding:5px 0;
				border: 1px solid #333333;
				 border-radius: 10px; background: rgba(255, 255, 255, 0.45); width: 50%; margin: auto;">
			<h1>データの詳細</h1>
		</header>
		<br>
	
	
		<div
			style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;
				 background: rgba(255, 255, 255, 0.6);
			 border-radius: 10px;width: 75%; margin: auto;">

	<p>
		学籍番号：<%=bean.getStudent_ID_Number()%></p>
	<p>
		在籍状態：<%=status%></p>
	<p>
		在籍状態確定日：<%=bean.getEnrollment_Status_Date()%>
	</p>
	<p>
		学生氏名（漢字）：<%=bean.getStudent_Name()%>
	</p>
	<p>
		学生ふりがな：<%=bean.getStudent_Pronunciation()%>
	</p>
	<p>
		生年月日：<%=bean.getDate_of_birth()%>
	</p>
	<p>
	<p>
		本人郵便番号：<%=bean.getStudents_postal_code()%>
	</p>
	<p>
		本人住所：<%=bean.getStudents_address()%>
	</p>
	<p>
		本人電話番号：<%=bean.getPhone_number()%>
	</p>
	<p>
		本人メールアドレス（※）：<%=bean.getIndividuals_mail_address()%>
	</p>
	<p>
		保護者氏名（漢字）：<%=bean.getGuardians_name_in_Kanji()%>
	</p>
	<p>
		保護者ふりがな：<%=bean.getGuardians_Pronunciation()%>
	</p>
	<p>
		保護者郵便番号：<%=bean.getGuardians_postal_code()%>
	</p>
	<p>
		保護者住所：<%=bean.getGuardians_address()%>
	</p>
	<p>
		保護者電話番号：<%=bean.getParent_Guardian_Phone_Number()%>
	</p>
	<p>
		保護者メールアドレス（※）：<%=bean.getGuardians_email_address()%>
	</p>


	<p>
		<a href="update?Student_ID_Number=<%=bean.getStudent_ID_Number()%>"
			class="buttonImage" style="background: rgba(195, 218, 247,0.8)">編　　　集</a>

		<a href="displayall" class="buttonImage" style="background: rgba(195, 218, 247,0.8)">一覧へ戻る</a>
	</p>
	
				</div>
				<br>
				</div>
</body>
</html>