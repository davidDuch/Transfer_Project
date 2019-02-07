package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.Consts;

public class Category {

	private String Id;
	private String Name;

	
	
	
	
	public Category(String id, String name) {
		super();
		
		if(id != null && name != null) {
			Id = id;
			Name = name;
		}
		else if(id != null && name == null ) {
			
			this.Id = id;
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
					PreparedStatement stmt = conn.prepareStatement("SELECT tblCategory.categoryName FROM tblCategory WHERE tblCategory.categoryId = "+id);

					ResultSet rs = stmt.executeQuery();
					rs.next();
					Name =  rs.getObject(1).toString();

					 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(id == null && name != null ) {
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
					PreparedStatement stmt = conn.prepareStatement("SELECT tblCategory.categoryName FROM tblCategory WHERE tblCategory.categoryName = "+name);

					ResultSet rs = stmt.executeQuery();
					rs.next();
					id =  rs.getObject(1).toString();

					 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
		}
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Category other = (Category) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  Name;
	}

}