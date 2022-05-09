package vo;

public class PoolvillaSupplies {
	private int pvNo;
	private int suppliesNo;
	private int suppliesCnt;
	private String updateDate;
	public PoolvillaSupplies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaSupplies(int pvNo, int suppliesNo, int suppliesCnt, String updateDate) {
		super();
		this.pvNo = pvNo;
		this.suppliesNo = suppliesNo;
		this.suppliesCnt = suppliesCnt;
		this.updateDate = updateDate;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public int getSuppliesNo() {
		return suppliesNo;
	}
	public void setSuppliesNo(int suppliesNo) {
		this.suppliesNo = suppliesNo;
	}
	public int getSuppliesCnt() {
		return suppliesCnt;
	}
	public void setSuppliesCnt(int suppliesCnt) {
		this.suppliesCnt = suppliesCnt;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PoolvillaSupplies [pvNo=" + pvNo + ", suppliesNo=" + suppliesNo + ", suppliesCnt=" + suppliesCnt
				+ ", updateDate=" + updateDate + "]";
	}
	

}
