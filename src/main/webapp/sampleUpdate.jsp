<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データの修正</title>
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

	<%String status = (String) request.getAttribute("status");%>
	<%
	//--- データの取得
	SampleDataBean bean = (SampleDataBean) request.getAttribute("data");
	if (bean == null) {
		response.sendRedirect("displayall");
		return;
	}
	%>
	<h1>データの修正</h1>
	
		<div
			style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;
			 border-radius: 10px; background-color: #ffffff; width: 75%; margin: auto;">
	
	<form method="get" action="updatego">
		<p>
			学籍番号：<br><%=bean.getStudent_ID_Number()%></p>

		<label>在籍状態：<br></label> <select name="Enrollment_Status"
			class=" form-select-lg mb-3" aria-label="Default select example" style="width: 75%">
			<option value="<%=bean.getEnrollment_Status()%>"><%=status%></option>
			<option value="0">在学</option>
			<option value="1">休学</option>
			<option value="2">退学</option>
			<option value="3">除籍</option>
		</select>

		<!-- 
		<p>
			在籍状態：<input type="number" name="Enrollment_Status"
				value="<%=bean.getEnrollment_Status()%>" min="0" max="3" required>
		</p>
		<p>
			在籍状態確定日：<input type="date" name="Enrollment_Status_Date"
				value="<%=bean.getEnrollment_Status_Date()%>" required>
		</p> -->
		<p>
			学生氏名（漢字）：<br><input type="text" name="Student_Name"
				value="<%=bean.getStudent_Name()%>" required style="width: 75%">
		</p>
		<p>
			学生ふりがな：<br><input type="text" name="Student_Pronunciation"
				value="<%=bean.getStudent_Pronunciation()%>" required style="width: 75%"
				pattern="[\u3041-\u3096]*">
		</p>
		<p>
			生年月日：<br><input type="date" name="Date_of_birth"
				value="<%=bean.getDate_of_birth()%>" required style="width: 75%">
		</p>
		<p>
			本人郵便番号：<br><input type="text" name="Students_postal_code"
				value="<%=bean.getStudents_postal_code()%>" required style="width: 75%">
		</p>
		<p>
			本人住所：<br><input type="text" name="Students_address"
				value="<%=bean.getStudents_address()%>" required maxlength="7" style="width: 75%">
		</p>
		<p>
			本人電話番号：<br><input type="text" name="Phone_number"
				value="<%=bean.getPhone_number()%>" required style="width: 75%">
		</p>
		<p>
			本人メールアドレス（※）：<br><input type="text" name="Individuals_mail_address"
				value="<%=bean.getIndividuals_mail_address()%>" style="width: 75%">
		</p>
		<p>
			保護者氏名（漢字）：<br><input type="text" name="Guardians_name_in_Kanji"
				value="<%=bean.getGuardians_name_in_Kanji()%>" required style="width: 75%">
		</p>
		<p>
			保護者ふりがな：<br><input type="text" name="Guardians_Pronunciation"
				value="<%=bean.getGuardians_Pronunciation()%>" required style="width: 75%"
				pattern="[\u3041-\u3096]*">
		</p>
		<p>
			保護者郵便番号：<br><input type="text" name="Guardians_postal_code"
				value="<%=bean.getGuardians_postal_code()%>" required maxlength="7" style="width: 75%">
		</p>
		<p>
			保護者住所：<br><input type="text" name="Guardians_address"
				value="<%=bean.getGuardians_address()%>" required style="width: 75%">
		</p>
		<p>
			保護者電話番号：<br><input type="text" name="Parent_Guardian_Phone_Number"
				value="<%=bean.getParent_Guardian_Phone_Number()%>" required style="width: 75%">
		</p>
		<p>
			保護者メールアドレス（※）：<br><input type="text" name="Guardians_email_address"
				value="<%=bean.getGuardians_email_address()%>" style="width: 75%">
		</p>
		<input type="hidden" name="Student_ID_Number"
			value="<%=bean.getStudent_ID_Number()%>">
		<button type="submit" name="submit" value="1" style="background-color: #c9d2e5">変　　　更</button>
		<button type="submit" name="submit" value="2" style="background-color: #c9d2e5">キャンセル</button>
		<button type="reset" style="background-color: #c9d2e5">リセット</button>
	</form>
	
				</div>
</body>
</html>

