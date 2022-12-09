package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SampleDataBean;
import conn.Conn;

public class SampleDAO extends Conn implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAXROW = 10;
	Connection con;

	public SampleDAO() {
		con = conn(); // --- スーパークラスのデータベース接続部分を呼び出す。conn という変数を利用して参照できる。
	}

	// --- sample テーブルから取り出したデータを ArrayList に格納して返却する
	public List<SampleDataBean> getAllData(int page, String keyword) {
		List<SampleDataBean> data = new ArrayList<SampleDataBean>();
		try {

			String sql = "";
			if (keyword == null || keyword == "") {
				keyword = "";
			}
//			System.out.println(page);
//			System.out.println(keyword);
//			String sql = "select * from gakusei_master;";
//			System.out.println("sql " +  sql);
//			Statement st = con.createStatement();
//			
//			int baseRow = (page - 1) * MAXROW;
//			System.out.println("MAXROW " +  MAXROW);
//			System.out.println("baseRow " +  baseRow);
//			System.out.println("page " +  page);
////			System.out.println("baseRow: " + baseRow);
////			System.out.println("MAXROW: " + MAXROW);	
//			System.out.println("-------------------- ");
//			ResultSet rs = st.executeQuery(sql);
//			System.out.println("-------------------- ");

			sql = "select * from gakusei_master where Student_ID_Number like ? or Student_Name like ? or Student_Pronunciation like ? limit ?, ?;";
			

			System.out.println("sql " + sql);
			PreparedStatement st = con.prepareStatement(sql);

			int baseRow = (page - 1) * MAXROW;
			System.out.println("page " + page);
//			System.out.println("baseRow: " + baseRow);
//			System.out.println("MAXROW: " + MAXROW);
			st.setString(1, "%" + keyword + "%");
			st.setString(2, "%" + keyword + "%");
			st.setString(3, "%" + keyword + "%");
			st.setInt(4, baseRow);
			st.setInt(5, MAXROW);
			System.out.println("-------------------- ");
			ResultSet rs = st.executeQuery();
			System.out.println("-------------------- ");
			while (rs.next()) {
				int Student_ID_Number = rs.getInt("Student_ID_Number");
				System.out.println("Student_ID_Number " + Student_ID_Number);
				String Student_Name = rs.getString("Student_Name");
				System.out.println("Student_Name " + Student_Name);
				String Student_Pronunciation = rs.getString("Student_Pronunciation");
				System.out.println("Stu	dent_Pronunciation " + Student_Pronunciation);
				// ---- ArrayList へデータを追加する
				SampleDataBean b = new SampleDataBean();
				b.setStudent_ID_Number(Student_ID_Number);
				b.setStudent_Name(Student_Name);
				b.setStudent_Pronunciation(Student_Pronunciation);
				data.add(b);
			}
		} catch (Exception e) {
			System.out.println("---------------------------------------- ");
			System.out.println("e " + e);
			System.out.println("--------------------------------------------- ");
			data = null;
		}
		return data;
	}

	// --- 全ページ数を返却する
	public int getMaxPage(String keyword) {
		int allPage = -1;
		try {
			String sql = "select count(*) as cnt from gakusei_master where Student_Name like ?";
			PreparedStatement st = con.prepareStatement(sql);
//			Statement st = con.createStatement();
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			rs.next();
			int records = rs.getInt("cnt");
			allPage = (records - 1) / MAXROW + 1;
		} catch (Exception e) {
			e.printStackTrace();
			allPage = 0;
		}
		System.out.println(allPage);
		return allPage;
	}

	public List<SampleDataBean> getData(String keyword) {
		List<SampleDataBean> data = new ArrayList<SampleDataBean>();
		try {
			String sql = "select * from gakusei_master Student_Name Student_Name like ? or Student_Name like ? or Student_Pronunciation like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + keyword + "%");
			st.setString(2, "%" + keyword + "%");
			st.setString(3, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int Student_ID_Number = rs.getInt("Student_ID_Number");
				String Student_Name = rs.getString("Student_Name");
				String Student_Pronunciation = rs.getString("Student_Pronunciation");

				// ---- ArrayListへデータを追加する
				SampleDataBean b = new SampleDataBean();
				b.setStudent_ID_Number(Student_ID_Number);
				b.setStudent_Name(Student_Name);
				b.setStudent_Pronunciation(Student_Pronunciation);
				data.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}

	public int insertData(SampleDataBean bean) {
//		public int insertData(int Student_ID_Number, int Enrollment_Status, String Enrollment_Status_Date,
//				String Date_of_birth,
//				String Name,String Student_Name,String Student_Pronunciation ,
//				String Students_postal_code,String Students_address,String Phone_number,
//				String Individuals_mail_address,String Guardians_name_in_Kanji,
//				String Guardians_Pronunciation,String Guardians_postal_code,
//				String Guardians_address,String Parent_Guardian_Phone_Number,
//				String Guardians_email_address) {
		int result = -1; // --- ダミー
		try {
			String sql = "INSERT INTO gakusei_master(Student_ID_Number,Enrollment_Status,Enrollment_Status_Date,Student_Name,Student_Pronunciation,Date_of_birth,Students_postal_code,Students_address,Phone_number,Individuals_mail_address,Guardians_name_in_Kanji,Guardians_Pronunciation,Guardians_postal_code,Guardians_address,Parent_Guardian_Phone_Number,Guardians_email_address)"
					+ " VALUES (?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, bean.getStudent_ID_Number()); // 番号（id）のセット
			st.setInt(2, bean.getEnrollment_Status()); // 氏名のセット
			st.setString(3, bean.getEnrollment_Status_Date()); // 氏名のセット
//			st.setDate(3, (Date) bean.getEnrollment_Status_Date()); // 氏名のセット
			st.setString(4, bean.getStudent_Name()); // 氏名のセット
			st.setString(5, bean.getStudent_Pronunciation()); // 氏名のセット
			st.setString(6, bean.getDate_of_birth()); // 氏名のセット
			st.setString(7, bean.getStudents_postal_code()); // 氏名のセット
			st.setString(8, bean.getStudents_address()); // 氏名のセット
			st.setString(9, bean.getPhone_number()); // 氏名のセット
			st.setString(10, bean.getIndividuals_mail_address()); // 氏名のセット
			st.setString(11, bean.getGuardians_name_in_Kanji()); // 氏名のセット
			st.setString(12, bean.getGuardians_Pronunciation()); // 氏名のセット
			st.setString(13, bean.getGuardians_postal_code()); // 氏名のセット
			st.setString(14, bean.getGuardians_address()); // 氏名のセット
			st.setString(15, bean.getParent_Guardian_Phone_Number()); // 氏名のセット
			st.setString(16, bean.getGuardians_email_address()); // 氏名のセット

//			String sql = "insert into sample(Student_ID_Number, Enrollment_Status,Enrollment_Status_DateName,Student_Name,Student_Pronunciation,Date_of_birth,Students_postal_code,Students_address,Phone_number,Individuals_mail_address,Guardians_name_in_Kanji,Guardians_Pronunciation,Guardians_postal_code,Guardians_address,Parent_Guardian_Phone_Number,Guardians_email_address) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			PreparedStatement st = con.prepareStatement(sql);
//			st.setInt(1,Student_ID_Number);
//			st.setInt(2, Enrollment_Status);
//			st.setString(3, Enrollment_Status_Date);
//			st.setString(5, Student_Name);
//			st.setString(6, Student_Pronunciation);
//			st.setString(7, Date_of_birth);
//			st.setString(8, Students_postal_code);
//			st.setString(9, Students_address);
//			st.setString(10, Phone_number);
//			st.setString(11, Individuals_mail_address);
//			st.setString(12, Guardians_name_in_Kanji);
//			st.setString(13, Guardians_Pronunciation);
//			st.setString(14, Guardians_postal_code);
//			st.setString(15, Guardians_address);
//			st.setString(16, Parent_Guardian_Phone_Number);
//			st.setString(17, Guardians_email_address);

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); // --- 実行エラーの場合にトレースを表示する
			result = 0;
		}
		return result;
	}

	// ----- 1 レコードを取得 取得したレコードを返す（失敗はnull）
	public SampleDataBean getOneRec(int Student_ID_Number) {
		SampleDataBean data = new SampleDataBean(); // 返却するデータ
		try {
			String sql = "select * from gakusei_master where Student_ID_Number=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Student_ID_Number);
			ResultSet rs = st.executeQuery();
			rs.next(); // 最初のレコードの取り出し
			data.setStudent_ID_Number(rs.getInt("Student_ID_Number")); // 番号（id）のセット
			data.setEnrollment_Status(rs.getInt("Enrollment_Status")); // 氏名のセット
			data.setEnrollment_Status_Date(rs.getString("Enrollment_Status_Date")); // 氏名のセット
			data.setStudent_Name(rs.getString("Student_Name")); // 氏名のセット
			data.setStudent_Pronunciation(rs.getString("Student_Pronunciation")); // 氏名のセット
			data.setDate_of_birth(rs.getString("Date_of_birth")); // 氏名のセット
			data.setStudents_postal_code(rs.getString("Students_postal_code")); // 氏名のセット
			data.setStudents_address(rs.getString("Students_address")); // 氏名のセット
			data.setPhone_number(rs.getString("Phone_number")); // 氏名のセット
			data.setIndividuals_mail_address(rs.getString("Individuals_mail_address")); // 氏名のセット
			data.setGuardians_name_in_Kanji(rs.getString("Guardians_name_in_Kanji")); // 氏名のセット
			data.setGuardians_Pronunciation(rs.getString("Guardians_Pronunciation")); // 氏名のセット
			data.setGuardians_postal_code(rs.getString("Guardians_postal_code")); // 氏名のセット
			data.setGuardians_address(rs.getString("Guardians_address")); // 氏名のセット
			data.setParent_Guardian_Phone_Number(rs.getString("Parent_Guardian_Phone_Number")); // 氏名のセット
			data.setGuardians_email_address(rs.getString("Guardians_email_address")); // 氏名のセット
		} catch (Exception e) {
			e.printStackTrace(); // しくじった時は念のためトレース表示
			data = null;
		}
		return data;
	}

	// ----- 削除する処理 処理したレコード数を返す（失敗＝0）
	public int deleteData(int Student_ID_Number) {
		int result = -1; // 返却値の宣言、ダミーの値を設定している
		try {
			String sql = "delete from gakusei_master where Student_ID_Number=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Student_ID_Number);
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); // しくじった時は念のためトレース表示
			result = 0;
		}
		return result;
	}

	public boolean isExists(int Student_ID_Number) {
		boolean result = false; // 結果を返却する変数(存在しない)
		try {
			String sql = "select count(*) from gakusei_master where Student_ID_Number=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Student_ID_Number);
			ResultSet rs = st.executeQuery();
			rs.next(); // 最初のレコードの位置へ移動
			// --- 結果を取り出す
			if (rs.getInt(1) == 1) {
				result = true; // データが存在するのでtrueを返却
			}
		} catch (Exception e) {
			e.printStackTrace(); // しくじった時は念のためトレース表示
			result = true; // 何かのエラーがあったので登録できないようにtrue返す
		}
		return result;
	}

	public int updateDataAll(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Enrollment_Status=?,Enrollment_Status_Date=?,"
					+ "Student_Name=?,Student_Pronunciation=?,Date_of_birth=?,Students_postal_code=?,Students_address=?,"
					+ "Phone_number=?,Individuals_mail_address=?,Guardians_name_in_Kanji=?,Guardians_Pronunciation=?,"
					+ "Guardians_postal_code=?,Guardians_address=?,Parent_Guardian_Phone_Number=?,Guardians_email_address=?"
					+ " where Student_ID_Number=?";
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudent_Name()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録

			st.setInt(1, bean.getEnrollment_Status()); // 番号（id）のセット
			st.setString(2, bean.getEnrollment_Status_Date()); // 氏名のセット
			st.setString(3, bean.getStudent_Name()); // 氏名のセット
//			st.setDate(3, (Date) bean.getEnrollment_Status_Date()); // 氏名のセット
			st.setString(4, bean.getStudent_Pronunciation()); // 氏名のセット
			st.setString(5, bean.getDate_of_birth()); // 氏名のセット
			st.setString(6, bean.getStudents_postal_code()); // 氏名のセット
			st.setString(7, bean.getStudents_address()); // 氏名のセット
			st.setString(8, bean.getPhone_number()); // 氏名のセット
			st.setString(9, bean.getIndividuals_mail_address()); // 氏名のセット
			st.setString(10, bean.getGuardians_name_in_Kanji()); // 氏名のセット
			st.setString(11, bean.getGuardians_Pronunciation()); // 氏名のセット
			st.setString(12, bean.getGuardians_postal_code()); // 氏名のセット
			st.setString(13, bean.getGuardians_address()); // 氏名のセット
			st.setString(14, bean.getParent_Guardian_Phone_Number()); // 氏名のセット
			st.setString(15, bean.getGuardians_email_address()); // 氏名のセット
			st.setInt(16, bean.getStudent_ID_Number()); // ID の登録

			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataName(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Student_Name=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudent_Name()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataEnrollment_Status(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Enrollment_Status=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setInt(1, bean.getEnrollment_Status()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataStudent_Pronunciation(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Student_Pronunciation=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudent_Pronunciation()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataStudents_postal_code(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Students_postal_code=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudents_postal_code()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataStudents_address(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Students_address=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudents_address()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataPhone_number(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Phone_number=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getPhone_number()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataIndividuals_mail_address(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Individuals_mail_address=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getIndividuals_mail_address()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataGuardians_name_in_Kanji(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Guardians_name_in_Kanji=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getGuardians_name_in_Kanji()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataGuardians_Pronunciation(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Guardians_Pronunciation=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getGuardians_Pronunciation()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataGuardians_postal_code(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Guardians_postal_code=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getGuardians_postal_code()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataStudent_Name(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Student_Name=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getStudent_Name()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataGuardians_address(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Guardians_address=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getGuardians_address()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataParent_Guardian_Phone_Number(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Parent_Guardian_Phone_Number=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getParent_Guardian_Phone_Number()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

	public int updateDataGuardians_email_address(SampleDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set Guardians_email_address=? where Student_ID_Number=?"; // SQL 文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getGuardians_email_address()); // 氏名の登録
			st.setInt(2, bean.getStudent_ID_Number()); // ID の登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

}
