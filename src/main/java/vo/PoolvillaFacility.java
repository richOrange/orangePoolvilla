package vo;

public class PoolvillaFacility {
	private int pvNo;
	private int facilityNo;
	private String updateDate;
	private int facilityCnt;
	public PoolvillaFacility() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaFacility(int pvNo, int facilityNo, String updateDate, int facilityCnt) {
		super();
		this.pvNo = pvNo;
		this.facilityNo = facilityNo;
		this.updateDate = updateDate;
		this.facilityCnt = facilityCnt;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public int getFacilityNo() {
		return facilityNo;
	}
	public void setFacilityNo(int facilityNo) {
		this.facilityNo = facilityNo;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getFacilityCnt() {
		return facilityCnt;
	}
	public void setFacilityCnt(int facilityCnt) {
		this.facilityCnt = facilityCnt;
	}
	@Override
	public String toString() {
		return "PoolvillaFacility [pvNo=" + pvNo + ", facilityNo=" + facilityNo + ", updateDate=" + updateDate
				+ ", facilityCnt=" + facilityCnt + "]";
	}
	
	

}
