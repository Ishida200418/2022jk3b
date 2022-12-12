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
	<h1>データの修正</h1>
	<form method="get" action="updatego">
		<p>
			学籍番号：<%=bean.getStudent_ID_Number()%></p>
		<p>
			在籍状態<input type="number" name="Enrollment_Status"
				value="<%=bean.getEnrollment_Status()%>">
		</p>
		<p>
			在籍状態確定日<input type="date" name="Enrollment_Status_Date"
				value="<%=bean.getEnrollment_Status_Date()%>">
		</p>
		<p>
			学生氏名（漢字）<input type="text" name="Student_Name"
				value="<%=bean.getStudent_Name()%>">
		</p>
		<p>
			学生ふりがな<input type="text" name="Student_Pronunciation"
				value="<%=bean.getStudent_Pronunciation()%>">
		</p>
		<p>
			生年月日<input type="date" name="Date_of_birth"
				value="<%=bean.getDate_of_birth()%>">
		</p>
		<p>
			本人郵便番号<input type="text" name="Students_postal_code"
				value="<%=bean.getStudents_postal_code()%>">
		</p>
		<p>
			本人住所<input type="text" name="Students_address"
				value="<%=bean.getStudents_address()%>">
		</p>
		<p>
			本人電話番号<input type="text" name="Phone_number"
				value="<%=bean.getPhone_number()%>">
		</p>
		<p>
			本人メールアドレス（※）<input type="text" name="Individuals_mail_address"
				value="<%=bean.getIndividuals_mail_address()%>">
		</p>
		<p>
			保護者氏名（漢字）<input type="text" name="Guardians_name_in_Kanji"
				value="<%=bean.getGuardians_name_in_Kanji()%>">
		</p>
		<p>
			保護者ふりがな<input type="text" name="Guardians_Pronunciation"
				value="<%=bean.getGuardians_Pronunciation()%>">
		</p>
		<p>
			保護者郵便番号<input type="text" name="Guardians_postal_code"
				value="<%=bean.getGuardians_postal_code()%>">
		</p>
		<p>
			保護者住所<input type="text" name="Guardians_address"
				value="<%=bean.getGuardians_address()%>">
		</p>
		<p>
			保護者電話番号<input type="text" name="Parent_Guardian_Phone_Number"
				value="<%=bean.getParent_Guardian_Phone_Number()%>">
		</p>
		<p>
			保護者メールアドレス（※）<input type="text" name="Guardians_email_address"
				value="<%=bean.getGuardians_email_address()%>">
		</p>
		<input type="hidden" name="Student_ID_Number"
			value="<%=bean.getStudent_ID_Number()%>">
		<button type="submit" name="submit" value="1">変更</button>
		<button type="submit" name="submit" value="2">キャンセル</button>
		<button type="reset">リセット</button>
	</form>
</body>
</html>

