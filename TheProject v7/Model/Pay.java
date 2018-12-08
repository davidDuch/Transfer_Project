package Model;

import java.util.Date;

import Utils.Status;

public class Pay extends Transaction {

	private String sellerAddress;
	private String sellerSignature;
	private Double BtcAmount;

	public Pay(String id) {
		super(id);

	}

	public Pay(String id, String description, double size, Date dateCreated, Date dateApproved, Status status,
			double btcAmount, double commission, String sellerAddress, String sellerSignature, String creatorAddress,
			String creatorSignature, String wallet) {

		super(id, description, size, dateCreated, dateApproved, commission, status, creatorAddress, creatorSignature,
				wallet);

		this.sellerSignature = sellerSignature;
		this.sellerAddress = sellerAddress;
		this.BtcAmount = btcAmount;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String seller) {
		this.sellerAddress = seller;
	}

	public Double getBtcAmount() {
		return BtcAmount;
	}

	public void setBtcAmount(Double btcAmount) {
		BtcAmount = btcAmount;
	}

	@Override
	public String toString() {
		return "Pay [seller=" + sellerAddress + ", BtcAmount=" + BtcAmount + "]";
	}

	public String getSellerSignature() {
		return sellerSignature;
	}

	public void setSellerSignature(String sellerSignature) {
		this.sellerSignature = sellerSignature;
	}

}