package vo;

public class CookingTool {
	private int cookingToolNo;
	private String cookingToolName;
	private String updateDate;
	public CookingTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CookingTool(int cookingToolNo, String cookingToolName, String updateDate) {
		super();
		this.cookingToolNo = cookingToolNo;
		this.cookingToolName = cookingToolName;
		this.updateDate = updateDate;
	}
	public int getCookingToolNo() {
		return cookingToolNo;
	}
	public void setCookingToolNo(int cookingToolNo) {
		this.cookingToolNo = cookingToolNo;
	}
	public String getCookingToolName() {
		return cookingToolName;
	}
	public void setCookingToolName(String cookingToolName) {
		this.cookingToolName = cookingToolName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "CookingTool [cookingToolNo=" + cookingToolNo + ", cookingToolName=" + cookingToolName + ", updateDate="
				+ updateDate + "]";
	}
	
	

}
