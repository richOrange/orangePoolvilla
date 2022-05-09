package vo;

public class Address {
	private int addressNo;
	private String zipCode;
	private String privince;
	private String city;
	private String town;
	private String street;
	private String building1;
	
	public Address() {
		super();
	}
	
	public Address(int addressNo, String zipCode, String privince, String city, String town, String street,
			String building1) {
		super();
		this.addressNo = addressNo;
		this.zipCode = zipCode;
		this.privince = privince;
		this.city = city;
		this.town = town;
		this.street = street;
		this.building1 = building1;
	}

	@Override
	public String toString() {
		return "Address [addressNo=" + addressNo + ", zipCode=" + zipCode + ", privince=" + privince + ", city=" + city
				+ ", town=" + town + ", street=" + street + ", building1=" + building1 + "]";
	}
	
	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPrivince() {
		return privince;
	}
	public void setPrivince(String privince) {
		this.privince = privince;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getBuilding1() {
		return building1;
	}
	public void setBuilding1(String building1) {
		this.building1 = building1;
	}
}
