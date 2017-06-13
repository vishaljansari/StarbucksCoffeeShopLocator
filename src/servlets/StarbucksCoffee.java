package servlets;

public class StarbucksCoffee {

	private Double latitude;
	private Double longitude;
	private String location;
	private String city;
	private String zip;
	private String phone;

	public StarbucksCoffee(Double latitude, Double longitude, String city,
			String location, String zip) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.city = city;
		this.zip = zip;
	}

	public StarbucksCoffee() {

	}

	public StarbucksCoffee(Double latitude, Double longitude, String city,
			String location, String zip, String phone) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "StarbucksCoffee [latitude=" + latitude + ", longitude="
				+ longitude + ", location=" + location + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + "]";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}