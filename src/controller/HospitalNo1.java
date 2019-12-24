package controller;


// 테이블뷰와 일치하기 위해 만든 VO 건의게시판 / 칭찬게시판에 연관이 되어야 함

public class HospitalNo1 {
	
	private String No1;
	private String No2;
	private String No3;
	
	public HospitalNo1(String no1, String no2, String no3) {
		super();
		No1 = no1;
		No2 = no2;
		No3 = no3;
	}
	public HospitalNo1() {
		// TODO Auto-generated constructor stub
	}
	public String getNo1() {
		return No1;
	}
	public void setNo1(String no1) {
		No1 = no1;
	}
	public String getNo2() {
		return No2;
	}
	public void setNo2(String no2) {
		No2 = no2;
	}
	public String getNo3() {
		return No3;
	}
	public void setNo3(String no3) {
		No3 = no3;
	}
}

	
	
