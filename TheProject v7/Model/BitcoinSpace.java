package Model;

public class BitcoinSpace extends Wallet {

	private int maxTransSize;

	public BitcoinSpace(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			int maxTransSize) {
		super(address, funds, futureValue, pC, tablet, phone);
		this.maxTransSize = maxTransSize;
	}

}