package vo;

public class PoolvillaPhoto {
	private int photoNo;
	private int pvNo;
	private String photoName;
	private String photoOriginalName;
	private String photoType;
	private String photoArea;
	private String createDate;
	private String updateDate;
	public PoolvillaPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaPhoto(int photoNo, int pvNo, String photoName, String photoOriginalName, String photoType,
			 String photoArea, String createDate, String updateDate) {
		super();
		this.photoNo = photoNo;
		this.pvNo = pvNo;
		this.photoName = photoName;
		this.photoOriginalName = photoOriginalName;
		this.photoType = photoType;
		this.photoArea = photoArea;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PoolvillaPhoto [photoNo=" + photoNo + ", pvNo=" + pvNo + ", photoName=" + photoName
				+ ", photoOriginalName=" + photoOriginalName + ", photoType=" + photoType 
				+ ", photoArea=" + photoArea + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
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
	
}
