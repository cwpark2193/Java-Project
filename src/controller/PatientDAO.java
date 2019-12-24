package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DoctorVO;
import model.PatientVO;

public class PatientDAO {
	// 데이터 입력
	// 등록 부분
	public PatientVO getPatientInfoRegiste(PatientVO pvo) {
		String dml = "insert into patientTBL(patientNumber,patientID,patientPassword,patientName,patientGender,patientAge,"
				+ "patientSSN,patientPhone,patientAddress,patientImage)"+"values(null,?,?,?,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스와 연결
		try {
			con = DBUtil.getConnection();
			// 보안을 위해 사용
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, pvo.getPatientID());
			pstmt.setString(2, pvo.getPatientPassword());
			pstmt.setString(3, pvo.getPatientName());
			pstmt.setString(4, pvo.getPatientGender());
			pstmt.setInt(5, pvo.getPatientAge());
			pstmt.setString(6, pvo.getPatientSSN());
			pstmt.setString(7, pvo.getPatientPhone());
			pstmt.setString(8, pvo.getPatientAddress());
			pstmt.setString(9, pvo.getPatientImage());

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
		return pvo;
	}// end of rediste
		// 대상 삭제 기능

	public void getPIDelete(int patientNumber) throws Exception {
		// 데이터 처리를 위한 SQL 문
		String dml = "delete from patientTBL where patientNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 수행후 처리 결과를 가져오기
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, patientNumber);

			// 수행후 처리 결과를 가져오기
			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(5, "환자자료 삭제", "환자자료 삭제 완료.", "창을 종료합니다.");
			} else {
				PMClass.alertDisplay(5, "환자자료 삭제", "환자자료 삭제 실패.", "다시 시도해주세요.");
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

	public PatientVO getPatUpdate(PatientVO pvo, int patientNumber) throws Exception {
		String dml = "update patientTBL set "
				+ " patientName =?, patientGender =?, patientAge =?, patientSSN =?, patientPhone =?, patientAddress =?, "
				+ "patientImage = ? where patientNumber=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, pvo.getPatientName());
			pstmt.setString(2, pvo.getPatientGender());
			pstmt.setInt(3, pvo.getPatientAge());
			pstmt.setString(4, pvo.getPatientSSN());
			pstmt.setString(5, pvo.getPatientPhone());
			pstmt.setString(6, pvo.getPatientAddress());
			pstmt.setString(7, pvo.getPatientImage());
			pstmt.setInt(8, patientNumber);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(3, "수정 완료", pvo.getPatientName() + "의 자료가 수정되었습니다.", "수정 완료");
			} else {
				PMClass.alertDisplay(1, "수정 실패", pvo.getPatientName() + "의 자료 수정에 실패했습니다.", "수정 실패");
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
		return pvo;

	}// end of update

	public ArrayList<PatientVO> getPITotal() {
		ArrayList<PatientVO> pvlist = new ArrayList<PatientVO>();
		String dml = "select * from patientTBL";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체
		ResultSet rs = null;
		PatientVO pvo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pvo = new PatientVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				pvlist.add(pvo);
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
		return pvlist;
	}// end of total
}
