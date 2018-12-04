package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.Advice;
import Model.Commitment;
import Model.Confirm;
import Model.Pay;
import Model.User;
import Model.Wallet;
import Utils.Consts;
import Utils.Status;

public class UserLogic {

	/**
	 * 
	 * IN PROCESSSSSSS
	 * 
	 * 
	 * 
	 * get Transactions by status
	 * @param user
	 * @param status
	 * @return
	 */
	public static ArrayList<Pay> getPayByStatus(User user , Status status) {
			ArrayList<Pay> results = new ArrayList<>();
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_USERPAYBYSTATUES)) {
				
				stmt.setString(1, status.toString());
				stmt.setString(2, user.getPublicAddress());
				stmt.setString(3, user.getDigitalSignature());
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					int i = 1;
					results.add(new Pay(rs.getString(i++), rs.getString(i++), rs.getDouble(i++), rs.getDate(i++), rs.getDate(i++),
							status, rs.getDouble(i++), rs.getDouble(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));
				}
				
				return results;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 
	 * IN PROCESSSSSSS
	 * 
	 * 
	 * 
	 * deletes user from the DB 
	 * @param user
	 * @return
	 */
	public static boolean deleteUser(User user)  {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
					CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_DELETEUSER); 
				
				stmt.setString(1, "Y0005I");
				stmt.setString(2, "P1C");
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
	 * Get all advises that are sent to  user 
	 * @param user
	 * @return
	 */
	public static ArrayList<Advice> getUsersAdvice(User user) {
		ArrayList<Advice> results = new ArrayList<>();
	
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn
						.prepareStatement(Consts.SQL_GET_USERSADVICE);

				stmt.setString(1, user.getPublicAddress());
				stmt.setString(2, user.getDigitalSignature());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Advice(rs.getInt(i++), Sys.toCalendar(rs.getDate(i++)), rs.getDouble(i++), rs.getDouble(i++)));
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
	 * Get users wallets
	 * @param user
	 * @return users wallets
	 */
 	public static ArrayList<Wallet> getUserWallets(User user) {

		ArrayList<Wallet> results = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * from tblWallet WHERE ownerAddress = ? AND ownerSignature = ?;");

				stmt.setString(1, user.getPublicAddress());
				stmt.setString(2, user.getDigitalSignature());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Wallet(rs.getString(i++), rs.getDouble(i++), rs.getDouble(i++), rs.getBoolean(i++),
							rs.getBoolean(i++), rs.getBoolean(i++)));
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
	 * A method that gets all users from DB 
	 * @return
	 */
	public static ArrayList<User> getUsers() {
		ArrayList<User> results = new ArrayList<User>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_USERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new User(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
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

}
