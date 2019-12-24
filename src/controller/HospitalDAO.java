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
//	//1. 데이터를 입력하기 위함 ( insert )
//	public String getHospital(HospitalNo1 hno) throws Exception{
//		1. 다시 선택 활성화.
//		2. 예약하기 버튼 활성화.
//		3. 남은 2개 VIew 만들기.
	
//		// 2. 데이터 처리를 위한 SQL문
//		String dbTest = "insert into shopdatabase"
//				+"(No1,No2,No3)"+"values"+" (null,?,?,now(),?)" ;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		int count="";
//		try {
//			//3. DBUtil 클래스의 getConnection() 메소드로 데이터베이스를 연결한다.
//			con=DBUtil.getConnection();
//			//4. 입력받은 학생 정보를 처리하기 위한 SQL문장을 생성한다.
//			pstmt = con.prepareStatement(dbTest);
//			pstmt.setString(1,hno.getNo1());
//			pstmt.setString(2,hno.getNo2());
//			pstmt.setString(3,hno.getNo3());
//			
//			//SQL 문을 수행후 처리 결과를 얻어온다.
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
