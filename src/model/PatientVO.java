package model;

public class PatientVO {
	private int patientNumber;
	private String patientID;
	private String patientPassword;
	private String patientName;
	private String patientGender;
	private int patientAge;
	private String patientSSN;
	private String patientPhone;
	private String patientAddress;
	private String patientImage;
	public PatientVO() {
		super();
	}
	
	public PatientVO(String patientName, int patientAge, String patientPhone, String patientAddress) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
	}

	public PatientVO(String patientName, String patientGender, int patientAge, String patientSSN, String patientPhone,
			String patientAddress, String patientImage) {
		super();
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientSSN = patientSSN;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.patientImage = patientImage;
	}

	public PatientVO(String patientID, String patientPassword, String patientName, String patientGender, int patientAge,
			String patientSSN, String patientPhone, String patientAddress, String patientImage) {
		super();
		this.patientID = patientID;
		this.patientPassword = patientPassword;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientSSN = patientSSN;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.patientImage = patientImage;
	}

	public PatientVO(int patientNumber, String patientID, String patientPassword, String patientName,
			String patientGender, int patientAge, String patientSSN, String patientPhone, String patientAddress,
			String patientImage) {
		super();
		this.patientNumber = patientNumber;
		this.patientID = patientID;
		this.patientPassword = patientPassword;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientSSN = patientSSN;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.patientImage = patientImage;
	}

	public int getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(int patientNumber) {
		this.patientNumber = patientNumber;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getPatientPassword() {
		return patientPassword;
	}
	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
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
	public String getPatientImage() {
		return patientImage;
	}
	public void setPatientImage(String patientImage) {
		this.patientImage = patientImage;
	}
		
}
