package Model;
public class BitcoinKnots extends Wallet {

	private double discount;



	public BitcoinKnots(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone , double discount) {
		super( address,  funds,  futureValue,  pC,  tablet,  phone);
		this.discount = discount;
	}

	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


	


	
	
	
}