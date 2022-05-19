package vo;
//예약상태변경이력 테이블
public class ReservationStatusHistory {
	private int reservationNo;
	private String reservationStatus;
	private String reservationStatusUpdateDate;
	
	
	public ReservationStatusHistory() {
	}


	public ReservationStatusHistory(int reservationNo, String reservationStatus, String reservationStatusUpdateDate) {
		super();
		this.reservationNo = reservationNo;
		this.reservationStatus = reservationStatus;
		this.reservationStatusUpdateDate = reservationStatusUpdateDate;
	}


	@Override
	public String toString() {
		return "ReservationStatusHistory [reservationNo=" + reservationNo + ", reservationStatus=" + reservationStatus
				+ ", reservationStatusUpdateDate=" + reservationStatusUpdateDate + "]";
	}


	public int getReservationNo() {
		return reservationNo;
	}


	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}


	public String getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}


	public String getReservationStatusUpdateDate() {
		return reservationStatusUpdateDate;
	}


	public void setReservationStatusUpdateDate(String reservationStatusUpdateDate) {
		this.reservationStatusUpdateDate = reservationStatusUpdateDate;
	}
	
	

	
	

}
