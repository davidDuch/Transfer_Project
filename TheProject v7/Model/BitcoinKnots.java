package Model;

public class BitcoinKnots extends Wallet {
	private double discount;

	public BitcoinKnots(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			double discount) {
		super( address,  pC,  tablet,  phone,  funds,  futureValue);
		this.discount = discount;
	}

	public BitcoinKnots(String address, double discount) {
		super(address);
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}