package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SampleDataBean;
import dao.SampleDAO;

@WebServlet("/insert")
public class SampleInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SampleInsert() {
		super();
	}

	static public final String DATE_PATTERN = "yyyy-MM-dd";

	public static String parseDateToString(Date date) {
		String str;
		if (date == null) {
			str = null;
		} else {
			str = new SimpleDateFormat(DATE_PATTERN).format(date);
		}
		return str;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		boolean errSw = false; // 送信されたデータに誤りがあればtrue にする

//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/mm/dd");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strId = request.getParameter("Student_ID_Number");
		String strEnrollment_Status = request.getParameter("Enrollment_Status");

		System.out.println("Enrollment_Status_Date  " + request.getParameter("Enrollment_Status_Date"));

		String d1 = "";
		Date Enrollment_Status_Date = null;
		try {
//			Enrollment_Status_Date = sdFormat.parse(request.getParameter("Enrollment_Status_Date"));
			Date d = new Date();
			d1 = parseDateToString(d);
			Enrollment_Status_Date = sdFormat.parse(d1);

			System.out.println("Enrollment_Status_Date  " + Enrollment_Status_Date);
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			response.getWriter().println("<p>登録日が入力されていません</p>");
			errSw = true;
		}
		String Student_Name = request.getParameter("Student_Name");
		String Student_Pronunciation = request.getParameter("Student_Pronunciation");
		Date Date_of_birth = null;
		try {
			Date_of_birth = sdFormat.parse(request.getParameter("Date_of_birth"));
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			response.getWriter().println("<p>誕生日が入力されていません</p>");
			errSw = true;
		}

		Calendar now = Calendar.getInstance();
		String today = sdFormat.format(now.getTime());
		if (request.getParameter("Date_of_birth").compareTo(today) < 0) {
			// 日付が翌日以降ならエラーなし
		} else {
			response.getWriter().println("<p>誕生日の値が適切ではありません。</p>");
		}

		String Students_postal_code = request.getParameter("Students_postal_code");
		String Students_address = request.getParameter("Students_address");
		String Phone_number = request.getParameter("Phone_number");
		String Individuals_mail_address = request.getParameter("Individuals_mail_address");
		String Guardians_name_in_Kanji = request.getParameter("Guardians_name_in_Kanji");
		String Guardians_Pronunciation = request.getParameter("Guardians_Pronunciation");
		String Guardians_postal_code = request.getParameter("Guardians_postal_code");
		String Guardians_address = request.getParameter("Guardians_address");
		String Parent_Guardian_Phone_Number = request.getParameter("Parent_Guardian_Phone_Number");
		String Guardians_email_address = request.getParameter("Guardians_email_address");

		List<String> message = new ArrayList<String>(); // ---メッセージ格納用配列
		// --- エラーチェック
		int Student_ID_Number = -1; // ダミーの値をとりあえず入れておく
		int Enrollment_Status = -1; // ダミーの値をとりあえず入れておく

		// --- 番号が空か、および値が数値かを判断
		if (strId == null || strId == "") {
			response.getWriter().println("<p>学籍番号が入力されていません</p>");
			errSw = true;
		} else {
			try {
				Student_ID_Number = Integer.parseInt(strId);
			} catch (Exception e) {
				response.getWriter().println("<p>学籍番号が数字ではありません</p>");
				errSw = true;
			}
		}
		
		// --- 番号が空か、および値が数値かを判断
		if (strEnrollment_Status == null || strEnrollment_Status == "") {
			response.getWriter().println("<p>在籍状態が入力されていません</p>");
			errSw = true;
		} else {
			try {
				Enrollment_Status = Integer.parseInt(strEnrollment_Status);
			} catch (Exception e) {
				response.getWriter().println("<p>在籍状態が数字ではありません</p>");
				errSw = true;
			}
		}
		if (Student_Name == null || Student_Name == "") {
			response.getWriter().println("<p>学生氏名（漢字）が入力されていません</p>");
			errSw = true;
		}
		if (Student_Pronunciation == null || Student_Pronunciation == "") {
			response.getWriter().println("<p>学生ふりがなが入力されていません</p>");
			errSw = true;
		}
		if (Students_postal_code == null || Students_postal_code == "") {
			response.getWriter().println("<p>本人郵便番号が入力されていません</p>");
			errSw = true;
		}
		String strPattern = "^[0-9]{7}$";
		Pattern p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		Matcher m = p.matcher(Students_postal_code); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("本人郵便番号が一致します");
		} else {
			System.out.println("本人郵便番号が不一致です");
			response.getWriter().println("<p>本人郵便番号が不一致です");
			errSw = true;
		}
		if (Students_address == null || Students_address == "") {
			response.getWriter().println("<p>本人住所が入力されていません</p>");
			errSw = true;
		}
		if (Phone_number == null || Phone_number == "") {
			response.getWriter().println("<p>本人電話番号が入力されていません</p>");
			errSw = true;
		}
		strPattern = "^[0-9][0-9¥¥-]*$"; /* 正規表現文字列 */
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Phone_number); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("本人電話番号が一致します");
		} else {
			System.out.println("本人電話番号が不一致です");
			response.getWriter().println("<p>本人電話番号が不一致です");
			errSw = true;
		}

		if (Individuals_mail_address == null || Individuals_mail_address == "") {
			response.getWriter().println("<p>本人メールアドレス（※）が入力されていません</p>");
			errSw = true;
		}
		if (Guardians_name_in_Kanji == null || Guardians_name_in_Kanji == "") {
			response.getWriter().println("<p>保護者氏名（漢字）が入力されていません</p>");
			errSw = true;
		}
		if (Guardians_Pronunciation == null || Guardians_Pronunciation == "") {
			response.getWriter().println("<p>保護者ふりがなが入力されていません</p>");
			errSw = true;
		}
		if (Guardians_postal_code == null || Guardians_postal_code == "") {
			response.getWriter().println("<p>保護者郵便番号が入力されていません</p>");
			errSw = true;
		}
		strPattern = "^[0-9]{7}$";
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Guardians_postal_code); /* 正	規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("保護者郵便番号が一致します");
		} else {
			System.out.println("保護者郵便番号が不一致です");
			response.getWriter().println("<p>保護者郵便番号が不一致です");
			errSw = true;
		}
		if (Guardians_address == null || Guardians_address == "") {
			response.getWriter().println("<p>保護者住所が入力されていません</p>");
			errSw = true;
		}
		if (Parent_Guardian_Phone_Number == null || Parent_Guardian_Phone_Number == "") {
			response.getWriter().println("<p>保護者電話番号が入力されていません</p>");
			errSw = true;
		}
		strPattern = "^[0-9][0-9¥¥-]*$"; /* 正規表現文字列 */
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Parent_Guardian_Phone_Number); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("保護者電話番号が一致します");
		} else {
			System.out.println("保護者電話番号が不一致です");
			response.getWriter().println("<p>保護者電話番号が不一致です");
			errSw = true;
		}
		if (Guardians_email_address == null || Guardians_email_address == "") {
			response.getWriter().println("<p>保護者メールアドレス（※）が入力されていません</p>");
			errSw = true;
		}
		// --- 送信されたデータにエラーが無ければ登録する
		if (!errSw) {
			// --- すでに登録済みの id かを調べる。
			SampleDAO dao = new SampleDAO();
			SampleDataBean bean = dao.getOneRec(Student_ID_Number);
			if (bean == null) {
				bean = new SampleDataBean();
				bean.setStudent_ID_Number(Student_ID_Number);
				System.out.println("Student_ID_Number  " + Student_ID_Number);
				bean.setEnrollment_Status(Enrollment_Status);
				System.out.println("Enrollment_Status  " + Enrollment_Status);
//				bean.setEnrollment_Status_Date(request.getParameter("Enrollment_Status_Date"));
				bean.setEnrollment_Status_Date(d1);

				System.out.println("d1  " + d1);
				System.out.println("Enrollment_Status_Date  " + Enrollment_Status_Date);
				System.out.println("request.getParameter(\"Enrollment_Status_Date\")  "
						+ request.getParameter("Enrollment_Status_Date"));
				bean.setStudent_Name(Student_Name);
				System.out.println("Student_Name  " + Student_Name);
				bean.setStudent_Pronunciation(Student_Pronunciation);
				System.out.println("Student_Pronunciation  " + Student_Pronunciation);
				bean.setDate_of_birth(request.getParameter("Date_of_birth"));
				System.out.println("request.getParameter(\"Date_of_birth\")  " + request.getParameter("Date_of_birth"));
				bean.setStudents_postal_code(Students_postal_code);
				System.out.println("Students_postal_code  " + Students_postal_code);
				bean.setStudents_address(Students_address);
				System.out.println("Students_address  " + Students_address);
				bean.setPhone_number(Phone_number);
				System.out.println("Phone_number  " + Phone_number);
				bean.setIndividuals_mail_address(Individuals_mail_address);
				System.out.println("Individuals_mail_address  " + Individuals_mail_address);
				bean.setGuardians_name_in_Kanji(Guardians_name_in_Kanji);
				System.out.println("Guardians_name_in_Kanji  " + Guardians_name_in_Kanji);
				bean.setGuardians_Pronunciation(Guardians_Pronunciation);
				System.out.println("Guardians_Pronunciation  " + Guardians_Pronunciation);
				bean.setGuardians_postal_code(Guardians_postal_code);
				System.out.println("Guardians_postal_code  " + Guardians_postal_code);
				bean.setGuardians_address(Guardians_address);
				System.out.println("Guardians_email_address  " + Guardians_email_address);
				bean.setParent_Guardian_Phone_Number(Parent_Guardian_Phone_Number);
				System.out.println("Parent_Guardian_Phone_Number  " + Parent_Guardian_Phone_Number);
				bean.setGuardians_email_address(Guardians_email_address);
				System.out.println("Guardians_email_address  " + Guardians_email_address);

				int result = dao.insertData(bean);
				if (result == 0) {
					message.add("登録できませんでした。");
				} else {
					message.add("登録完了しました。");
				}

			} else {
				message.add("ID が重複しています。");
			}
		}

		// --- メッセージ表示用の jsp へ遷移
		request.setAttribute("message", message);
		request.getRequestDispatcher("sampleInsert.jsp").forward(request, response);
	}

}
