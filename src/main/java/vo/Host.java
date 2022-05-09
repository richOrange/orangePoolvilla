package vo;

public class Host {
	private String hostId;
	private String hostPw;
	private int level;
	private String name;
	private String email;
	private String phone;
	private String createDate;
	private String updateDate;
	public Host() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Host(String hostId, String hostPw, int level, String name, String email, String phone, String createDate,
			String updateDate) {
		super();
		this.hostId = hostId;
		this.hostPw = hostPw;
		this.level = level;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getHostPw() {
		return hostPw;
	}
	public void setHostPw(String hostPw) {
		this.hostPw = hostPw;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Host [hostId=" + hostId + ", hostPw=" + hostPw + ", level=" + level + ", name=" + name + ", email="
				+ email + ", phone=" + phone + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	

}
