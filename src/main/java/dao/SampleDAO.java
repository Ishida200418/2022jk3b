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

	public List<SampleDataBean> getAllData(int page, String keyword, ArrayList<Integer> hoge) {
		List<SampleDataBean> data = new ArrayList<SampleDataBean>();
		try {

			String sql = "";
			if (keyword == null || keyword == "") {
				keyword = "";
			}

			sql = "select * from gakusei_master where (Enrollment_Status not like ? and Enrollment_Status not like ? and Enrollment_Status not like ? and Enrollment_Status not like ?) and (Student_ID_Number like ? or Student_Name like ? or Student_Pronunciation like ?) limit ?, ?;";




			System.out.println("sql " + sql);
			PreparedStatement st = con.prepareStatement(sql);

			int baseRow = (page - 1) * MAXROW;
			System.out.println("page " + page);

			System.out.println("hoge.size() " + hoge.size());
			for (int i = 0; i < hoge.size(); i++) {
				System.out.println("hoge.get(i) " + hoge.get(i));
				st.setInt(i + 1, hoge.get(i));
			}

			st.setString(5, "%" + keyword + "%");
			st.setString(6, "%" + keyword + "%");
			st.setString(7, "%" + keyword + "%");
			st.setInt(8, baseRow);
			st.setInt(9, MAXROW);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int Student_ID_Number = rs.getInt("Student_ID_Number");
				String Student_Name = rs.getString("Student_Name");
				String Student_Pronunciation = rs.getString("Student_Pronunciation");
				
				System.out.println("Student_ID_Number " + Student_ID_Number);
				System.out.println("Student_Name " + Student_Name);
				System.out.println("Stu	dent_Pronunciation " + Student_Pronunciation);
				
				// ---- ArrayList へデータを追加する
				SampleDataBean b = new SampleDataBean();
				b.setStudent_ID_Number(Student_ID_Number);
				b.setStudent_Name(Student_Name);
				b.setStudent_Pronunciation(Student_Pronunciation);
				data.add(b);
			}
		} catch (

		Exception e) {
			System.out.println("---------------------------------------- ");
			System.out.println("e " + e);
			data = null;
		}
		return data;
	}

//	---全ページ数を返却する

	public int getMaxPage(String keyword) {
		System.out.println("getMaxPage  ");
		int allPage = -1;
		try {
			String sql = "select count(*) as cnt from gakusei_master where Student_Name like ?";
			PreparedStatement st = con.prepareStatement(sql);
//			Statement st = con.createStatement();
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			rs.next();
			int records = rs.getInt("cnt");
			
			System.out.println("records  " + records);
			
			allPage = (records - 1) / MAXROW + 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
			allPage = 0;
		}
		System.out.println("allPage" + allPage);
		return allPage;
	}
	


	// --- 全ページ数を返却する
	public int getMaxPage2(String keyword, ArrayList<Integer> hoge) {
		System.out.println("getMaxPage2  ");
		int allPage = -1;
		try {
			String sql = "";

			sql = "select count(*) as cnt from gakusei_master where (Enrollment_Status not like ? and Enrollment_Status not like ? and Enrollment_Status not like ? and Enrollment_Status not like ?) and (Student_ID_Number like ? or Student_Name like ? or Student_Pronunciation like ?);";

			PreparedStatement st = con.prepareStatement(sql);
			System.out.println("hoge.size() " + hoge.size());
			for (int i = 0; i < hoge.size(); i++) {
				
				System.out.println("hoge.get(i) " + hoge.get(i));
				st.setInt(i + 1, hoge.get(i));
			}

			st.setString(5, "%" + keyword + "%");
			st.setString(6, "%" + keyword + "%");
			st.setString(7, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			rs.next();
			int records = rs.getInt("cnt");
			
			System.out.println("records: " + records);
			allPage = (records - 1) / MAXROW + 1;
		} catch (Exception e) {

			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
			e.printStackTrace();
			allPage = 0;
		}
		System.out.println("allPage" + allPage);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
			e.printStackTrace();
			data = null;
		}
		return data;
	}

	public int insertData(SampleDataBean bean) {
		int result = -1; // --- ダミー
		try {
			String sql = "INSERT INTO gakusei_master(Student_ID_Number,Enrollment_Status,Enrollment_Status_Date,Student_Name,Student_Pronunciation,Date_of_birth,Students_postal_code,Students_address,Phone_number,Individuals_mail_address,Guardians_name_in_Kanji,Guardians_Pronunciation,Guardians_postal_code,Guardians_address,Parent_Guardian_Phone_Number,Guardians_email_address)"
					+ " VALUES (?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, bean.getStudent_ID_Number()); // 番号（id）のセット
			st.setInt(2, bean.getEnrollment_Status()); // 氏名のセット
			st.setString(3, bean.getEnrollment_Status_Date()); // 氏名のセット
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
			data.setStudent_ID_Number(rs.getInt("Student_ID_Number")); // 
			data.setEnrollment_Status(rs.getInt("Enrollment_Status")); // 
			data.setEnrollment_Status_Date(rs.getString("Enrollment_Status_Date")); // 
			data.setStudent_Name(rs.getString("Student_Name")); // 
			data.setStudent_Pronunciation(rs.getString("Student_Pronunciation")); // 
			data.setDate_of_birth(rs.getString("Date_of_birth")); // 
			data.setStudents_postal_code(rs.getString("Students_postal_code")); // 
			data.setStudents_address(rs.getString("Students_address")); // 
			data.setPhone_number(rs.getString("Phone_number")); // 
			data.setIndividuals_mail_address(rs.getString("Individuals_mail_address")); // 
			data.setGuardians_name_in_Kanji(rs.getString("Guardians_name_in_Kanji")); // 
			data.setGuardians_Pronunciation(rs.getString("Guardians_Pronunciation")); // 
			data.setGuardians_postal_code(rs.getString("Guardians_postal_code")); // 
			data.setGuardians_address(rs.getString("Guardians_address")); // 
			data.setParent_Guardian_Phone_Number(rs.getString("Parent_Guardian_Phone_Number")); // 
			data.setGuardians_email_address(rs.getString("Guardians_email_address")); // 
		} catch (Exception e) {
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			st.setString(1, bean.getStudent_Name()); //
			st.setInt(2, bean.getStudent_ID_Number()); // 

			st.setInt(1, bean.getEnrollment_Status()); //
			st.setString(2, bean.getEnrollment_Status_Date()); //
			st.setString(3, bean.getStudent_Name()); 
			st.setString(4, bean.getStudent_Pronunciation()); // 
			st.setString(5, bean.getDate_of_birth()); //
			st.setString(6, bean.getStudents_postal_code()); //
			st.setString(7, bean.getStudents_address()); //
			st.setString(8, bean.getPhone_number()); //
			st.setString(9, bean.getIndividuals_mail_address()); // 
			st.setString(10, bean.getGuardians_name_in_Kanji());
			st.setString(11, bean.getGuardians_Pronunciation());
			st.setString(12, bean.getGuardians_postal_code()); 
			st.setString(13, bean.getGuardians_address());
			st.setString(14, bean.getParent_Guardian_Phone_Number()); 
			st.setString(15, bean.getGuardians_email_address()); 
			st.setInt(16, bean.getStudent_ID_Number()); 

			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
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
			System.out.println("---------------------------------------- ");
			System.out.println("e  " + e);
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}

}
