package Entity;

public class BitcoinSpace extends Wallet {

	private int maxTransSize;

	public BitcoinSpace(String address, double funds, double futureValue, Boolean pC, Boolean tablet, Boolean phone,
			int maxTransSize) {
		super(address, funds, futureValue, pC, tablet, phone);
		this.setMaxTransSize(maxTransSize);
	}

	public int getMaxTransSize() {
		return maxTransSize;
	}

	public void setMaxTransSize(int maxTransSize) {
		this.maxTransSize = maxTransSize;
	}

}