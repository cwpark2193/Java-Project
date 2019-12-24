package model;

public class DiagnosisVO {
	private int diagNumber;
	private String patientName;
	private String patientGender;
	private int patientAge;
	private String patientSSN;
	private String patientPhone;
	private String patientAddress;
	private String reservCareDate;
	private String reservCaretime;
	private String careHistory;
	private String prescription;
	private String remarks;
	private String purpose;
	private String publishDate;
	private String hospitalName;
	private String hospitalAdd;
	private String doctorPhone;
	private String doctorLicense;
	private String doctorName;
	
	public DiagnosisVO() {
		super();
	}
	public DiagnosisVO(String patientName, String patientGender, int patientAge, String patientSSN, String patientPhone,
			String patientAddress, String reservCareDate, String reservCaretime, String careHistory,
			String prescription, String remarks, String purpose, String publishDate, String hospitalName,
			String hospitalAdd, String doctorPhone, String doctorLicense, String doctorName) {
		super();
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientSSN = patientSSN;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.prescription = prescription;
		this.remarks = remarks;
		this.purpose = purpose;
		this.publishDate = publishDate;
		this.hospitalName = hospitalName;
		this.hospitalAdd = hospitalAdd;
		this.doctorPhone = doctorPhone;
		this.doctorLicense = doctorLicense;
		this.doctorName = doctorName;
	}
	public DiagnosisVO(int diagNumber, String patientName, String patientGender, int patientAge, String patientSSN,
			String patientPhone, String patientAddress, String reservCareDate, String reservCaretime,
			String careHistory, String prescription, String remarks, String purpose, String publishDate,
			String hospitalName, String hospitalAdd, String doctorPhone, String doctorLicense, String doctorName) {
		super();
		this.diagNumber = diagNumber;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientSSN = patientSSN;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.reservCareDate = reservCareDate;
		this.reservCaretime = reservCaretime;
		this.careHistory = careHistory;
		this.prescription = prescription;
		this.remarks = remarks;
		this.purpose = purpose;
		this.publishDate = publishDate;
		this.hospitalName = hospitalName;
		this.hospitalAdd = hospitalAdd;
		this.doctorPhone = doctorPhone;
		this.doctorLicense = doctorLicense;
		this.doctorName = doctorName;
	}
	public int getDiagNumber() {
		return diagNumber;
	}
	public void setDiagNumber(int diagNumber) {
		this.diagNumber = diagNumber;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientSSN() {
		return patientSSN;
	}
	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
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
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalAdd() {
		return hospitalAdd;
	}
	public void setHospitalAdd(String hospitalAdd) {
		this.hospitalAdd = hospitalAdd;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getDoctorLicense() {
		return doctorLicense;
	}
	public void setDoctorLicense(String doctorLicense) {
		this.doctorLicense = doctorLicense;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
}
