//package controller;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import model.HospitalNo1;
//
//public class HospitalDAO {
//	
//	//1. �����͸� �Է��ϱ� ���� ( insert )
//	public String getHospital(HospitalNo1 hno) throws Exception{
//		1. �ٽ� ���� Ȱ��ȭ.
//		2. �����ϱ� ��ư Ȱ��ȭ.
//		3. ���� 2�� VIew �����.
	
//		// 2. ������ ó���� ���� SQL��
//		String dbTest = "insert into shopdatabase"
//				+"(No1,No2,No3)"+"values"+" (null,?,?,now(),?)" ;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		int count="";
//		try {
//			//3. DBUtil Ŭ������ getConnection() �޼ҵ�� �����ͺ��̽��� �����Ѵ�.
//			con=DBUtil.getConnection();
//			//4. �Է¹��� �л� ������ ó���ϱ� ���� SQL������ �����Ѵ�.
//			pstmt = con.prepareStatement(dbTest);
//			pstmt.setString(1,hno.getNo1());
//			pstmt.setString(2,hno.getNo2());
//			pstmt.setString(3,hno.getNo3());
//			
//			//SQL ���� ������ ó�� ����� ���´�.
//			count = pstmt.executeUpdate();
//				
//		}catch(SQLException e) {
//			System.out.println("e= "+e+"]");
//		}catch(Exception e) {
//			System.out.println("e= "+e+"]");
//		}finally {
//			try {
//				
//			}catch (Exception e) {
//				if(pstmt != null) {
//					pstmt.close();
//				}if(con != null) {
//					con.close();
//				}catch (SQLException e) {
//				}
//			}
//			return count;
//		}
//		return null;
//		
//	}
//
//}
