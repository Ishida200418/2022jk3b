package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SampleDataBean;
import dao.SampleDAO;

@WebServlet("/delete")
public class SampleDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SampleDelete() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	SampleDataBean bean = null; // 返却するデータ ダミーでnull を入れる
	boolean errflag = false; // エラーチェックの結果を格納する変数
	// --- 送信されたID を受け取る 存在しなければerrflag をtrue にする
	String Student_ID_Number = request.getParameter("Student_ID_Number");
	if (Student_ID_Number == null || Student_ID_Number == "") {
	    errflag = true;
	} else {
	    // --- id が存在するのでデータベースから取得するが、無ければerrflag をtrue にする
	    SampleDAO dao = new SampleDAO();
	    bean = dao.getOneRec(Integer.parseInt(Student_ID_Number));
	    if (bean == null) {
		errflag = true;
	    }
	}
	// --- エラーがあればdisplayall へ戻る
	if (errflag) {
	    response.sendRedirect("displayall");
	} else {
	    // --- 表示するデータを送る
	    request.setAttribute("data", bean);
	    request.getRequestDispatcher("sampleDelete.jsp").forward(request, response);
	}
    }

}
