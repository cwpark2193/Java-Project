package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//1. 드라이버명을 적재
		private static String driver="com.mysql.jdbc.Driver";
		//2. 데이터 베이스 url 저장
		private static String url="jdbc:mysql://localhost/hospitaldb";
		
		//2. 드라이버를 적재하고, 데이터베이스를 연결하는 함수
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			//1. 드라이버 적재
			Class.forName(driver);
			//2. 데이터베이스 연결
			Connection con=DriverManager.getConnection(url, "root", "123456");
			return con;
		}
}
