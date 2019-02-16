package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Controller.Sys;
import Utils.Consts;
import Utils.Status;

public  class Transaction implements Comparable<Transaction> {

	

	private String id;
	private String description;
	private double size;
	private Date dateCreated;
	private Date dateApproved;
	private double commission;
	private Status status;
	private String creatorAddress;
	private String creatorSignature;
	private String wallet;
	private String sendType;

	

	public Transaction(String id) {
		super();
		this.id = id;
	}
	
	public Transaction(String id, double size ,String sendType, double commission ) {
		this.id = id;
		this.size=size;
		this.sendType=sendType ;
		this.commission= commission ;
	}

	public Transaction(String id, String description, double size, Date dateCreated, Date dateApproved,
			double commission, Status status, String creatorAddress, String creatorSignature, String wallet) {

		super();
		this.id = id;
		this.description = description;
		this.size = size;
		this.commission = commission;
		this.status = status;
		this.creatorAddress = creatorAddress;
		this.creatorSignature = creatorSignature;
		this.wallet = wallet;
		this.dateCreated = (dateCreated);
		this.dateApproved = (dateApproved != null ? (dateApproved) : null);

	}

	
	
	
	
	/**
	 * use it to get the wallet from the DB 
	 * 
	 * @return
	 */
	public Wallet getWalletObject() {
		
		Wallet results = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT * FROM tblWallet WHERE tblWallet.address  =  '"  +wallet  + "' ;");
				ResultSet rs = stmt.executeQuery();
				rs.next();
				results = new Wallet(rs.getString(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4), rs.getDouble(5), rs.getDouble(6));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public String getWallet() {
		return wallet;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
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

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", creatorAddress=" + creatorAddress + ", creatorSignature=" + creatorSignature
				+ "]";
	}

}