package vo;

public class PoolvillaRoom {
	private int roomNo;
	private int pvNo;
	private String roomType;
	private String roomName;
	private String roomInfo;
	private double roomSize;
	private String updateDate;
	
	
	
	


	public PoolvillaRoom(int roomNo, int pvNo, String roomType, String roomName, String roomInfo, double roomSize,
			String updateDate) {
		super();
		this.roomNo = roomNo;
		this.pvNo = pvNo;
		this.roomType = roomType;
		this.roomName = roomName;
		this.roomInfo = roomInfo;
		this.roomSize = roomSize;
		this.updateDate = updateDate;
	}
	

	public PoolvillaRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PoolvillaRoom [roomNo=" + roomNo + ", pvNo=" + pvNo + ", roomType=" + roomType + ", roomName="
				+ roomName + ", roomInfo=" + roomInfo + ", roomSize=" + roomSize + ", updateDate=" + updateDate + "]";
	}
	
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomInfo() {
		return roomInfo;
	}
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
	public double getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(double roomSize) {
		this.roomSize = roomSize;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
}
