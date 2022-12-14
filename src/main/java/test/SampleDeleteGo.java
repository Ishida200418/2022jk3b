package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SampleDAO;

@WebServlet("/deletego")
public class SampleDeleteGo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SampleDeleteGo() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	// --- ボタンのvalue を取得して、１以外なら一覧へ戻る
	String check = request.getParameter("check");
	if (!check.equals("1")) {
	    response.sendRedirect("displayall");
	} else {
	    // --- 削除処理を呼び出す
		String Student_ID_Number = request.getParameter("Student_ID_Number");
	    SampleDAO dao = new SampleDAO();
	    int result = dao.deleteData(Integer.parseInt(Student_ID_Number));
	    // --- 結果表示をするJSP へのメッセージを構築して遷移する
	    String resultMessage = "";
	    if (result == 0) {
		resultMessage = "削除できませんでした";
	    } else {
		resultMessage = "削除が完了しました";
	    }
	    request.setAttribute("message", resultMessage);
	    request.getRequestDispatcher("sampleDeleteGo.jsp").forward(request, response);
	}
	

    }

}
