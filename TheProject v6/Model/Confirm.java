package Model;

import java.util.Calendar;
import java.util.Date;

import Controller.Sys;
import Utils.Status;

public class Confirm extends Transaction {

	private String buyer;
	private Boolean approved;
	private Calendar DateOfSupply;

	public Confirm(String id, String description, double size, Date dateCreated, Date dateApproved, Status status,
			double commission, Date dateOfSupply, Boolean approved, String buyerAddress, String buyerSignature,
			String creatorAddress, String creatorSignature, String wallet) {

		super(id, description, size, dateCreated, dateApproved, commission, status,
				creatorAddress, creatorSignature,wallet);
		this.buyer = buyer;
		this.approved = approved;
		this.DateOfSupply = (dateOfSupply != null ? Sys.toCalendar(dateOfSupply) : null);
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
		return "Confirm [buyer=" + buyer + ", approved=" + approved + ", DateOfSupply=" + DateOfSupply + "]";
	}

}