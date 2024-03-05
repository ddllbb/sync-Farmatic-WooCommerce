package com.dataConnection;

public class LoginClient {
	//BBDD: Farmatic
	public String server; 
	public String jdbc; 
	public String name;
	public String user;
	public String password;
	//API: WooCommerce
	public String consumerKey; 
	public String consumerSecret;
	public String products;
	public String clients;
	public String orders;

	public LoginClient() {
	    super();
	}
	
	
	
	public LoginClient(String server, String jdbc, String name, String user, String password, String consumerKey,
			String consumerSecret, String products, String clients, String orders) {
		super();
		this.server = server;
		this.jdbc = jdbc;
		this.name = name;
		this.user = user;
		this.password = password;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.products = products;
		this.clients = clients;
		this.orders = orders;
	}

	public String getServer() {
		return server;
	}

	public String getJdbc() {
		return jdbc;
	}

	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getProducts() {
		return products;
	}

	public String getClients() {
		return clients;
	}

	public String getOrders() {
		return orders;
	}

	



	
	
	
	

}
