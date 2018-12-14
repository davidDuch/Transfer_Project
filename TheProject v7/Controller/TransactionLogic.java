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

}
