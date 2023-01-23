package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SampleDataBean;
import dao.SampleDAO;

@WebServlet("/update")
public class SampleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SampleUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		SampleDataBean bean = null;
		boolean errflag = false;

		// --- 送信された ID を受け取る 存在しなければ displayall へ戻る
		String Student_ID_Number = request.getParameter("Student_ID_Number");

		System.out.println("Student_ID_Number   " + Student_ID_Number);
		if (Student_ID_Number == null || Student_ID_Number == "") {
			errflag = true;
		} else {
			// --- データベースから該当するデータを取得する 無ければ displayall へ戻る
			SampleDAO dao = new SampleDAO();
			bean = dao.getOneRec(Integer.parseInt(Student_ID_Number));
			if (bean == null) {
				errflag = true;
			}
			
		}

		
		String status="";
		try{
			if (bean.getEnrollment_Status() == 0 ) {
				status = "在学";
			}else if (bean.getEnrollment_Status() == 1) {
				status = "休学";
			}else if (bean.getEnrollment_Status() == 2) {
				status = "退学";
				
			}else if (bean.getEnrollment_Status() == 3) {
				status = "除籍";
			}
			}
			catch(Exception e){
				request.getRequestDispatcher("displayall").forward(request, response);
				return;
			}
		// --- エラーがあれば displayall へ戻る
		if (errflag) {
			request.getRequestDispatcher("displayall").forward(request, response);
			return;
		} else {

			// --- 更新用のフォームを呼び出す
			request.setAttribute("status", status);
			request.setAttribute("data", bean);
			request.getRequestDispatcher("sampleUpdate.jsp").forward(request, response);
		}
	}

}
