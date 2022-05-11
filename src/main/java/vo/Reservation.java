package vo;

public class Reservation {
	private int reservationNo;
	private String customerId;
	private String pvNo;
	private String reservationBeginDate;
	private String reservationLastDate;
	private String reservationStatus;
	private String createDate;
	private String updateDate;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(int reservationNo, String customerId, String pvNo, String reservationBeginDate,
			String reservationLastDate, String reservationStatus, String createDate, String updateDate) {
		super();
		this.reservationNo = reservationNo;
		this.customerId = customerId;
		this.pvNo = pvNo;
		this.reservationBeginDate = reservationBeginDate;
		this.reservationLastDate = reservationLastDate;
		this.reservationStatus = reservationStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Reservation [reservationNo=" + reservationNo + ", customerId=" + customerId + ", pvNo=" + pvNo
				+ ", reservationBeginDate=" + reservationBeginDate + ", reservationLastDate=" + reservationLastDate
				+ ", reservationStatus=" + reservationStatus + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPvNo() {
		return pvNo;
	}
	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}
	public String getReservationBeginDate() {
		return reservationBeginDate;
	}
	public void setReservationBeginDate(String reservationBeginDate) {
		this.reservationBeginDate = reservationBeginDate;
	}
	public String getReservationLastDate() {
		return reservationLastDate;
	}
	public void setReservationLastDate(String reservationLastDate) {
		this.reservationLastDate = reservationLastDate;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
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
