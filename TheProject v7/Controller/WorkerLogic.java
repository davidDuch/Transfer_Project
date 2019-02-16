package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Category;
import Model.User;
import Utils.Consts;

public class WorkerLogic {
	
	
	
	public static ArrayList<Double> getParameters() {
		ArrayList<Double> parameters = new ArrayList<>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_PARAMETERS)) {

				ResultSet rs = stmt.executeQuery();
				rs.next();
				parameters.add(rs.getDouble(1));
				parameters.add(rs.getDouble(2));
				parameters.add(rs.getDouble(3));
				parameters.add(rs.getDouble(4));
				parameters.add(rs.getDouble(5));
				parameters.add(rs.getDouble(6));
				
				return parameters;


			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
		
	}
	public static ArrayList<Category> getCategories() {
		ArrayList<Category> results = new ArrayList<Category>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CATEGORIES);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int i = 1;
					results.add(new Category(rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static boolean addCategory(int id , String name) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_CATEGORY)) {
				int i = 1;

				stmt.setInt(i++, id);
				stmt.setString(i++, name);
			

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
	
		
	public static boolean setDiscountExpandPrice(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_discountExpandPrice);
				
				stmt.setDouble(1, set);
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
	public static boolean setDate(Date date) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				int i=1;
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_DATE);
				
				stmt.setDate(i++, new java.sql.Date(date.getTime()));
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

	public static boolean setExpandPrice(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_sizeExpendPrice);
				
				stmt.setDouble(1, set);
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
	
	public static boolean setDefaultWalletSize(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_defaultWalletSize);
				
				stmt.setDouble(1, set);
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
	
	public static boolean setExpandWalletSize(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_expandWalletSize);
				
				stmt.setDouble(1, set);
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
	
	
	public static boolean  setExpandDiscountSize(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_expandDiscountSize);
				
				stmt.setDouble(1, set);
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
	
	public static boolean setMaxPossibleExpansionSize(double set) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement(Consts.WORKER_UPDATE_maxPossibleExpansionSize);
				
				stmt.setDouble(1, set);
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

	
}
