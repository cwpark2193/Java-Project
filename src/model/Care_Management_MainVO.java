package model;

public class Care_Management_MainVO {
	private int careNumber;
	private int doctorNumber;
	private int patientNumber;
	private int reservationNumber;
	private String doctorName;
	private String doctorPhone;
	private String patientName;
	private String patientPhone;
	private String reservCareDate;
	private String reservCaretime;
	private String careHistory;
	private String Prescription;
	private int carePay;
	public Care_Management_MainVO() {
		super();
	}

	public Care_Management_MainVO(int careNumber, String doctorName, String doctorPhone, String patientName,
			String patientPhone) {
		super();
		this.careNumber = careNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
	}

	public Care_Management_MainVO(String doctorName, String doctorPhone, String patientName, String patientPhone,
			String reservCareDate, String reservCaretime, String careHistory, String Prescription, int carePay) {
		super();
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.Prescription = Prescription;
		this.carePay = carePay;
	}

	public Care_Management_MainVO(int careNumber, String doctorName, String doctorPhone, String patientName,
			String patientPhone, String reservCareDate, String reservCaretime, String careHistory, String Prescription,
			int carePay) {
		super();
		this.careNumber = careNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.Prescription = Prescription;
		this.carePay = carePay;
	}
	public Care_Management_MainVO(int doctorNumber, int patientNumber, int reservationNumber, String doctorName,
			String doctorPhone, String patientName, String patientPhone, String reservCareDate, String reservCaretime,
			String careHistory, String Prescription, int carePay) {
		super();
		this.doctorNumber = doctorNumber;
		this.patientNumber = patientNumber;
		this.reservationNumber = reservationNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.Prescription = Prescription;
		this.carePay = carePay;
	}
	public Care_Management_MainVO(int careNumber, int doctorNumber, int patientNumber, int reservationNumber,
			String doctorName, String doctorPhone, String patientName, String patientPhone, String reservCareDate,
			String reservCaretime, String careHistory, String Prescription, int carePay) {
		super();
		this.careNumber = careNumber;
		this.doctorNumber = doctorNumber;
		this.patientNumber = patientNumber;
		this.reservationNumber = reservationNumber;
		this.doctorName = doctorName;
		this.doctorPhone = doctorPhone;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.Prescription = Prescription;
		this.carePay = carePay;
	}

	public int getCareNumber() {
		return careNumber;
	}

	public void setCareNumber(int careNumber) {
		this.careNumber = careNumber;
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
	
	public int getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
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

	public String getCareHistory() {
		return careHistory;
	}

	public void setCareHistory(String careHistory) {
		this.careHistory = careHistory;
	}

	public String getPrescription() {
		return Prescription;
	}

	public void setPrescription(String Prescription) {
		this.Prescription = Prescription;
	}

	public int getCarePay() {
		return carePay;
	}

	public void setCarePay(int carePay) {
		this.carePay = carePay;
	}

}
