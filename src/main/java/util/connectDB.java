package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB {

	public static Connection getCon() {
		try {
			
			String jdbcURL = "jdbc:mysql://localhost:3306/jspDB?"+
							"useSSL=false&serverTimezone=UTC";
				
//			String jdbcURL = "jdbc:mysql://localhost:3306/jspDB?autoReconnect=true";
			
			String dbUser = "root";
			String dbPass = "938271456aq";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			return DriverManager.getConnection(jdbcURL, dbUser, dbPass);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
