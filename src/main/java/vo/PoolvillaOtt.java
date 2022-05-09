package vo;

public class PoolvillaOtt {
	private int pvNo;
	private int ottNo;
	private String updateDate;
	public PoolvillaOtt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaOtt(int pvNo, int ottNo, String updateDate) {
		super();
		this.pvNo = pvNo;
		this.ottNo = ottNo;
		this.updateDate = updateDate;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public int getOttNo() {
		return ottNo;
	}
	public void setOttNo(int ottNo) {
		this.ottNo = ottNo;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PoolvillaOtt [pvNo=" + pvNo + ", ottNo=" + ottNo + ", updateDate=" + updateDate + "]";
	}
	
	

}
