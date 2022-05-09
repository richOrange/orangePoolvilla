package vo;

public class Facility {
	private int facilityNo;
	private String facilityName;
	private String Date;
	
	
	
	
	


	public Facility(int facilityNo, String facilityName, String date) {
		super();
		this.facilityNo = facilityNo;
		this.facilityName = facilityName;
		Date = date;
	}
	
	
	
	public Facility() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Facility [facilityNo=" + facilityNo + ", facilityName=" + facilityName + ", Date=" + Date + "]";
	}
	
	public int getFacilityNo() {
		return facilityNo;
	}
	public void setFacilityNo(int facilityNo) {
		this.facilityNo = facilityNo;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
}
