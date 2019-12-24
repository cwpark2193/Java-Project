package model;

public class ReservationVO {
	private int reservationNumber;
	private int doctorNumber;
	private int patientNumber;
	private String doctorName;
	private String doctorPhone;
	private String patientName;
	private String patientPhone;
	private String reservCareDate;
	private String reservCaretime;
	private String reservReaseon;
	public ReservationVO() {
		super();
	}
	public ReservationVO(String doctorName, String doctorPhone, String patientName, String patientPhone,
			String reservCareDate, String reservCaretime, String reservReaseon) {
		super();
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.reservReaseon = reservReaseon;
	}
	public ReservationVO(int reservationNumber, String doctorName, String doctorPhone, String patientName,
			String patientPhone, String reservCareDate, String reservCaretime, String reservReaseon) {
		super();
		this.reservationNumber = reservationNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.reservReaseon = reservReaseon;
	}
	public ReservationVO(int doctorNumber, int patientNumber, String doctorName, String doctorPhone, String patientName,
			String patientPhone, String reservCareDate, String reservCaretime, String reservReaseon) {
		super();
		this.doctorNumber = doctorNumber;
		this.patientNumber = patientNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.reservReaseon = reservReaseon;
	}
	public ReservationVO(int reservationNumber, int doctorNumber, int patientNumber, String doctorName,
			String doctorPhone, String patientName, String patientPhone, String reservCareDate, String reservCaretime,
			String reservReaseon) {
		super();
		this.reservationNumber = reservationNumber;
		this.doctorNumber = doctorNumber;
		this.patientNumber = patientNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.reservReaseon = reservReaseon;
	}
	public int getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public int getDoctorNumber() {
		return doctorNumber;
	}
	public void setDoctorNumber(int doctorNumber) {
		this.doctorNumber = doctorNumber;
	}
	public int getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(int patientNumber) {
		this.patientNumber = patientNumber;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getReservCareDate() {
		return reservCareDate;
	}
	public void setReservCareDate(String reservCareDate) {
		this.reservCareDate = reservCareDate;
	}
	public String getReservCaretime() {
		return reservCaretime;
	}
	public void setReservCaretime(String reservCaretime) {
		this.reservCaretime = reservCaretime;
	}
	public String getReservReaseon() {
		return reservReaseon;
	}
	public void setReservReaseon(String reservReaseon) {
		this.reservReaseon = reservReaseon;
	}
	
	
}
