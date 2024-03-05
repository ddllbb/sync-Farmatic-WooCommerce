package com.dataConnection;

public class Miro {
	// BBDD: Farmatic
	public static final String SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String JDBC= "jdbc:sqlserver://127.0.0.1:1433;databaseName=Farmatic;encrypt=true;trustServerCertificate=true";
	public static final String NAME = "Farmatic";
	public static final String USER = "sa";
	public static final String PASSWORD= "Jm1244dV!";
	// API: WooCommerce
	public static final String CONSUMER_KEY= "ck_531ac9223f8c4a5db2a534fba564d87aa519f5fe"; 
	public static final String CONSUMER_SECRET= "cs_7a8aa7f4cf5c358bf06cb74b3d3429a44c61a57f";
	public static final String PRODUCTS = "https://farmaciamiro57.com/wp-json/wc/v3/products";
	public static final String CLIENTS = "https://farmaciamiro57.com/wp-json/wc/v3/customers";
	public static final String ORDERS = "https://farmaciamiro57.com/wp-json/wc/v3/orders";
	
	public static final LoginClient LOGIN = new LoginClient(SERVER,JDBC,NAME,USER,PASSWORD,CONSUMER_KEY,CONSUMER_SECRET,PRODUCTS,CLIENTS,ORDERS);

}
