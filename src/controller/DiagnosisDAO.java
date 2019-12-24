package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DiagnosisVO;
import model.PatientVO;

public class DiagnosisDAO {
	// 데이터 입력
		// 등록 부분
		public DiagnosisVO getDiagnosisRegiste(DiagnosisVO dgvo) {
			String dml = "insert into diagnosisTBL(diagNumber,patientName,patientGender,patientAge,patientSSN,patientPhone,"
					+ "patientAddress,reservCareDate,reservCaretime,careHistory,prescription,remarks,purpose,"
					+ "publishDate,hospitalName,hospitalAdd,doctorPhone,doctorLicense,doctorName)"
					+"values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Connection con = null;
			PreparedStatement pstmt = null;
			// 데이터베이스와 연결
			try {
				con = DBUtil.getConnection();
				// 보안을 위해 사용
				pstmt = con.prepareStatement(dml);
				pstmt.setString(1, dgvo.getPatientName());
				pstmt.setString(2, dgvo.getPatientGender());
				pstmt.setInt(3, dgvo.getPatientAge());
				pstmt.setString(4, dgvo.getPatientSSN());
				pstmt.setString(5, dgvo.getPatientPhone());
				pstmt.setString(6, dgvo.getPatientAddress());
				pstmt.setString(7, dgvo.getReservCareDate());
				pstmt.setString(8, dgvo.getReservCaretime());
				pstmt.setString(9, dgvo.getCareHistory());
				pstmt.setString(10, dgvo.getPrescription());
				pstmt.setString(11, dgvo.getRemarks());
				pstmt.setString(12, dgvo.getPurpose());
				pstmt.setString(13, dgvo.getPublishDate());
				pstmt.setString(14, dgvo.getHospitalName());
				pstmt.setString(15, dgvo.getHospitalAdd());
				pstmt.setString(16, dgvo.getDoctorPhone());
				pstmt.setString(17, dgvo.getDoctorLicense());
				pstmt.setString(18, dgvo.getDoctorName());

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
			return dgvo;
			
		}// end of rediste
			// 대상 삭제 기능

		public void getDigDelete(int diagNumber) throws Exception {
			// 데이터 처리를 위한 SQL 문
			String dml = "delete from diagnosisTBL where diagNumber = ?";
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				// 데이터베이스와 연결
				con = DBUtil.getConnection();

				// 수행후 처리 결과를 가져오기
				pstmt = con.prepareStatement(dml);
				pstmt.setInt(1, diagNumber);

				// 수행후 처리 결과를 가져오기
				int i = pstmt.executeUpdate();

				if (i == 1) {
					PMClass.alertDisplay(5, "진단서 삭제", "진단서 삭제 완료.", "창을 종료합니다.");
				} else {
					PMClass.alertDisplay(5, "진단서 삭제", "진단서 삭제 실패.", "다시 시도해주세요.");
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
		public DiagnosisVO getDoctorUpdate(DiagnosisVO digvo, int diagNumber) throws Exception {
			String dml = "update diagnosisTBL set "
					+ " patientName =?, patientGender =?, patientAge =?, patientSSN =?, patientPhone =?, patientAddress =?, "
					+ " reservCareDate = ?, reservCaretime = ?, careHistory = ?, prescription = ?, remarks = ?, purpose = ?, "
					+ " publishDate = ?, hospitalName = ?, hospitalAdd = ?, doctorPhone = ?, doctorLicense = ?, doctorName = ? "
					+ " where patientNumber=?";

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = DBUtil.getConnection();

				pstmt = con.prepareStatement(dml);

				pstmt.setString(1, digvo.getPatientName());
				pstmt.setString(2, digvo.getPatientGender());
				pstmt.setInt(3, digvo.getPatientAge());
				pstmt.setString(4, digvo.getPatientSSN());
				pstmt.setString(5, digvo.getPatientPhone());
				pstmt.setString(6, digvo.getPatientAddress());
				pstmt.setString(7, digvo.getReservCareDate());
				pstmt.setString(8, digvo.getReservCaretime());
				pstmt.setString(9, digvo.getCareHistory());
				pstmt.setString(10, digvo.getPrescription());
				pstmt.setString(11, digvo.getRemarks());
				pstmt.setString(12, digvo.getPurpose());
				pstmt.setString(13, digvo.getPublishDate());
				pstmt.setString(14, digvo.getHospitalName());
				pstmt.setString(15, digvo.getHospitalAdd());
				pstmt.setString(16, digvo.getDoctorPhone());
				pstmt.setString(17, digvo.getDoctorLicense());
				pstmt.setString(18, digvo.getDoctorName());

				int i = pstmt.executeUpdate();

				if (i == 1) {
					PMClass.alertDisplay(3, "수정 완료", digvo.getPatientName() + "의 자료가 수정되었습니다.", "수정 완료");
				} else {
					PMClass.alertDisplay(1, "수정 실패", digvo.getPatientName() + "의 자료 수정에 실패했습니다.", "수정 실패");
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
			return digvo;

		}// end of update

		public ArrayList<DiagnosisVO> getDIgTotal() {
			ArrayList<DiagnosisVO> digList = new ArrayList<DiagnosisVO>();
			String dml = "select * from patientTBL";

			Connection con = null;
			PreparedStatement pstmt = null;
			// 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체
			ResultSet rs = null;
			DiagnosisVO digvo = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(dml);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					digvo = new DiagnosisVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
							rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17),
							rs.getString(18), rs.getString(19));
					digList.add(digvo);
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
			return digList;
		}// end of total
}
