package vo;

public class Order {
	private int orderNo;
	private String customerId;
	private String pvNo;
	private String reservationBeginDate;
	private String reservationLastDate;
	private String orderStatus;
	private String createDate;
	private String updateDate;
	
	public Order() {
		super();
	}
	
	public Order(int orderNo, String customerId, String pvNo, String reservationBeginDate, String reservationLastDate,
			String orderStatus, String createDate, String updateDate) {
		super();
		this.orderNo = orderNo;
		this.customerId = customerId;
		this.pvNo = pvNo;
		this.reservationBeginDate = reservationBeginDate;
		this.reservationLastDate = reservationLastDate;
		this.orderStatus = orderStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", customerId=" + customerId + ", pvNo=" + pvNo + ", reservationBeginDate="
				+ reservationBeginDate + ", reservationLastDate=" + reservationLastDate + ", orderStatus=" + orderStatus
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
