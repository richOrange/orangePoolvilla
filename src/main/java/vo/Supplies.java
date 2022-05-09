package vo;

public class Supplies {
	private int suppliesNo;
	private String suppliesName;
	private String updateDate;
	public int getSuppliesNo() {
		return suppliesNo;
	}
	public void setSuppliesNo(int suppliesNo) {
		this.suppliesNo = suppliesNo;
	}
	public String getSuppliesName() {
		return suppliesName;
	}
	public void setSuppliesName(String suppliesName) {
		this.suppliesName = suppliesName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Supplies [suppliesNo=" + suppliesNo + ", suppliesName=" + suppliesName + ", updateDate=" + updateDate
				+ "]";
	}
	public Supplies(int suppliesNo, String suppliesName, String updateDate) {
		super();
		this.suppliesNo = suppliesNo;
		this.suppliesName = suppliesName;
		this.updateDate = updateDate;
	}
	public Supplies() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
