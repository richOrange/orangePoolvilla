package vo;

public class Review {
	private int reviewNo;
	private int cleanliness;
	private String revisit;
	private int satisfaction;
	private String opinion;
	private String reviewContents;
	private String createDate;
	private String updateDate;
	private String reviewActive;
	private int orderNo;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int reviewNo, int cleanliness, String revisit, int satisfaction, String opinion,
			String reviewContents, String createDate, String updateDate, String reviewActive, int orderNo) {
		super();
		this.reviewNo = reviewNo;
		this.cleanliness = cleanliness;
		this.revisit = revisit;
		this.satisfaction = satisfaction;
		this.opinion = opinion;
		this.reviewContents = reviewContents;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.reviewActive = reviewActive;
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", cleanliness=" + cleanliness + ", revisit=" + revisit
				+ ", satisfaction=" + satisfaction + ", opinion=" + opinion + ", reviewContents=" + reviewContents
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", reviewActive=" + reviewActive
				+ ", orderNo=" + orderNo + "]";
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getCleanliness() {
		return cleanliness;
	}
	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}
	public String getRevisit() {
		return revisit;
	}
	public void setRevisit(String revisit) {
		this.revisit = revisit;
	}
	public int getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getReviewContents() {
		return reviewContents;
	}
	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
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
	public String getReviewActive() {
		return reviewActive;
	}
	public void setReviewActive(String reviewActive) {
		this.reviewActive = reviewActive;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
}
