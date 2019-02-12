package Model;

public class WalletType {

	private String address;
	private double funds;
	private double futureValue;
	private Boolean PC;
	private Boolean Tablet;
	private Boolean Phone; 
	private String type;  
	private double discount; //KNOTS
	private double maxTransSize; //SPACE 
	
	public WalletType(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			String type, double discount, double maxTransSize) {
		this.address = address;
		this.funds = funds;
		this.futureValue = futureValue;
		PC = pC;
		Tablet = tablet;
		Phone = phone;
		this.type = type;
		this.discount = discount;
		this.maxTransSize = maxTransSize;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}

	public double getFutureValue() {
		return futureValue;
	}

	public void setFutureValue(double futureValue) {
		this.futureValue = futureValue;
	}

	public Boolean getPC() {
		return PC;
	}

	public void setPC(Boolean pC) {
		PC = pC;
	}

	public Boolean getTablet() {
		return Tablet;
	}

	public void setTablet(Boolean tablet) {
		Tablet = tablet;
	}

	public Boolean getPhone() {
		return Phone;
	}

	public void setPhone(Boolean phone) {
		Phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getMaxTransSize() {
		return maxTransSize;
	}

	public void setMaxTransSize(double maxTransSize) {
		this.maxTransSize = maxTransSize;
	}

	
}
