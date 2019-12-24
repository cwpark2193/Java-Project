package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Care_Management_MainVO;
import model.DoctorVO;
import model.ReservationVO;

public class Care_ManagementDAO {
	// 데이터 입력
	// 등록 부분
	public Care_Management_MainVO getCareRegiste(Care_Management_MainVO cmvo) {
		String dml = "insert into caremanageTBL(careNumber,doctorName,doctorPhone,patientName,patientPhone,"
				+ "reservCareDate,reservCaretime,careHistory,Prescription,carePay)values(null,?,?,?,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스와 연결
		try {
			con = DBUtil.getConnection();
			// 보안을 위해 사용
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, cmvo.getDoctorName());
			pstmt.setString(2, cmvo.getDoctorPhone());
			pstmt.setString(3, cmvo.getPatientName());
			pstmt.setString(4, cmvo.getPatientPhone());
			pstmt.setString(5, cmvo.getReservCareDate());
			pstmt.setString(6, cmvo.getReservCaretime());
			pstmt.setString(7, cmvo.getCareHistory());
			pstmt.setString(8, cmvo.getPrescription());
			pstmt.setInt(9, cmvo.getCarePay());

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
		return cmvo;
	}// end of rediste
	// 대상 삭제 기능
	public void getCareDelete(int careNumber) throws Exception {
		// 데이터 처리를 위한 SQL 문
		String dml = "delete from caremanageTBL where careNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 수행후 처리 결과를 가져오기
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, careNumber);

			// 수행후 처리 결과를 가져오기
			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(5, "진료기록 삭제", "진료기록 삭제 완료.", "창을 종료합니다.");
			} else {
				PMClass.alertDisplay(5, "진료기록 삭제", "진료기록 삭제 실패.", "다시 시도해주세요.");
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
	// 업데이트 기능
	public Care_Management_MainVO getCmmUpdate(Care_Management_MainVO cmmVO, int careNumber) throws Exception {
		String dml = "update caremanageTBL set " + 
				" doctorName = ?, doctorPhone = ?, patientName = ?, patientPhone = ?, reservCareDate = ?, "
				+ " reservCaretime = ?,careHistory = ?, Prescription= ?, carePay= ? where careNumber= ? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, cmmVO.getDoctorName());
			pstmt.setString(2, cmmVO.getDoctorPhone());
			pstmt.setString(3, cmmVO.getPatientName());
			pstmt.setString(4, cmmVO.getPatientPhone());
			pstmt.setString(5, cmmVO.getReservCareDate());
			pstmt.setString(6, cmmVO.getReservCaretime());
			pstmt.setString(7, cmmVO.getCareHistory());
			pstmt.setString(8, cmmVO.getPrescription());
			pstmt.setInt(9, cmmVO.getCarePay());
			pstmt.setInt(10, careNumber);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(3, "수정 완료", "자료가 수정되었습니다.", "수정 완료");
			} else {
				PMClass.alertDisplay(1, "수정 실패", "자료 수정에 실패했습니다.", "수정 실패");
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				//
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return cmmVO;

	}// end of update
	// 전체 보여주기
	public ArrayList<Care_Management_MainVO> getCareTotal() {
		ArrayList<Care_Management_MainVO> list = new ArrayList<Care_Management_MainVO>();
		String dml = "select * from caremanageTBL";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체
		ResultSet rs = null;
		Care_Management_MainVO cmvo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cmvo = new Care_Management_MainVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9),rs.getInt(10));
				list.add(cmvo);
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
	// 날짜 체크
	public static ArrayList<Care_Management_MainVO> getCareDateCheck(String s) {
		ArrayList<Care_Management_MainVO> list = new ArrayList<Care_Management_MainVO>();

		String dml = "select * from caremanageTBL WHERE reservCareDate LIKE ?;";
		String ss = "%" + s + "%";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// 데이타베이스 값을 임시로 저장하는 장소 제공하는 객체
		Care_Management_MainVO cmmVO = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, ss);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cmmVO = new Care_Management_MainVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
				list.add(cmmVO);
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
}
