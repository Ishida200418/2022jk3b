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

@WebServlet("/Dbtest")
public class Dbtest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Dbtest() {
		super();
	}

	// --- get 送信されるページ番号を取得する 無ければ 1
	int page = 1;
	String keyword = "";
	String status = "";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		try {
			List<SampleDataBean> b = new ArrayList<SampleDataBean>();
			SampleDAO sb = new SampleDAO();
			b = sb.getAllData(page, keyword,status);
			for(SampleDataBean x : b) {
				response.getWriter().print(x.getStudent_ID_Number() + "," + x.getStudent_Name() + "," + x.getStudent_Name() + "<br>");
			}	
		} catch(Exception e) {
			response.getWriter().print(e.getMessage());
		}
		
		
	}
}

