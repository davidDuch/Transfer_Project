package Model;

public class BitcoinSpace extends Wallet {

	private int maxTransSize;

	public BitcoinSpace(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			int maxTransSize) {
		super( address,  pC,  tablet,  phone,  funds,  futureValue);
		this.maxTransSize = maxTransSize;
		
	}

}