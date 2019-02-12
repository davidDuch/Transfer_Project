package Model;

public class BitcoinSpace extends Wallet {

	private int maxTransSize;

	public BitcoinSpace(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			int maxTransSize) {
		super( address,  pC,  tablet,  phone,  funds,  futureValue);
		this.setMaxTransSize(maxTransSize);
		
	}

	public BitcoinSpace(String address, int maxTransSize) {
		super(address);
		this.maxTransSize = maxTransSize;
	}

	public int getMaxTransSize() {
		return maxTransSize;
	}

	public void setMaxTransSize(int maxTransSize) {
		this.maxTransSize = maxTransSize;
	}

}