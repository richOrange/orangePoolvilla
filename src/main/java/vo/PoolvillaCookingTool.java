package vo;

public class PoolvillaCookingTool {
	private int pvNo;
	private int cookingToolNo;
	private int cookingToolCnt;
	private String updateDate;
	public PoolvillaCookingTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaCookingTool(int pvNo, int cookingToolNo, int cookingToolCnt, String updateDate) {
		super();
		this.pvNo = pvNo;
		this.cookingToolNo = cookingToolNo;
		this.cookingToolCnt = cookingToolCnt;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PoolvillaCookingTool [pvNo=" + pvNo + ", cookingToolNo=" + cookingToolNo + ", cookingToolCnt="
				+ cookingToolCnt + ", updateDate=" + updateDate + "]";
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public int getCookingToolNo() {
		return cookingToolNo;
	}
	public void setCookingToolNo(int cookingToolNo) {
		this.cookingToolNo = cookingToolNo;
	}
	public int getCookingToolCnt() {
		return cookingToolCnt;
	}
	public void setCookingToolCnt(int cookingToolCnt) {
		this.cookingToolCnt = cookingToolCnt;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
