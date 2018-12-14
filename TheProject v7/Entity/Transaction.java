package Entity;

import java.util.Calendar;
import java.util.Date;

import Controller.Sys;
import Utils.Status;

public abstract class Transaction implements Comparable<Transaction> {

	private String id;
	private String description;
	private double size;
	private Calendar dateCreated;
	private Calendar dateApproved;
	private double commission;
	private Status status;
	private String creatorAddress;
	private String creatorSignature;
	private String wallet;

	
	public Transaction(String id) {
		super();
		this.id = id;
	}

	public Transaction(String id, String description, double size, Date dateCreated, Date dateApproved,
			double commission, Status status, String creatorAddress, String creatorSignature, String Wallet) {

		super();
		this.id = id;
		this.description = description;
		this.size = size;
		this.commission = commission;
		this.status = status;
		this.creatorAddress = creatorAddress;
		this.creatorSignature = creatorSignature;
		this.dateCreated = Sys.toCalendar(dateCreated);
		this.dateApproved = (dateApproved != null ? Sys.toCalendar(dateApproved) : null);

	}
	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public String getCreatorAddress() {
		return creatorAddress;
	}

	public void setCreatorAddress(String creatorAddress) {
		this.creatorAddress = creatorAddress;
	}

	public String getCreatorSignature() {
		return creatorSignature;
	}

	public void setCreatorSignature(String creatorSignature) {
		this.creatorSignature = creatorSignature;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Calendar getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Calendar dateApproved) {
		this.dateApproved = dateApproved;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int compareTo(Transaction o) {

		if (dateCreated.before(o.dateCreated))
			return -1;
		else if (!dateCreated.before(o.dateCreated))
			return 1;

		else if (!this.equals(o))
			return 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}