package vo;

public class RoomBed {
	private int bedNo;
	private int roomNo;
	private String bedSize;
	private int bedCnt;
	private String updateDate;
	public RoomBed() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomBed(int bedNo, int roomNo, String bedSize, int bedCnt, String updateDate) {
		super();
		this.bedNo = bedNo;
		this.roomNo = roomNo;
		this.bedSize = bedSize;
		this.bedCnt = bedCnt;
		this.updateDate = updateDate;
	}
	public int getBedNo() {
		return bedNo;
	}
	public void setBedNo(int bedNo) {
		this.bedNo = bedNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getBedSize() {
		return bedSize;
	}
	public void setBedSize(String bedSize) {
		this.bedSize = bedSize;
	}
	public int getBedCnt() {
		return bedCnt;
	}
	public void setBedCnt(int bedCnt) {
		this.bedCnt = bedCnt;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "RoomBed [bedNo=" + bedNo + ", roomNo=" + roomNo + ", bedSize=" + bedSize + ", bedCnt=" + bedCnt
				+ ", updateDate=" + updateDate + "]";
	}
	
	

}
