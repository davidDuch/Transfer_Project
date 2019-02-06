package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static boolean addCategory(String id , String name) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_CATEGORY)) {
				int i = 1;

				stmt.setString(i++, id);
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
	
		
	public static boolean setDiscountExpandPrice(int set) {
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
	
	public static boolean setExpandPrice(int set) {
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
	
	public static boolean setDefaultWalletSize(int set) {
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
	
	public static boolean setExpandWalletSize(int set) {
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
	
	public static boolean setMaxPossibleExpansionSize(int set) {
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
