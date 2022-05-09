package vo;

public class WishList {
	private int pvNo;
	private String customerId;
	private String updateDate;
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "WishList [pvNo=" + pvNo + ", customerId=" + customerId + ", updateDate=" + updateDate + "]";
	}
	public WishList(int pvNo, String customerId, String updateDate) {
		super();
		this.pvNo = pvNo;
		this.customerId = customerId;
		this.updateDate = updateDate;
	}
	public WishList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
