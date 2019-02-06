package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Advice;
import Model.Category;
import Model.Confirm;
import Model.Pay;
import Model.Product;
import Model.User;
import Model.Wallet;
import Utils.Consts;
import Utils.Status;

public class UserLogic {


	/**
	 * 
	 * @param address
	 * @param pC
	 * @param tablet
	 * @param phone
	 * @param funds
	 * @param futureValue
	 * @param publicAddress
	 * @param digitalSignature
	 * @return
	 */
	public static boolean add_wallet(Boolean pC, Boolean tablet, Boolean phone, double funds, double futureValue,
			String publicAddress, String digitalSignature) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_WALLET)) {

				stmt.setString(1, "W" + Wallet.walletsCount);
				stmt.setBoolean(2, pC);
				stmt.setBoolean(3, tablet);
				stmt.setBoolean(4, phone);
				stmt.setDouble(5, funds);
				stmt.setDouble(6, futureValue);
				stmt.setString(7, publicAddress);
				stmt.setString(8, digitalSignature);
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
	 * 
	 * add new user to the system
	 * 
	 * @param publicAddress
	 * @param digitalSignature
	 * @param userName
	 * @param password
	 * @param phoneNumber
	 * @param email
	 * @return
	 */
	public static boolean addNewUser(String publicAddress, String digitalSignature, String userName, String password,
			String phoneNumber, String email) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_USER)) {

				stmt.setString(1, publicAddress);
				stmt.setString(2, digitalSignature);
				stmt.setString(3, userName);
				stmt.setString(4, password);
				stmt.setString(5, phoneNumber);
				stmt.setString(6, email);
				stmt.executeUpdate();
				add_wallet(false, false, false, 0, 0, publicAddress, digitalSignature);

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
	 * get Transactions by status
	 * 
	 * @param user
	 * @param status
	 * @return
	 */
	public static ArrayList<Pay> getPayByStatus(User user, Status status) {
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
					results.add(new Pay(rs.getString(i++), rs.getString(i++), rs.getDouble(i++), rs.getDate(i++),
							rs.getDate(i++), status, rs.getDouble(i++), rs.getDouble(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++)));
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
	 * 
	 * @param user
	 * @return
	 */
	public static boolean deleteUser(User user) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
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
	 * 
	 * 
	 * IN PROCESSSSSSS
	 * 
	 * 
	 * 
	 * 
	 * Get all advises that are sent to user
	 * 
	 * @param user
	 * @return
	 */
	public static ArrayList<Advice> getUsersAdvice(User user) {
		ArrayList<Advice> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_USERSADVICE);

				stmt.setString(1, user.getPublicAddress());
				stmt.setString(2, user.getDigitalSignature());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Advice(rs.getInt(i++), Sys.toCalendar(rs.getDate(i++)), rs.getDouble(i++),
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
	 * 
	 * 
	 * Get users wallets
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
					results.add(new Wallet(rs.getString(i++), rs.getBoolean(i++), rs.getBoolean(i++),
							rs.getBoolean(i++), rs.getDouble(i++), rs.getDouble(i++)));
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
	 * 
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

	public static Number counts_All_Wallets() {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_COUNT_WALLETS)) {

				ResultSet rs = stmt.executeQuery();
				rs.next();
				return ((Number) rs.getObject(1)).intValue();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public static ArrayList<Product> getProducts(int from, int to) {
		ArrayList<Product> results = new ArrayList<>();
		

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_PRODUCTS_PRICE);

				stmt.setDouble(1,from);
				stmt.setDouble(2, to);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Product(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDouble(i++), new Model.Category(rs.getString(i++) , null), rs.getInt(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;

	}

	public static ArrayList<Product> getProducts(String name) {
	ArrayList<Product> results = new ArrayList<>();
		
	try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblProduct WHERE tblProduct.name  =  " +"'" +name+"'"+";");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Product(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDouble(i++), new Model.Category( rs.getString(i++) , null), rs.getInt(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static ArrayList<Product> getProducts(Category  category) {
		ArrayList<Product> results = new ArrayList<>();
		
		try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblProduct WHERE tblProduct.categoryId  =  "+category.getId() +";");
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						results.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), category, rs.getInt(7)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		

	}

	public static ArrayList<Pay> getAllUsersPay(User user){
		
	ArrayList<Pay> results = new ArrayList<>();
		
		try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_USERS_PAY);
					
					stmt.setString(1,user.getPublicAddress());
					stmt.setString(2, user.getDigitalSignature());
					
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						results.add(new Pay(rs.getString(1), rs.getString(2),rs.getDouble(3), rs.getDate(4),rs.getDate(5) ,Status.valueOf(rs.getString(6)) , rs.getDouble(7) ,rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return results;
		
		
		
	}
	
	public static ArrayList<Confirm> getAllUsersConfirm(User user){
		ArrayList<Confirm> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_USERS_CONFIRM);
				
				stmt.setString(1,user.getPublicAddress());
				stmt.setString(2, user.getDigitalSignature());
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					results.add(new Confirm(rs.getString(1), rs.getString(2),rs.getDouble(3), rs.getDate(4),rs.getDate(5) ,Status.valueOf(rs.getString(6)) , rs.getDouble(7) , rs.getDate(8), rs.getBoolean(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
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
