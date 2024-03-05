package com.dataConnection;

public class Miro {
	// BBDD: Farmatic
	public static final String SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String JDBC= "jdbc:sqlserver://...";
	public static final String NAME = "NAME";
	public static final String USER = "USER";
	public static final String PASSWORD= "PASSWORD!";
	// API: WooCommerce
	public static final String CONSUMER_KEY= "CONSUMER_KEY"; 
	public static final String CONSUMER_SECRET= "CONSUMER_SECRET";
	public static final String PRODUCTS = "https://dominio.com/wp-json/wc/v3/products";
	public static final String CLIENTS = "https://dominio.com/wp-json/wc/v3/customers";
	public static final String ORDERS = "https://dominio.com/wp-json/wc/v3/orders";
	
	public static final LoginClient LOGIN = new LoginClient(SERVER,JDBC,NAME,USER,PASSWORD,CONSUMER_KEY,CONSUMER_SECRET,PRODUCTS,CLIENTS,ORDERS);

}
