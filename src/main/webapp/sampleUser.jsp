

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h1>データの詳細</h1>
		<p>
			学籍番号：<%=bean.getStudent_ID_Number()%></p>
		<p>
			在籍状態:<%=bean.getEnrollment_Status()%></p>
		<p>
			在籍状態確定日:<%=bean.getEnrollment_Status_Date()%>>
		</p>
		<p>
			学生氏名（漢字）:<%=bean.getStudent_Name()%>>
		</p>
		<p>
			学生ふりがな:<%=bean.getStudent_Pronunciation()%>>
		</p>
		<p>
			生年月日:<%=bean.getDate_of_birth()%>>
		</p>
		<p>
		<p>
			本人郵便番号:<%=bean.getStudents_postal_code()%>>
		</p>
		<p>
			本人住所:<%=bean.getStudents_address()%>>
		</p>
		<p>
			本人電話番号:<%=bean.getPhone_number()%>>
		</p>
		<p>
			本人メールアドレス（※）:<%=bean.getIndividuals_mail_address()%>>
		</p>
		<p>
			保護者氏名（漢字）:<%=bean.getGuardians_name_in_Kanji()%>>
		</p>
		<p>
			保護者ふりがな:<%=bean.getGuardians_Pronunciation()%>>
		</p>
		<p>
			保護者郵便番号:<%=bean.getGuardians_postal_code()%>>
		</p>
		<p>
			保護者住所:<%=bean.getGuardians_address()%>>
		</p>
		<p>
			保護者電話番号:<%=bean.getParent_Guardian_Phone_Number()%>>
		</p>
		<p>
			保護者メールアドレス（※）:<%=bean.getGuardians_email_address()%>>
		</p>
		
		
		
		
		
		
	<a href="update?Student_ID_Number=<%=bean.getStudent_ID_Number()%>" class="buttonImage">編集</a>
		
		
	<a href="displayall" class="buttonImage">一覧へ戻る</a>
</body>
</html>