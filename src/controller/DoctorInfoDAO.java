package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DoctorVO;

public class DoctorInfoDAO {
	// 데이터 입력
	// 등록 부분
	public DoctorVO getDoctorInfoRegiste(DoctorVO dvo) {
		String dml = "insert into doctorTBL(doctorNumber,doctorID,doctorPassword,doctorName,doctorGender,doctorAge,"
				+ "doctorSSN,doctorPhone,doctorAddress,doctorCareTime,doctorLicense,doctorImage)"+"values"
						+ "(null,?,?,?,?,?,?,?,?,now(),?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스와 연결
		try {
			con = DBUtil.getConnection();
			// 보안을 위해 사용
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, dvo.getDoctorID());
			pstmt.setString(2, dvo.getDoctorPassword());
			pstmt.setString(3, dvo.getDoctorName());
			pstmt.setString(4, dvo.getDoctorGender());
			pstmt.setInt(5, dvo.getDoctorAge());
			pstmt.setString(6, dvo.getDoctorSSN());
			pstmt.setString(7, dvo.getDoctorPhone());
			pstmt.setString(8, dvo.getDoctorAddress());
			pstmt.setString(9, dvo.getDoctorLicense());
			pstmt.setString(10, dvo.getDoctorImage());

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
		return dvo;
	}// end of rediste
	
	// 대상 삭제 기능
	public void getDIDelete(int doctorNumber) throws Exception {
		// 데이터 처리를 위한 SQL 문
		String dml = "delete from doctorTBL where doctorNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 수행후 처리 결과를 가져오기
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, doctorNumber);

			// 수행후 처리 결과를 가져오기
			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(5, "의사자료 삭제", "의사자료 삭제 완료.", "창을 종료합니다.");
			} else {
				PMClass.alertDisplay(5, "의사자료 삭제", "의사자료 삭제 실패.", "다시 시도해주세요.");
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

	public DoctorVO getDoctorUpdate(DoctorVO dvo, int doctorNumber) throws Exception {
		String dml = "update doctorTBL set "
				+ " doctorName =?, doctorGender =?, doctorAge =?, doctorSSN =?, doctorPhone =?, doctorAddress =?, "
				+ "doctorCareTime =? ,doctorLicense = ?,doctorImage = ? where doctorNumber=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, dvo.getDoctorName());
			pstmt.setString(2, dvo.getDoctorGender());
			pstmt.setInt(3, dvo.getDoctorAge());
			pstmt.setString(4, dvo.getDoctorSSN());
			pstmt.setString(5, dvo.getDoctorPhone());
			pstmt.setString(6, dvo.getDoctorAddress());
			pstmt.setString(7, dvo.getDoctorCareTime());
			pstmt.setString(8, dvo.getDoctorLicense());
			pstmt.setString(9, dvo.getDoctorImage());
			pstmt.setInt(10, doctorNumber);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(3, "수정 완료", dvo.getDoctorName() + "의 자료가 수정되었습니다.", "수정 완료");
			} else {
				PMClass.alertDisplay(1, "수정 실패", dvo.getDoctorName() + "의 자료 수정에 실패했습니다.", "수정 실패");
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
		return dvo;

	}// end of update

	public ArrayList<DoctorVO> getDITotal() {
		ArrayList<DoctorVO> list = new ArrayList<DoctorVO>();
		String dml = "select * from doctorTBL";

		Connection con = null;
		PreparedStatement pstmt = null;
		// 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체
		ResultSet rs = null;
		DoctorVO DVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DVO = new DoctorVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
				list.add(DVO);
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

	//
	public ArrayList<String> getCollumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select* from doctorTBl");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i < cols; i++) {
				columnName.add(rsmd.getColumnName(i));

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					;
				rs.close();
				if (pstmt != null)
					;
				pstmt.close();
				if (con != null)
					;
				con.close();
			} catch (SQLException se) {

			}
		}
		return columnName;
	}// end of col

}
