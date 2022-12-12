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

@WebServlet("/displayall")
public class SampleDisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SampleDisplayAll() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// --- get 送信されるページ番号を取得する 無ければ 1
		String strPage = (String) request.getParameter("page");
		
		int page = 1;
		if (strPage != null) {
			try {
				page = Integer.parseInt(strPage);
			} catch (Exception e) {
				page = 1;
			}
		}

		// --- キーワードを取得する
		String keyword = (String) request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		

		String status = (String) request.getParameter("status");
		if (status == null) {
			status = "";
		}
		System.out.println("status " +status);
		
		
		// SampleDAO のgetAllData メソッドを呼び出して全データを取り出し、list へ格納
		List<SampleDataBean> list = new ArrayList<SampleDataBean>();
		SampleDAO dao = new SampleDAO();
		list = dao.getAllData(page, keyword,status);
		// list をjsp へ送るための設定
		request.setAttribute("data", list);
		System.out.println("data " + list);
		// 現在のページを送る
		request.setAttribute("page", page);
		System.out.println("page " + page);
		// 総ページ数を送る
		request.setAttribute("allpage", dao.getMaxPage(keyword));
		System.out.println("allpage " + dao.getMaxPage(keyword));
		// キーワードを送る
		request.setAttribute("keyword", keyword);
		System.out.println("keyword " + keyword);

		// jsp へ遷移
		request.getRequestDispatcher("sampleDisplay.jsp").forward(request, response);
	}

}
