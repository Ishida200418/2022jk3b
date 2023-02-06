<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.SampleDataBean"%>
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
<title>データ一覧</title>
<style type="text/css">
a {
	color: #3e3b72;
}

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

input[type="text"] {
	border: 2px solid #555555;
}

input[type="text"]:focus {
	border: 3px solid rgba(252, 102, 28,0.6);
	outline: 0;
}

input[type="password"] {
	border: 3px solid #999;
}

input[type="password"]:focus {
	border: 3px solid #ff9900;
	outline: 0;
}
</style>
</head>
<body>

	<div
		style="background: linear-gradient(45deg, rgb(56, 40, 104), rgb(252, 102, 28)),
		radial-gradient(rgba(255, 0, 0,0.9), rgba(0, 0, 0,0.9));">
<br>
		<header
				style="text-align:center;
  padding:5px 0;
				border: 1px solid #333333;
				 border-radius: 10px; background: rgba(255, 255, 255, 0.45); width: 40%; margin: auto;">
			<h1>データ 一覧</h1>
		</header>
		<br>
		<main>
			<%
			//--- 現在のページを取得
			int currentPage = (Integer) request.getAttribute("page");
			//--- 全ページ数を取得
			int allPage = (Integer) request.getAttribute("allpage");
			//--- キーワードを取得
			String keyword = (String) request.getAttribute("keyword");
			%>

			<div
				style="padding: 10px; margin-bottom: 10px;
				border: 1px solid #333333; border-radius: 10px;
				 background: rgba(255, 255, 255, 0.6);
				  width: 75%; margin: auto;">
				<!--
		<div
			style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;
			border-radius: 10px; background-color: #c9d2e5; width: 75%; margin: auto;">

			 -->

				<form class="formarea" method="get" action="select">
					キーワード <input type="text" name="keyword" value=<%=keyword%>><br>在籍（除外）<br>
					<input type="checkbox" name="status" value=0>在学 <input
						type="checkbox" name="status" value=1>休学<input
						type="checkbox" name="status" value=2>退学 <input
						type="checkbox" name="status" value=3>除籍 <br>
					<button type="submit" name="submit" value="search"
						style="background:rgba(195, 218, 247,0.8)">検 索</button>
					<!--
				<button type="submit" name="submit" value="search" style="background-color:#606bc9">検　　　索</button> -->

				</form>
				<br>

				<form class="formarea" method="get" action="select">
					<table style="background: rgba(255, 255, 255, 0.3)">
						<tr style="background: rgba(255, 244, 234, 0.54">
							<th>選択</th>
							<th>学籍番号</th>
							<th>学生氏名（漢字）</th>
							<th>学生ふりがな</th>
						</tr>
						<%
						//----- 受け取ったデータをテーブルに表示する
						int cnt = 0;
						List<SampleDataBean> data = (ArrayList) request.getAttribute("data");

						if (data != null) {
							for (SampleDataBean bean : data) {
								cnt++;
						%>
						<tr>
							<td><input type="radio" name="Student_ID_Number"
								value="<%=bean.getStudent_ID_Number()%>" id="radio<%=cnt%>"></td>
							<td><label for="radio<%=cnt%>"><a
									href="./user?Student_ID_Number=<%=bean.getStudent_ID_Number()%>">
									<img src="gjwa9joija9ojw.png" alt="<%=bean.getStudent_ID_Number()%>" width="13" height="13">
									<%=bean.getStudent_ID_Number()%>
									</a>
 </a></label></td>
							<td><label for="radio<%=cnt%>"><%=bean.getStudent_Name()%></label></td>
							<td><label for="radio<%=cnt%>"><%=bean.getStudent_Pronunciation()%></label></td>
						</tr>
						<%
						//----- 繰り返しを閉じるところ
						}
						}
						%>
					</table>
					<%
					if (keyword == null) {
						keyword = "";
					}
					//--- データが存在する場合にページの表示をする
					if (allPage > 0) {
						//--- 現在のページの２ページ前を表示の最初とするが、なければ１を最初とする。
						int startPage = currentPage - 2;

						if (startPage < 1) {
							startPage = 1;
						}
						//--- 開始ページの４ページ後を表示の最後とするが、無ければ最終ページとする。
						int endPage = startPage + 4;
						if (endPage > allPage) {
							endPage = allPage;
						}
						//--- 必ず５ページ分になるように、表示数の調整
						while (startPage > 1 && endPage - startPage < 4) {
							startPage--;
						}
						//--- "前へ"を表示 ただし、現在ページが１ならリンクは設定しない
						if (currentPage > 1) {
					%>
					<a class="linkStyle"
						href="displayall?page=<%=(currentPage - 1)%>&keyword=<%=keyword%>"
						style="color: #3e3b72;"> 前へ</a>
					<%
					} else {
					%>
					<span class="noLinkStyle">前へ</span>
					<%
					}
					//--- ページ表示 現在ページの場合はリンクは設定しない
					for (int i = startPage; i <= endPage; i++) {
					if (i == currentPage) {
					%>
					<span class="noLinkStyle"><%=i%></span>
					<%
					} else {
					%>
					<a class="linkStyle"
						href="displayall?page=<%=i%>&keyword=<%=keyword%>"
						style="color: #3e3b72;"><%=i%></a>
					<%
					}
					}
					//---- "へ"の表示 ただし、現在ページが最終ページならリンクは設定しない
					if (currentPage < allPage) {
					%>

					<a class="linkStyle"
						href="displayall?page=<%=(currentPage + 1)%>&keyword=<%=keyword%>"
						style="color: #3e3b72;"> 次へ</a>
					<%
					} else {
					%>
					<span class="noLinkStyle">次へ</span>
					<%
					}
					}
					%>
					<div class="buttonarea">
						<!-- <button type="submit" name="submit" value="delete">削除</button>
						<button type="submit" name="submit" value="insert"
							style="background:rgba(201, 210, 229,0.8)">新 規 登 録</button>
						<button type="submit" name="submit" value="update"
							style="background: rgba(201, 210, 229,0.8)">編 集</button> -->
						<!-- <button type="submit" name="submit" value="delete">削除</button> -->
						<button type="submit" name="submit" value="insert"
							style="background:rgba(195, 218, 247,0.8)">新 規 登 録</button>
						<button type="submit" name="submit" value="update"
							style="background: rgba(195, 218, 247,0.8)">編 集</button>

					</div>

				</form>
			</div>
		</main>
		<br> <br>
	</div>
</body>
</html>