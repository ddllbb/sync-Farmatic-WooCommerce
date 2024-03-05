package com.dataConnection;

public class FuenteCarrantona {
	//BBDD: Farmatic
	public static final String SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String JDBC= "jdbc:sqlserver://127.0.0.1:1433;databaseName=Farmatic;encrypt=true;trustServerCertificate=true";
	public static final String NAME = "Farmatic";
	public static final String USER = "sa";
	public static final String PASSWORD = "Jm1244dV!";
	//API: WooCommerce
	public static final String CONSUMER_KEY= "ck_d6445233c3510c084209e0347549999285d3eb4a"; 
	public static final String CONSUMER_SECRET= "cs_8ba2e34d5426adab00e357467140c1d2f6fd8501";
	public static final String PRODUCTS = "https://fuentecarrantona.com/wp-json/wc/v3/products";
	public static final String CLIENTS = "https://fuentecarrantona.com/wp-json/wc/v3/customers";
	public static final String ORDERS = "https://fuentecarrantona.com/wp-json/wc/v3/orders";
	
	public static final LoginClient LOGIN = new LoginClient(SERVER,JDBC,NAME,USER,PASSWORD,CONSUMER_KEY,CONSUMER_SECRET,PRODUCTS,CLIENTS,ORDERS);
	
}
