package com.dataConnection;

public class Url {
	
	//WooCommerce
		public static final String PRODUCTS = "https://fuentecarrantona.com/wp-json/wc/v3/products";
		public static final String CLIENTS = "https://fuentecarrantona.com/wp-json/wc/v3/customers";
		public static final String ORDERS = "https://fuentecarrantona.com/wp-json/wc/v3/orders";
		
	//MYSQL
		public static final String MYSQL_SERVER = "com.mysql.cj.jdbc.Driver";
		public static final String JDBC_MYSQL = "jdbc:mysql://h24sr_server01:3306/Farmatic";
		
	//SQL SERVER
		public static final String SQL_SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		public static final String JDBC_SQL = "jdbc:sqlserver://h24sr-server01:3306;databaseName=Farmatic";
		public static final String JDBC_SQL_TLS1 = "jdbc:sqlserver://h24sr-server01:1433;databaseName=Farmatic;encrypt=false;trustServerCertificate=true;sslProtocol=TLSv1.1";
		public static final String JDBC_TLS1 = "jdbc:sqlserver://h24sr-server01:1433;databaseName=Farmatic;encrypt=true;trustServerCertificate=false;trustStore=example.truststore;trustStorePassword=truststorepassword;sslProtocol=TLSv1";
		public static final String JDBC_CERTIF = "jdbc:sqlserver://h24sr-server01:1433;databaseName=Farmatic;encrypt=true;trustServerCertificate=true";
		public static final String JDBC_SQL_CASA = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Farmatic";
		public static final String JDBC_SQL_CASA_CERTIF = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Farmatic;encrypt=true;trustServerCertificate=true";

}
