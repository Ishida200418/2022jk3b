package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SampleDataBean;
import dao.SampleDAO;

@WebServlet("/updatego")
	public class SampleUpdateGo extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 

		public SampleUpdateGo() {
			super();
		}

	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");

	 //--- 変更ボタンクリック以外は一覧へ戻す
	 String submit = (String) request.getParameter("submit");
	 if (submit == null || !submit.equals("1")) {
	 response.sendRedirect("displayall");
	 return;
	 }

	 //--- エラーメッセージを格納する配列
	 List<String> list = new ArrayList();

	 //--- フォームデータの取得
	 SampleDataBean bean = new SampleDataBean();
	 String Student_ID_Number = request.getParameter("Student_ID_Number");
	 
	 String strSimei = request.getParameter("simei");

	 //--- ID の設定（エラーチェックもする）
	 try {
	 bean.setStudent_ID_Number(Integer.parseInt(Student_ID_Number));
	 } catch(Exception e) {
	 list.add("ID が数値でありません。");
	 }

	 //--- 氏名の設定（エラーチェックもする）
	 if (strSimei.isEmpty()) {
	 list.add("氏名の値が未設定になっています");
	 } else {
	 bean.setStudent_Name(strSimei);
	 }

	 //--- DAO の updateata を呼び出す。
	 if (list.size()== 0) {
	 SampleDAO dao = new SampleDAO();
	 int result = dao.updateDataName(bean);
	 if (result == 1) {
	 list.add("修正完了しました。");
	 } else {
	 list.add("修正できませんでした。");
	 }
	 }

	 //--- 結果表示のｊjsp へ遷移
	 request.setAttribute("message", list);
	 request.getRequestDispatcher("sampleUpdateGo.jsp").forward(request, response);
	 }
	}
