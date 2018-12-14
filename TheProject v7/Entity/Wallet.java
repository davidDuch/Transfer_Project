package Entity;

public class Wallet {

	private String address;
	private double funds;
	private double futureValue;
	private Boolean PC;
	private Boolean Tablet;
	private Boolean Phone;

	public Wallet(String address) {
		super();
		this.address = address;
	}

	public Wallet(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone) {
		super();
		this.address = address;
		this.funds = funds;
		this.futureValue = futureValue;
		PC = pC;
		Tablet = tablet;
		Phone = phone;
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

	public Double calculateFutureFunds() {
		// TODO - implement Wallet.calculateFutureFunds
		throw new UnsupportedOperationException();
	}

	public boolean upgradeWallet() {
		// TODO - implement Wallet.upgradeWallet
		throw new UnsupportedOperationException();

	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Wallet other = (Wallet) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wallet [address=" + address + ", funds=" + funds + ", futureValue=" + futureValue + ", PC=" + PC
				+ ", Tablet=" + Tablet + ", Phone=" + Phone + "]";
	}

}