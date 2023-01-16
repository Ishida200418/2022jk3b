package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@WebServlet("/updatego")
public class SampleUpdateGo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SampleUpdateGo() {
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

		// --- 変更ボタンクリック以外は一覧へ戻す
		String submit = (String) request.getParameter("submit");
		if (submit == null || !submit.equals("1")) {
			response.sendRedirect("displayall");
			return;
		}
		

		// --- エラーメッセージを格納する配列
		List<String> list = new ArrayList();

		SampleDAO dao = new SampleDAO();
		String strId = request.getParameter("Student_ID_Number");
		SampleDataBean bean = dao.getOneRec(Integer.parseInt(strId));
		String strSimei = request.getParameter("Student_Name");
		boolean errSw = false; // 送信されたデータに誤りがあればtrue にする

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strEnrollment_Status = request.getParameter("Enrollment_Status");
		Date Enrollment_Status_Date = null;
		try {

			String d1 = "";
			Date d = new Date();
			d1 = parseDateToString(d);
			Enrollment_Status_Date = sdFormat.parse(d1);

			System.out.println("d1  " + d1);
			System.out.println("Enrollment_Status_Date  " + Enrollment_Status_Date);
			bean.setEnrollment_Status_Date(d1);

		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			response.getWriter().println("<p>在籍状態確定日が入力されていません</p>");
			errSw = true;
		}

		String Student_Name = request.getParameter("Student_Name");
		String Student_Pronunciation = request.getParameter("Student_Pronunciation");
		Date Date_of_birth = null;
		try {
			Date_of_birth = sdFormat.parse(request.getParameter("Date_of_birth"));
//			bean.setEnrollment_Status_Date(sdFormat.parse(request.getParameter("Enrollment_Status_Date")));
			String str = new SimpleDateFormat("yyyy-MM-dd").format(Date_of_birth);
			bean.setDate_of_birth(str);
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			response.getWriter().println("<p>誕生日が入力されていません</p>");
			errSw = true;
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

		System.out.println("----------------------------------------------------------------- ");
		// --- ID の設定（エラーチェックもする）
		try {
			System.out.println("bean.getStudent_ID_Number  " + bean.getStudent_ID_Number());
			System.out.println("strId  " + strId);
			bean.setStudent_ID_Number(Integer.parseInt(strId));
		} catch (Exception e) {
			System.out.println("e  " + e);
			System.out.println("bean.getStudent_ID_Number  " + bean.getStudent_ID_Number());
			System.out.println("strId  " + strId);
			list.add("学籍番号 が数値でありません。");
		}

		try {
			System.out.println("bean.getEnrollment_Status  " + bean.getEnrollment_Status());
			System.out.println("strEnrollment_Status  " + strEnrollment_Status);
			bean.setEnrollment_Status(Integer.parseInt(strEnrollment_Status));
		} catch (Exception e) {
			System.out.println("e  " + e);
			System.out.println("bean.getEnrollment_Status  " + bean.getEnrollment_Status());
			System.out.println("strEnrollment_Status  " + strEnrollment_Status);
			list.add("在籍状態 が数値でありません。");
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Student_Name.isEmpty()) {
			list.add("学生氏名（漢字）の値が未設定になっています");
		} else {
			System.out.println("bean.getStudent_Name  " + bean.getStudent_Name());
			System.out.println("Student_Name  " + Student_Name);
			bean.setStudent_Name(Student_Name);
		}
		// --- 氏名の設定（エラーチェックもする）
		if (Student_Pronunciation.isEmpty()) {
			list.add("学生ふりがなの値が未設定になっています");
		} else {
			System.out.println("bean.getStudent_Pronunciation  " + bean.getStudent_Pronunciation());
			System.out.println("Student_Pronunciation  " + Student_Pronunciation);
			bean.setStudent_Pronunciation(Student_Pronunciation);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Students_postal_code.isEmpty()) {
			list.add("本人郵便番号の値が未設定になっています");
		} else {
			System.out.println("bean.getStudents_postal_code  " + bean.getStudents_postal_code());
			System.out.println("Students_postal_code  " + Students_postal_code);
			bean.setStudents_postal_code(Students_postal_code);
		}

		String strPattern = "^[0-9]{7}$";
		Pattern p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		Matcher m = p.matcher(Students_postal_code); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("本人郵便番号が一致します");
		} else {
			list.add("本人郵便番号が不一致です");
			System.out.println("本人郵便番号が不一致です");
			System.out.println("Students_postal_code" + Students_postal_code);
			response.getWriter().println("<p>本人郵便番号が不一致です");
		}
		// --- 氏名の設定（エラーチェックもする）
		if (Students_address.isEmpty()) {
			list.add("本人住所の値が未設定になっています");
		} else {
			System.out.println("bean.getStudents_address  " + bean.getStudents_address());
			System.out.println("Students_address  " + Students_address);
			bean.setStudents_address(Students_address);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Phone_number.isEmpty()) {
			list.add("本人電話番号の値が未設定になっています");
		} else {
			System.out.println("bean.getPhone_number  " + bean.getPhone_number());
			System.out.println("Phone_number  " + Phone_number);
			bean.setPhone_number(Phone_number);
		}
		strPattern = "^[0-9][0-9¥¥-]*$"; /* 正規表現文字列 */
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Phone_number); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("本人電話番号が一致します");
		} else {
			list.add("本人電話番号が一致します");
			System.out.println("本人電話番号が不一致です");
			System.out.println("Phone_number" + Phone_number);
			response.getWriter().println("<p>本人電話番号が不一致です");
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Individuals_mail_address.isEmpty()) {
			list.add("本人メールアドレス（※）の値が未設定になっています");
		} else {
			System.out.println("bean.getIndividuals_mail_address  " + bean.getIndividuals_mail_address());
			System.out.println("Individuals_mail_address  " + Individuals_mail_address);
			bean.setIndividuals_mail_address(Individuals_mail_address);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Guardians_name_in_Kanji.isEmpty()) {
			list.add("保護者氏名（漢字）の値が未設定になっています");
		} else {
			System.out.println("bean.getGuardians_name_in_Kanji  " + bean.getGuardians_name_in_Kanji());
			System.out.println("Guardians_name_in_Kanji  " + Guardians_name_in_Kanji);
			bean.setGuardians_name_in_Kanji(Guardians_name_in_Kanji);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Guardians_Pronunciation.isEmpty()) {
			list.add("保護者ふりがなの値が未設定になっています");
		} else {
			System.out.println("bean.getGuardians_Pronunciation  " + bean.getGuardians_Pronunciation());
			System.out.println("Guardians_Pronunciation  " + Guardians_Pronunciation);
			bean.setGuardians_Pronunciation(Guardians_Pronunciation);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Guardians_postal_code.isEmpty()) {
			list.add("保護者郵便番号の値が未設定になっています");
		} else {
			System.out.println("bean.getGuardians_postal_code  " + bean.getGuardians_postal_code());
			System.out.println("Guardians_postal_code  " + Guardians_postal_code);
			bean.setGuardians_postal_code(Guardians_postal_code);
		}

		strPattern = "^[0-9]{7}$";
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Guardians_postal_code); /* 正 規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("保護者郵便番号が一致します");
		} else {
			list.add("保護者郵便番号が不一致です");
			System.out.println("保護者郵便番号が不一致です");
			System.out.println("Guardians_postal_code" + Guardians_postal_code);
			response.getWriter().println("<p>保護者郵便番号が不一致です");
			errSw = true;
		}
		// --- 氏名の設定（エラーチェックもする）
		if (Guardians_address.isEmpty()) {
			list.add("保護者住所の値が未設定になっています");
		} else {
			System.out.println("bean.getGuardians_address  " + bean.getGuardians_address());
			System.out.println("Guardians_address " + Guardians_address);
			bean.setGuardians_address(Guardians_address);
		}

		// --- 氏名の設定（エラーチェックもする）
		if (Parent_Guardian_Phone_Number.isEmpty()) {
			list.add("保護者電話番号の値が未設定になっています");
		} else {
			System.out.println("bean.getParent_Guardian_Phone_Number  " + bean.getParent_Guardian_Phone_Number());
			System.out.println("Parent_Guardian_Phone_Number  " + Parent_Guardian_Phone_Number);
			bean.setParent_Guardian_Phone_Number(Parent_Guardian_Phone_Number);
		}

		strPattern = "^[0-9][0-9¥¥-]*$"; /* 正規表現文字列 */
		p = Pattern.compile(strPattern); /* 正規表現オブジェクトの準備 */
		m = p.matcher(Parent_Guardian_Phone_Number); /* 正規表現をマッチさせる */
		if (m.find()) { /* find メソッドが true なら一致する */
			System.out.println("保護者電話番号が一致します");
		} else {
			list.add("保護者電話番号が不一致です");
			System.out.println("保護者電話番号が不一致です");
			System.out.println("Parent_Guardian_Phone_Number" + Parent_Guardian_Phone_Number);
			response.getWriter().println("<p>保護者電話番号が不一致です");
		}
		// --- 氏名の設定（エラーチェックもする）
		if (Guardians_email_address.isEmpty()) {
			list.add("保護者メールアドレス（※）の値が未設定になっています");
		} else {
			System.out.println("bean.getGuardians_email_address  " + bean.getGuardians_email_address());
			System.out.println("Guardians_email_address  " + Guardians_email_address);
			bean.setGuardians_email_address(Guardians_email_address);
		}

		// --- DAO の updateata を呼び出す。
		if (list.size() == 0) {
			int result = dao.updateDataAll(bean);
			if (result == 1) {
				list.add("修正完了しました。");
			} else {
				list.add("修正できませんでした。");
			}
		}

		// --- 結果表示のｊjsp へ遷移
		request.setAttribute("message", list);
		request.getRequestDispatcher("sampleUpdateGo.jsp").forward(request, response);
	}
}
