package Model;

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



	public String getBuyerSignature() {
		return buyerSignature;
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

	

}