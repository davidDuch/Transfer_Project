package Model;
public class User {

	private String userName;
	private String phoneNumber;
	private String email;
	private String password;
	private String PublicAddress;
	private String DigitalSignature;




	public User(String PublicAddress , String DigitalSignature) {
		super();
		
		this.PublicAddress = PublicAddress;
		this.DigitalSignature = DigitalSignature;
	}

	public User(String userName, String phoneNumber, String email, String password, String publicAddress,
			String digitalSignature) {
		super();
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		PublicAddress = publicAddress;
		DigitalSignature = digitalSignature;
	}




	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublicAddress() {
		return PublicAddress;
	}

	public void setPublicAddress(String publicAddress) {
		PublicAddress = publicAddress;
	}

	public String getDigitalSignature() {
		return DigitalSignature;
	}

	public void setDigitalSignature(String digitalSignature) {
		DigitalSignature = digitalSignature;
	}

	public boolean PurchaseWallet() {
		// TODO - implement User.PurchaseWallet
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DigitalSignature == null) ? 0 : DigitalSignature.hashCode());
		result = prime * result + ((PublicAddress == null) ? 0 : PublicAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;

		String identifier1 = other.PublicAddress + other.DigitalSignature;
		String identifier2 = PublicAddress + DigitalSignature;

		if(!identifier2.equals(identifier1))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + ", PublicAddress=" + PublicAddress + ", DigitalSignature=" + DigitalSignature + "]";
	}




}