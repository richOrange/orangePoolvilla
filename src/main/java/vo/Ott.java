package vo;

public class Ott {
	private int ottNo;
	private String ottName;
	private String updateDate;
	public Ott() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ott(int ottNo, String ottName, String updateDate) {
		super();
		this.ottNo = ottNo;
		this.ottName = ottName;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Ott [ottNo=" + ottNo + ", ottName=" + ottName + ", updateDate=" + updateDate + "]";
	}
	public int getOttNo() {
		return ottNo;
	}
	public void setOttNo(int ottNo) {
		this.ottNo = ottNo;
	}
	public String getOttName() {
		return ottName;
	}
	public void setOttName(String ottName) {
		this.ottName = ottName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
