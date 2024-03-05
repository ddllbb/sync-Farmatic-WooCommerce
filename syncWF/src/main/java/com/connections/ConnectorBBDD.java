package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dataConnection.LoginClient;
import com.main.C;

public class ConnectorBBDD {
	
		String server;
		String jdbc;
		String user;
		String password;
		/////////////////////////
		String query;
		/////////////////////////		
		Connection connection;
		PreparedStatement pstmt;
		ResultSet resultSet;
		
		public ConnectorBBDD(LoginClient login, String query) throws ClassNotFoundException, SQLException {
			Class.forName(login.server);
			this.connection = DriverManager.getConnection(login.jdbc,login.user,login.password);
			this.pstmt = connection.prepareStatement(query);
			//this.resultSet = pstmt.executeQuery();
		}
		
		public void setResultSet(PreparedStatement pstmt) throws SQLException {
			this.resultSet = pstmt.executeQuery();
		}



		public String getServer() {
			return server;
		}

		public String getJdbc() {
			return jdbc;
		}

		public String getUser() {
			return user;
		}

		public String getPassword() {
			return password;
		}

		public String getQuery() {
			return query;
		}

		public Connection getConnection() {
			return connection;
		}

		public PreparedStatement getPstmt() {
			return pstmt;
		}

		public ResultSet getResultSet() {
			return resultSet;
		}
					
		
}

