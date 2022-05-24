package vo;

public class RoomPhoto {
	private int photoNo;
	private int roomNo;
	private String photoName;
	private String photoOriginalName;
	private String photoType;
	private String photoArea;
	private String createDate;
	private String updateDate;
	public RoomPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomPhoto(int photoNo, int roomNo, String photoName, String photoOriginalName, String photoType,
			String photoArea, String createDate, String updateDate) {
		super();
		this.photoNo = photoNo;
		this.roomNo = roomNo;
		this.photoName = photoName;
		this.photoOriginalName = photoOriginalName;
		this.photoType = photoType;
		this.photoArea = photoArea;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoOriginalName() {
		return photoOriginalName;
	}
	public void setPhotoOriginalName(String photoOriginalName) {
		this.photoOriginalName = photoOriginalName;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public String getPhotoArea() {
		return photoArea;
	}
	public void setPhotoArea(String photoArea) {
		this.photoArea = photoArea;
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
	@Override
	public String toString() {
		return "RoomPhoto [photoNo=" + photoNo + ", roomNo=" + roomNo + ", photoName=" + photoName
				+ ", photoOriginalName=" + photoOriginalName + ", photoType=" + photoType + ", photoArea=" + photoArea
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
}
