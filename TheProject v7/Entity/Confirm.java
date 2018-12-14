package Entity;

import java.util.Calendar;
import java.util.Date;

import Controller.Sys;
import Utils.Status;

public class Confirm extends Transaction {

	private String buyerAddress;
	private String buyerSignature;
	private Boolean approved;
	private Calendar DateOfSupply;

	public Confirm(String id, String description, double size, Date dateCreated, Date dateApproved, Status status,
			double commission, Date dateOfSupply, Boolean approved, String buyerAddress, String buyerSignature,
			String creatorAddress, String creatorSignature, String wallet) {

		super(id, description, size, dateCreated, dateApproved, commission, status, creatorAddress, creatorSignature,
				wallet);
		this.buyerAddress = buyerAddress;
		this.buyerSignature = buyerSignature;
		this.approved = approved;
		this.DateOfSupply = (dateOfSupply != null ? Sys.toCalendar(dateOfSupply) : null);
	}


	public String getBuyerAddress() {
		return buyerAddress;
	}


	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}


	public String getBuyerSignature() {
		return buyerSignature;
	}


	public void setBuyerSignature(String buyerSignature) {
		this.buyerSignature = buyerSignature;
	}


	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Calendar getDateOfSupply() {
		return DateOfSupply;
	}

	public void setDateOfSupply(Calendar dateOfSupply) {
		DateOfSupply = dateOfSupply;
	}
	
	


	@Override
	public String toString() {
		return "Confirm [buyerAddress=" + buyerAddress + ", buyerSignature=" + buyerSignature + ", approved=" + approved
				+ ", DateOfSupply=" + DateOfSupply + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((buyerAddress == null) ? 0 : buyerAddress.hashCode());
		result = prime * result + ((buyerSignature == null) ? 0 : buyerSignature.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Confirm other = (Confirm) obj;
		if (buyerAddress == null) {
			if (other.buyerAddress != null)
				return false;
		} else if (!buyerAddress.equals(other.buyerAddress))
			return false;
		if (buyerSignature == null) {
			if (other.buyerSignature != null)
				return false;
		} else if (!buyerSignature.equals(other.buyerSignature))
			return false;
		return true;
	}



}