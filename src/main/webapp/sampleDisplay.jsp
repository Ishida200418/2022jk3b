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
	<header>
		<h1>データ一覧</h1>
	</header>
	<main>
		<form class="formarea" method="get" action="select">
			キーワード <input type="text" name="keyword"> 在籍 <select
				name="status" class=" form-select-lg mb-3"
				aria-label="Default select example">


				<option value="">全て</option>
				<option value="0">在学</option>
				<option value="1">休学</option>
				<option value="2">退学</option>
				<option value="3">除籍</option>
			</select>
			<button type="submit" name="submit" value="search">検索</button>
		</form>
		<form class="formarea" method="get" action="select">
			<table>
				<tr>
					<th>選択</th>
					<th>学籍番号</th>
					<th>学生氏名（漢字）</th>
					<th>学生ふりがな</th>
				</tr>
				<%
				//----- 受け取ったデータをテーブルに表示する
				int cnt = 0;
				List<SampleDataBean> data = (ArrayList) request.getAttribute("data");
				for (SampleDataBean bean : data) {
					cnt++;
				%>
				<tr>
					<td><input type="radio" name="Student_ID_Number"
						value="<%=bean.getStudent_ID_Number()%>" id="radio<%=cnt%>"></td>
					<td><label for="radio<%=cnt%>"><a
							href="./user?Student_ID_Number=<%=bean.getStudent_ID_Number()%>"><%=bean.getStudent_ID_Number()%></a></label></td>
					<td><label for="radio<%=cnt%>"><%=bean.getStudent_Name()%></label></td>
					<td><label for="radio<%=cnt%>"><%=bean.getStudent_Pronunciation()%></label></td>
				</tr>
				<%
				//----- 繰り返しを閉じるところ
				}
				%>
			</table>
			<%
			//--- 現在のページを取得
			int currentPage = (Integer) request.getAttribute("page");
			//--- 全ページ数を取得
			int allPage = (Integer) request.getAttribute("allpage");
			//--- キーワードを取得
			String keyword = (String) request.getAttribute("keyword");
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
				href="displayall?page=<%=(currentPage - 1)%>&keyword=<%=keyword%>">
				前へ</a>
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
				href="displayall?page=<%=i%>&keyword=<%=keyword%>"><%=i%></a>
			<%
			}
			}
			//---- "へ"の表示 ただし、現在ページが最終ページならリンクは設定しない
			if (currentPage < allPage) {
			%>
			<a class="linkStyle"
				href="displayall?page=<%=(currentPage + 1)%>&keyword=<%=keyword%>">
				次へ</a>
			<%
			} else {
			%>
			<span class="noLinkStyle">次へ</span>
			<%
			}
			}
			%>
			<div class="buttonarea">
				<!-- <button type="submit" name="submit" value="delete">削除</button> -->
				<button type="submit" name="submit" value="insert">新規登録</button>
				<button type="submit" name="submit" value="update">編集</button>

			</div>
		</form>
	</main>
</body>
</html>