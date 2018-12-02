package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.Confirm;
import Model.User;
import Model.Wallet;
import Utils.Consts;
import Utils.Status;

public class UserLogic {

	/**
	 * 
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
