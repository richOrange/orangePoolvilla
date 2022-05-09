package vo;

public class Customer {
	private String customerId;
	private String customerPw;
	private String name;
	private String gender;
	private String birthDate;
	private String email;
	private String phone;
	private int level;
	private String createDate;
	private String updateDate;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerId, String customerPw, String name, String gender, String birthDate, String email,
			String phone, int level, String createDate, String updateDate) {
		super();
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.level = level;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerPw=" + customerPw + ", name=" + name + ", gender="
				+ gender + ", birthDate=" + birthDate + ", email=" + email + ", phone=" + phone + ", level=" + level
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerPw() {
		return customerPw;
	}
	public void setCustomerPw(String customerPw) {
		this.customerPw = customerPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
