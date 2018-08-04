package com.cg.account.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.account.exception.AccountException;

public class DbConnect {
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String connUrl="oracle.jdbc.driver.OracleDriver";
	
	public static Connection getConnection() throws AccountException{
		
		try {
			Class.forName(connUrl);
			return DriverManager.getConnection(url,"system","orcl11g");
		
		} catch (ClassNotFoundException e) {
			
			throw new AccountException(e.getMessage());
		} catch (SQLException e) {
			
			throw new AccountException(e.getMessage());
		}		
	}
}
