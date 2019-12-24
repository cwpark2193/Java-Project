package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DoctorVO;
import model.PatientVO;

public class PatientDAO {
	// ������ �Է�
	// ��� �κ�
	public PatientVO getPatientInfoRegiste(PatientVO pvo) {
		String dml = "insert into patientTBL(patientNumber,patientID,patientPassword,patientName,patientGender,patientAge,"
				+ "patientSSN,patientPhone,patientAddress,patientImage)"+"values(null,?,?,?,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		// �����ͺ��̽��� ����
		try {
			con = DBUtil.getConnection();
			// ������ ���� ���
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
				// DB ���� ������Ʈ ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return pvo;
	}// end of rediste
		// ��� ���� ���

	public void getPIDelete(int patientNumber) throws Exception {
		// ������ ó���� ���� SQL ��
		String dml = "delete from patientTBL where patientNumber = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// ������ ó�� ����� ��������
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, patientNumber);

			// ������ ó�� ����� ��������
			int i = pstmt.executeUpdate();

			if (i == 1) {
				PMClass.alertDisplay(5, "ȯ���ڷ� ����", "ȯ���ڷ� ���� �Ϸ�.", "â�� �����մϴ�.");
			} else {
				PMClass.alertDisplay(5, "ȯ���ڷ� ����", "ȯ���ڷ� ���� ����.", "�ٽ� �õ����ּ���.");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ���� ������Ʈ�� ����
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
				PMClass.alertDisplay(3, "���� �Ϸ�", pvo.getPatientName() + "�� �ڷᰡ �����Ǿ����ϴ�.", "���� �Ϸ�");
			} else {
				PMClass.alertDisplay(1, "���� ����", pvo.getPatientName() + "�� �ڷ� ������ �����߽��ϴ�.", "���� ����");
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
		// �����ͺ��̽� ���� �ӽ÷� �����ϴ� ��� �����ϴ� ��ü
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
