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
//　コメント
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

//		String status = (String) request.getParameter("status");

		String[] status = request.getParameterValues("status");

//		if (status == null) {
//			status = "";
//		}

		if (status != null) {
			System.out.println("status " + status);

			System.out.println("要素数 = " + status.length);
		}

		int num = 0;

		ArrayList<Integer> hoge = new ArrayList<Integer>();

		int count = 0;
		if (status != null) {
			for (String lan : status) {
				System.out.println("lan " + lan);
				hoge.add(Integer.parseInt(lan));
			}
			count = status.length;
		}

		while (count < 4) {
			System.out.println("v = " + count);
			hoge.add(4);
			count += 1;
		}

		System.out.println("hoge = " + hoge.size());

		// SampleDAO のgetAllData メソッドを呼び出して全データを取り出し、list へ格納
		List<SampleDataBean> list = new ArrayList<SampleDataBean>();
		SampleDAO dao = new SampleDAO();
//		list = dao.getAllData(page, keyword, status);
		list = dao.getAllData(page, keyword, hoge);
		// list をjsp へ送るための設定
		request.setAttribute("data", list);
		System.out.println("data " + list);
		// 現在のページを送る
		request.setAttribute("page", page);
		System.out.println("page " + page);
		// 総ページ数を送る
		if (status != null) {
			request.setAttribute("allpage", dao.getMaxPage2(keyword, hoge));
			System.out.println("allpage " + dao.getMaxPage2(keyword, hoge));

//			request.setAttribute("allpage", dao.getMaxPage(keyword));
//			System.out.println("allpage " + dao.getMaxPage(keyword));
		} else {

			request.setAttribute("allpage", dao.getMaxPage2(keyword, hoge));
			System.out.println("allpage " + dao.getMaxPage2(keyword, hoge));

//			request.setAttribute("allpage", dao.getMaxPage(keyword));
//			System.out.println("allpage " + dao.getMaxPage(keyword));
		}
		// キーワードを送る
		request.setAttribute("keyword", keyword);
		System.out.println("keyword " + keyword);

		// jsp へ遷移
		request.getRequestDispatcher("sampleDisplay.jsp").forward(request, response);
	}

}
