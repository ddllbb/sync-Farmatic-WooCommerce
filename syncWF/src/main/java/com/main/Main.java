package com.main;


import com.client.Clients;
import com.dataConnection.FuenteCarrantona;
import com.dataConnection.LoginClient;
import com.dataConnection.Miro;
import com.order.Orders;
import com.product.Products;

public class Main {
	public static void main(String[] args) throws Exception {
		
		LoginClient login = FuenteCarrantona.LOGIN;
		//LoginClient login = Miro.LOGIN;
		
		//Clients.toWooC(login);
		//Clients.toBBDD(login);
		
		//Products.toWooC2(login);
		
		//Orders.toBBDD(login);
		
}}

