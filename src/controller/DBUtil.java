package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//1. ����̹����� ����
		private static String driver="com.mysql.jdbc.Driver";
		//2. ������ ���̽� url ����
		private static String url="jdbc:mysql://localhost/hospitaldb";
		
		//2. ����̹��� �����ϰ�, �����ͺ��̽��� �����ϴ� �Լ�
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			//1. ����̹� ����
			Class.forName(driver);
			//2. �����ͺ��̽� ����
			Connection con=DriverManager.getConnection(url, "root", "123456");
			return con;
		}
}