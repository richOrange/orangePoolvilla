package vo;

public class Poolvilla {
	private int pvNo;
	private String hostId;
	private int locationNo;
	private String locationName;
	private int addressNo;
	private String address;
	private String pvDetailaddr;
	private String pvName;
	private int price;
	private double pvSize;
	private int pvFloor;
	private int pvPeaple;
	private String createDate;
	private String updateDate;
	
	public Poolvilla() {}
	
	public Poolvilla(int pvNo, String hostId, int locationNo, String locationName, int addressNo, String address,
			String pvDetailaddr, String pvName, int price, double pvSize, int pvFloor, int pvPeaple, String createDate,
			String updateDate) {
		super();
		this.pvNo = pvNo;
		this.hostId = hostId;
		this.locationNo = locationNo;
		this.locationName = locationName;
		this.addressNo = addressNo;
		this.address = address;
		this.pvDetailaddr = pvDetailaddr;
		this.pvName = pvName;
		this.price = price;
		this.pvSize = pvSize;
		this.pvFloor = pvFloor;
		this.pvPeaple = pvPeaple;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Poolvilla [pvNo=" + pvNo + ", hostId=" + hostId + ", locationNo=" + locationNo + ", locationName="
				+ locationName + ", addressNo=" + addressNo + ", address=" + address + ", pvDetailaddr=" + pvDetailaddr
				+ ", pvName=" + pvName + ", price=" + price + ", pvSize=" + pvSize + ", pvFloor=" + pvFloor
				+ ", pvPeaple=" + pvPeaple + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

	public int getPvNo() {
		return pvNo;
	}

	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
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

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPvDetailaddr() {
		return pvDetailaddr;
	}

	public void setPvDetailaddr(String pvDetailaddr) {
		this.pvDetailaddr = pvDetailaddr;
	}

	public String getPvName() {
		return pvName;
	}

	public void setPvName(String pvName) {
		this.pvName = pvName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getPvSize() {
		return pvSize;
	}

	public void setPvSize(double pvSize) {
		this.pvSize = pvSize;
	}

	public int getPvFloor() {
		return pvFloor;
	}

	public void setPvFloor(int pvFloor) {
		this.pvFloor = pvFloor;
	}

	public int getPvPeaple() {
		return pvPeaple;
	}

	public void setPvPeaple(int pvPeaple) {
		this.pvPeaple = pvPeaple;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	
		
}
