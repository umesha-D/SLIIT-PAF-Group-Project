package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	
	public static void main(String args[]) {
		
		dbConnection conn = new dbConnection();
		System.out.println(conn.connect());
		
	}
	
	
	public Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc", "root", "");
			
		} catch ( Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
