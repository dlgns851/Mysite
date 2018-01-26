package com.javaex.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

private Connection con;
	
	public DbConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "webdb";
		String pwd = "webdb";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버로드성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getCon() {
		return con;
	}
}
