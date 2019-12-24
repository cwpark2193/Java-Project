package model;

public class DoctorVO {
	private int doctorNumber;
	private String doctorID;
	private String doctorPassword;
	private String doctorName;
	private String doctorGender;
	private int doctorAge;
	private String doctorSSN;
	private String doctorPhone;
	private String doctorAddress;
	private String doctorCareTime;
	private String doctorLicense;
	private String doctorImage;
	public DoctorVO() {
		super();
	}
	public DoctorVO(String doctorName, int doctorAge, String doctorPhone, String doctorAddress) {
		super();
		this.doctorName = doctorName;
		this.doctorAge = doctorAge;
		this.doctorPhone = doctorPhone;
		this.doctorAddress = doctorAddress;
	}
	public DoctorVO(String doctorName, String doctorGender, int doctorAge, String doctorSSN, String doctorPhone,
			String doctorAddress, String doctorCareTime, String doctorLicense, String doctorImage) {
		super();
		this.doctorName = doctorName;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.doctorSSN = doctorSSN;
		this.doctorPhone = doctorPhone;
		this.doctorAddress = doctorAddress;
		this.doctorCareTime = doctorCareTime;
		this.doctorLicense = doctorLicense;
		this.doctorImage = doctorImage;
	}
	public DoctorVO(String doctorID, String doctorPassword, String doctorName, String doctorGender, int doctorAge,
			String doctorSSN, String doctorPhone, String doctorAddress, String doctorCareTime, String doctorLicense,
			String doctorImage) {
		super();
		this.doctorID = doctorID;
		this.doctorPassword = doctorPassword;
		this.doctorName = doctorName;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.doctorSSN = doctorSSN;
		this.doctorPhone = doctorPhone;
		this.doctorAddress = doctorAddress;
		this.doctorCareTime = doctorCareTime;
		this.doctorLicense = doctorLicense;
		this.doctorImage = doctorImage;
	}
	public DoctorVO(int doctorNumber, String doctorID, String doctorPassword, String doctorName, String doctorGender,
			int doctorAge, String doctorSSN, String doctorPhone, String doctorAddress, String doctorCareTime,
			String doctorLicense) {
		super();
		this.doctorNumber = doctorNumber;
		this.doctorID = doctorID;
		this.doctorPassword = doctorPassword;
		this.doctorName = doctorName;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.doctorSSN = doctorSSN;
		this.doctorPhone = doctorPhone;
		this.doctorAddress = doctorAddress;
		this.doctorCareTime = doctorCareTime;
		this.doctorLicense = doctorLicense;
	}
	public DoctorVO(int doctorNumber, String doctorID, String doctorPassword, String doctorName, String doctorGender,
			int doctorAge, String doctorSSN, String doctorPhone, String doctorAddress, String doctorCareTime,
			String doctorLicense, String doctorImage) {
		this.doctorNumber = doctorNumber;
		this.doctorID = doctorID;
		this.doctorPassword = doctorPassword;
		this.doctorName = doctorName;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.doctorSSN = doctorSSN;
		this.doctorPhone = doctorPhone;
		this.doctorAddress = doctorAddress;
		this.doctorCareTime = doctorCareTime;
		this.doctorLicense = doctorLicense;
		this.doctorImage = doctorImage;
	}
	public int getDoctorNumber() {
		return doctorNumber;
	}
	public void setDoctorNumber(int doctorNumber) {
		this.doctorNumber = doctorNumber;
	}
	public String getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public String getDoctorPassword() {
		return doctorPassword;
	}
	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorGender() {
		return doctorGender;
	}
	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}
	public int getDoctorAge() {
		return doctorAge;
	}
	public void setDoctorAge(int doctorAge) {
		this.doctorAge = doctorAge;
	}
	public String getDoctorSSN() {
		return doctorSSN;
	}
	public void setDoctorSSN(String doctorSSN) {
		this.doctorSSN = doctorSSN;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public String getDoctorCareTime() {
		return doctorCareTime;
	}
	public void setDoctorCareTime(String doctorCareTime) {
		this.doctorCareTime = doctorCareTime;
	}
	public String getDoctorLicense() {
		return doctorLicense;
	}
	public void setDoctorLicense(String doctorLicense) {
		this.doctorLicense = doctorLicense;
	}
	public String getDoctorImage() {
		return doctorImage;
	}
	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}
	
}
