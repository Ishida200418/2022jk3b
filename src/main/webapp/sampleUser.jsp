

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
</style>
</head>
<body>
	<%
	//--- データの取得
	SampleDataBean bean = (SampleDataBean) request.getAttribute("data");
	if (bean == null) {
		response.sendRedirect("displayall");
		return;
	}
	%>
	
	<%String status = (String) request.getAttribute("status");%>
	<h1>データの詳細</h1>
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
		class="buttonImage">編集</a>

	</p>
	<p>

	<a href="displayall" class="buttonImage">一覧へ戻る</a></p>
</body>
</html>