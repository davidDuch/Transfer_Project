package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Model.Advice;
import Model.CommitmentPerUser;
import Model.User;
import Utils.Commitment;
import Utils.Consts;

public class AdviseLogic {

	public static ArrayList<Advice> getAllAdvisesPerUser(User user) {
		ArrayList<Advice> results = new ArrayList<Advice>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_ADVICE_PER_USER);
				stmt.setString(1, user.getPublicAddress());
				stmt.setString(2, user.getDigitalSignature());

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int i = 1;
					results.add(new Advice(rs.getInt(i++), (rs.getDate(i++)), rs.getDouble(i++),
							rs.getDouble(i++)));
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
	 * Adds a commitment to the select user and advice
	 * 
	 * @param user
	 * @param ad
	 * @param commitment
	 * @return
	 */
	public static boolean addCommitment(User user, Advice ad, Commitment commitment) {

		String address = user.getPublicAddress(), signature = user.getDigitalSignature();
		int adId = ad.getAdviceId();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_NEWCOMMITMENT)) {
				int i = 1;
				stmt.setInt(i++, adId);
				stmt.setString(i++, address);
				stmt.setString(i++, signature);
				stmt.setString(i++, commitment.toString());

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
	 * get all the advises from the DB
	 * 
	 * @return
	 */
	public static ArrayList<Advice> getAllAdvises() {
		ArrayList<Advice> results = new ArrayList<Advice>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ADVICE);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Advice(rs.getInt(i++), (rs.getDate(i++)), rs.getDouble(i++),
							rs.getDouble(i++)));
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
	 * add a new advice to the DB
	 * 
	 * @param adviceId
	 * @param date
	 * @param adviceComission
	 * @param prefPercent
	 * @return
	 */
	public static boolean addAdvice(int adviceId, Calendar date, double adviceComission, double prefPercent) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_NEWADVICE)) {
				int i = 1;
				stmt.setInt(i++, adviceId);
				stmt.setDate(i++, new java.sql.Date(date.getTime().getTime()));
				stmt.setDouble(i++, adviceComission);
				stmt.setDouble(i++, prefPercent);

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
	 * Get commitment per advice per User
	 * 
	 * @param user
	 * @return
	 */
	public static CommitmentPerUser getAdviceCommitement(User user, Advice advice) {
		CommitmentPerUser results = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_USERSADVICECOMMIT);

				stmt.setInt(1, advice.getAdviceId());
				stmt.setString(2, user.getPublicAddress());
				stmt.setString(3, user.getDigitalSignature());
				ResultSet rs = stmt.executeQuery();

				rs.next();
				results = new CommitmentPerUser(Commitment.valueOf(rs.getString(1)));

				System.out.println(results);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;

	}

}
