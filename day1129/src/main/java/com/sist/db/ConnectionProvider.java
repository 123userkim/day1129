package com.sist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "c##olympus";
			String pwd = "olympus";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
