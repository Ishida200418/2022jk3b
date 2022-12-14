package conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	final String url = "jdbc:mysql://192.168.54.225:3306/team_d_db";
	final String user = "userd"; // ユーザ名
	final String pass = "passwordd"; // パスワード

	public Connection conn() {
		Connection con;
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
			con = null;
		}
		return con;	
	}

}





















