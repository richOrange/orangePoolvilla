package vo;

public class PoolvillaLocation {
	private int locationNo;
	private String locationName;
	private String updateDate;
	
	public PoolvillaLocation() {
		super();
	}
	
	public PoolvillaLocation(int locationNo, String locationName, String updateDate) {
		super();
		this.locationNo = locationNo;
		this.locationName = locationName;
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "Location [locationNo=" + locationNo + ", locationName=" + locationName + ", updateDate=" + updateDate
				+ "]";
	}
	
	public int getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(int locationNo) {
		this.locationNo = locationNo;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}
