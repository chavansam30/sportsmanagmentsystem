package com.experian.finance.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
	private static Connection connection;
	private Statement stmt;
	private static DatabaseConnection databaseConnect = null;
	private static Properties properties = new Properties();
	private static boolean readOneTime = true;
	
	private DatabaseConnection() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String serverurl="jdbc:db2://IND-NOT-DB2U01:50000/ICBUDB1";
			String dbUsername="ibcom";
			String dbUserPassword="Experian123";
			connection = DriverManager.getConnection(serverurl,dbUsername,dbUserPassword);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DatabaseConnection getObject() {
		if (databaseConnect == null) {
			databaseConnect = new DatabaseConnection();
		}
		return databaseConnect;
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
