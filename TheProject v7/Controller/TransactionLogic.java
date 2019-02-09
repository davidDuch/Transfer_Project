package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.JarException;

import javax.swing.JFrame;

import Model.Confirm;
import Model.Pay;
import Model.Transaction;
import Model.Wallet;
import Utils.Consts;
import Utils.Status;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class TransactionLogic {

	private static TransactionLogic instance;

	public static TransactionLogic getInstance() {
		if (instance == null)
			instance = new TransactionLogic();
		return instance;
	}

	/**
	 * 
	 * Adds a new Confirm Transaction
	 * 
	 * @param id
	 * @param description
	 * @param size
	 * @param dateCreated
	 * @param dateApproved
	 * @param status
	 * @param commission
	 * @param dateOfSupply
	 * @param approved
	 * @param buyerAddress
	 * @param buyerSignature
	 * @param creatorAddress
	 * @param creatorSignature
	 * @param wallet
	 * @return
	 */
	public static boolean addConfirm(String id, String description, double size, Date dateCreated, Date dateApproved,
			Status status, double commission, Date dateOfSupply, Boolean approved, String buyerAddress,
			String buyerSignature, String creatorAddress, String creatorSignature, String wallet) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_NEWCONFIRM)) {
				int i = 1;

				stmt.setString(i++, id);
				stmt.setString(i++, description);
				stmt.setDouble(i++, size);
				stmt.setDate(i++, new java.sql.Date(dateCreated.getTime()));
				stmt.setDate(i++, new java.sql.Date(dateApproved.getTime()));
				stmt.setString(i++, status.toString());
				stmt.setDouble(i++, commission);
				stmt.setDate(i++, new java.sql.Date(dateOfSupply.getTime()));
				stmt.setBoolean(i++, approved);
				stmt.setString(i++, buyerAddress);
				stmt.setString(i++, buyerSignature);
				stmt.setString(i++, creatorAddress);
				stmt.setString(i++, creatorSignature);
				stmt.setString(i++, wallet);

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}

	public JFrame createReport() throws SQLException, JarException, ClassNotFoundException, JRException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
			JasperPrint print = JasperFillManager
					.fillReport(getClass().getResourceAsStream("/Model/TransactionsReport.jasper"), null, conn);
			JFrame frame = new JFrame("Customer Orders Report");
			frame.getContentPane().add(new JRViewer(print));
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.pack();
			return frame;
		}
	}

	/**
	 * add Pay Transaction to DB
	 * 
	 * @param id
	 * @param description
	 * @param size
	 * @param dateCreated
	 * @param dateApproved
	 * @param status
	 * @param btcAmount
	 * @param commission
	 * @param sellerAddress
	 * @param sellerSignature
	 * @param creatorAddress
	 * @param creatorSignature
	 * @param wallet
	 * @return
	 */
	public static boolean addPay(String id, String description, double size, Date dateCreated, Date dateApproved,
			Status status, double btcAmount, double commission, String sellerAddress, String sellerSignature,
			String creatorAddress, String creatorSignature, String wallet) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_NEWPAY)) {
				int i = 1;

				stmt.setString(i++, id);
				stmt.setString(i++, description);
				stmt.setDouble(i++, size);
				stmt.setDate(i++, new java.sql.Date(dateCreated.getDate()));
				stmt.setDate(i++, new java.sql.Date(dateApproved.getTime()));
				stmt.setString(i++, status.toString());
				stmt.setDouble(i++, btcAmount);
				stmt.setDouble(i++, commission);
				stmt.setString(i++, sellerAddress);
				stmt.setString(i++, sellerSignature);
				stmt.setString(i++, creatorAddress);
				stmt.setString(i++, creatorSignature);
				stmt.setString(i++, wallet);

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * A method to get all Confirm transactions from the DB
	 * 
	 * @return ArrayList of ConfirmTransactions
	 */
	public static ArrayList<Confirm> getConfirmTransactions() {
		ArrayList<Confirm> results = new ArrayList<Confirm>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CONFIRMTRANSACTIONS);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int i = 1;
					results.add(new Confirm(rs.getString(i++), rs.getString(i++), rs.getDouble(i++), rs.getDate(i++),
							rs.getDate(i++), Status.valueOf(rs.getString(i++)), rs.getDouble(i++), rs.getDate(i++),
							rs.getBoolean(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * A method to get all Pay transactions from the DB
	 * 
	 * @return ArrayList of PayTransactions
	 */
	public static ArrayList<Pay> getPayTransactions() {
		ArrayList<Pay> results = new ArrayList<Pay>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_PAYTRANSACTIONS);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int i = 1;
					results.add(new Pay(rs.getString(i++), rs.getString(i++), rs.getDouble(i++), rs.getDate(i++),
							rs.getDate(i++), Status.valueOf(rs.getString(i++)), rs.getDouble(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static ArrayList<Transaction> getAllWaitingTrans() {

		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ALLWAITING);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					results.add(new Transaction(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4)));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void updateTransaction(String id, String type) {

		if (type.equals("Confirm")) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call updateConfirm(?) };");

					stmt.setString(1, id);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} else if (type.equals("Pay")) {

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call updatePay(?) };");

					stmt.setString(1, id);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * use this method only when confirm and pay are executed
	 * 
	 * @param pay
	 * @param confirm
	 */
	public static void TransferFunds(Pay pay, Confirm confirm) {

		// TODO problems with understanding when this method should be used
		// the problem is that you cant know which confirm was made for each user
		double amountToTransfer = pay.getBtcAmount();
		Wallet buyer = pay.getWalletObject();
		Wallet seller = confirm.getWalletObject();

		buyer.setFutureValue(buyer.getFutureValue() + amountToTransfer);
		seller.setFutureValue(buyer.getFutureValue() - amountToTransfer);

		buyer.setFunds(buyer.getFunds() - amountToTransfer);
		seller.setFunds(seller.getFunds() + amountToTransfer);

		UserLogic.updateWalletFunds(buyer);
		UserLogic.updateWalletFunds(seller);
		
		// close the transactions
		updateTransactionStatus(confirm, Status.closed);
		updateTransactionStatus(pay, Status.closed);

	}

	
	
	
	/**
	 * method for closing the transactions
	 * 
	 * 
	 * 
	 * @param trans
	 * @param status
	 */
	public static void updateTransactionStatus(Transaction trans, Status status) {

		
		
	
		//find first the type of the transaction
		
		
		if (trans instanceof Pay) {

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call updateStatusPay(?,?) };");

					
					// close the transaction
					stmt.setString(1, trans.getId());
					stmt.setString(2, status.toString());
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} else {

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call updateStatusConfirm(?,?) };");

					// close the transaction
					stmt.setString(1, trans.getId());
					stmt.setString(2, status.toString());
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	public JFrame createReport2() throws SQLException, JarException, ClassNotFoundException, JRException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
			JasperPrint print = JasperFillManager
					.fillReport(getClass().getResourceAsStream("/Model/UserReport.jasper"), null, conn);
			JFrame frame = new JFrame("Customer Orders Report");
			frame.getContentPane().add(new JRViewer(print));
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.pack();
			return frame;
		}
	}
	
	
	public void UserReport() throws JarException, ClassNotFoundException, SQLException, JRException {
		prepareForReport();
		createReport2().show();
		
}

	private void prepareForReport() {
		
		/*
		 * 1) update superUnion
		 * 
		 * 2) update  todaysTransactions
		 * 
		 * 3) use createUserReport for the tblUserReport
		 * 
		 */
		
		
		// 1) updates super union table
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT tblTransactionPay.transactionId, tblTransactionPay.buyerAddress AS Address, tblTransactionPay.buyerSignature AS Signature, tblTransactionPay.status, tblTransactionPay.commission , tblTransactionPay.creationDate,tblTransactionPay.size AS size,'Pay' AS type\r\n" + 
						"FROM tblTransactionPay\r\n" + 
						"WHERE tblTransactionPay.creationDate<=Date()\r\n" + 
						"UNION ALL SELECT tblTransactionConfirm.transactionId, tblTransactionConfirm.sellerAddress AS Address, tblTransactionConfirm.sellerSignature AS Signature, tblTransactionConfirm.status,tblTransactionConfirm.commission, tblTransactionConfirm.creationDate,tblTransactionConfirm.size AS size,'Confirm' AS type \r\n" + 
						"FROM tblTransactionConfirm\r\n" + 
						"WHERE tblTransactionConfirm.creationDate<=Date();\r\n" + 
						"");
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		// 2) update  tblTodaysTransactions
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT superUnion.transactionId, superUnion.Address, superUnion.Signature, superUnion.status, superUnion.commission, superUnion.creationDate, superUnion.size, superUnion.type FROM superUnion INTO tblTodaysTransactions \r\n" + 
						"WHERE (((DateDiff(\"d\",[creationDate],Now()))=0));\r\n" + 
						"");
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// 3) updated tblUserReport
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT tblTodaysTransactions.Address, tblTodaysTransactions.Signature, Count(tblTodaysTransactions.transactionId) AS Amount, Avg(tblTodaysTransactions.commission) AS Average, ((SELECT Count(*) FROM tblTodaysTransactions WHERE status =  'executed')/Count(*)) AS Precent FROM tblTodaysTransactions INTO tblUserReport GROUP BY tblTodaysTransactions.Address, tblTodaysTransactions.Signature;");
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
