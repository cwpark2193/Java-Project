package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.ReservationVO;

public class ReservationDAO {
	// 데이터 입력
	// 등록 부분
	public ReservationVO getReservRegiste(ReservationVO rvo) {
		String dml = "insert into reservationTBL(reservationNumber,doctorNumber,patientNumber,reservCareDate,reservCaretime,reservReaseon)"
				+"values(null,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스와 연결
		try {
			con = DBUtil.getConnection();
			// 보안을 위해 사용
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, rvo.getDoctorNumber());
			pstmt.setInt(2, rvo.getPatientNumber());
//			pstmt.setString(3, rvo.getDoctorName());
//			pstmt.setString(4, rvo.getDoctorPhone());
//			pstmt.setString(5, rvo.getPatientName());
//			pstmt.setString(6, rvo.getPatientPhone());
			pstmt.setString(7, rvo.getReservCareDate());
			pstmt.setString(8, rvo.getReservCaretime());
			pstmt.setString(9, rvo.getReservReaseon());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("e=[" + e + "]");
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// DB 연결 오브젝트 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return rvo;
	}// end of rediste
	
	// 대상 삭제 기능
	public void getResDelete(int reservationNumber) throws Exception {
		// 데이터 처리를 위한 SQL 문
		String dml = "delete from reservationTBL where reservationNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 수행후 처리 결과를 가져오기
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, reservationNumber);

			// 수행후 처리 결과를 가져오기
			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(5, "예약자료 삭제", "예약자료 삭제 완료.", "창을 종료합니다.");
			} else {
				PMClass.alertDisplay(5, "예약자료 삭제", "예약자료 삭제 실패.", "다시 시도해주세요.");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 연결 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

	}// end of delete

	// 전체 보여주기
	public ArrayList<ReservationVO> getResTotal() {
		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();
		String dml = "select r.reservationNumber, d.doctorName, d.doctorPhone, \r\n" + 
				"p.patientName,p.patientPhone,	r.reservCareDate, r.reservCaretime,\r\n" + 
				"r.reservReaseon from reservationTBL r, doctorTBL d, patienttbl p\r\n" + 
				"where r.doctorNumber = d.doctorNumber and r.patientNumber = p.patientNumber";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체
		ResultSet rs = null;
		ReservationVO rvo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rvo = new ReservationVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(rvo);
			}
		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;
	}// end of total
	// 번호 체크?(수정 필요)
	public ReservationVO getNumCheck(int reservationNumber) throws Exception {
		String dml=" select r.reservationNumber, r.doctorNumber, r.reservCareDate,r.reservCaretime,r.reservReaseon, "
				+ "d.doctorName, d.doctorPhone,r.patientNumber , p.patientName, p.patientPhone "
				+ "from reservationTBL r ,doctorTBL d , patientTBL p where r.doctorNumber = d.doctorNumber and r.patientNumber = p.patientNumber "
				+ "and r.patientNumber like ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO rvo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml.toString());
			pstmt.setInt(1, reservationNumber);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rvo = new ReservationVO();
				rvo.setReservationNumber(rs.getInt(1));
				rvo.setDoctorNumber(rs.getInt(2));
				rvo.setReservCareDate(rs.getString(3).substring(0,10));
				rvo.setReservCaretime(rs.getString(4));
				rvo.setReservReaseon(rs.getString(5));
				rvo.setDoctorName(rs.getString(6));
				rvo.setDoctorPhone(rs.getString(7));
				rvo.setPatientNumber(rs.getInt(8));
				rvo.setPatientName(rs.getString(9));
				rvo.setPatientPhone(rs.getString(10));		
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {

			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return rvo;
	}
	// 날짜 체크
	public static ArrayList<ReservationVO> getResDateCheck(String s) {
		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();

		String dml = "select r.reservationNumber, d.doctorName, d.doctorPhone, \r\n" + 
				"p.patientName,p.patientPhone,	r.reservCareDate, r.reservCaretime,\r\n" + 
				"r.reservReaseon from reservationTBL r, doctorTBL d, patienttbl p\r\n" + 
				"where r.doctorNumber = d.doctorNumber and r.patientNumber = p.patientNumber and r.reservCareDate LIKE ?;";
		String ss = "%" + s + "%";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// 데이타베이스 값을 임시로 저장하는 장소 제공하는 객체
		ReservationVO rvo = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, ss);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rvo = new ReservationVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8));
				list.add(rvo);
			}
	      } catch (SQLException se) {
	         System.out.println(se);
	      } catch (Exception e) {
	         System.out.println(e);
	      } finally {
	         try {
	            if (rs != null)
	               rs.close();
	            if (pstmt != null)
	               pstmt.close();
	            if (con != null)
	               con.close();
	         } catch (SQLException se) {
	         }
	      }
	      return list;
	   }
	// 파이 차트 부르기
	public int getPieChart(String reservCaretime) throws Exception {
       String dml = "select count(*) as reservCaretime from reservationTBL where reservCaretime = ?";
       
       Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
           con = DBUtil.getConnection();
           pstmt = con.prepareStatement(dml);
           pstmt.setString(1, reservCaretime);
           rs = pstmt.executeQuery();
          // result = rs.getInt(1);
           System.out.println(result);
           while (rs.next()) {
                result = rs.getInt(1);
            }
         } catch (SQLException se) {
            System.out.println(se);
         } catch (Exception e) {
            System.out.println(e);
         } finally {
            try {
               if (rs != null)
                  rs.close();
               if (pstmt != null)
                  pstmt.close();
               if (con != null)
                  con.close();
            } catch (SQLException se) {
            }
         }
         return result;   
   }
	// 바 차트
//	public int getBarChart(String city, String payMethod) throws Exception {
//      String dml = "select count(*) as cityCount from outboundTBL where receiverAddress like ?"+"and payMethod=?";
//       
//       Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        int result=0;
//        try {
//           con = DBUtil.getConnection();
//           pstmt = con.prepareStatement(dml);
//           pstmt.setString(1, "%"+city+"%");
//           pstmt.setString(2, "payMethod");
//           rs = pstmt.executeQuery();
//           while (rs.next()) {
//                result = rs.getInt(1);
//            }
//        } catch (SQLException se) {
//            System.out.println(se);
//         } catch (Exception e) {
//            System.out.println(e);
//         } finally {
//            try {
//               if (rs != null)
//                  rs.close();
//               if (pstmt != null)
//                  pstmt.close();
//               if (con != null)
//                  con.close();
//            } catch (SQLException se) {
//            }
//         }
//      return result;
//   }

}
