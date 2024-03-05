package com.main;

import com.dataConnection.Key;
import com.dataConnection.Query;
import com.dataConnection.Url;

public class C {
		//BBDD Connection
		public static String urlServer = Url.SQL_SERVER;
		public static String urlJDBC = Url.JDBC_SQL_CASA_CERTIF;
		public static String userBBDD = Key.USER_SQL;
		public static String passwBBDD = Key.PASSWORD_SQL_CASA;
		
		//API Connection
		public static String consumerKey = Key.CONSUMER_KEY_FUENTEC;
		public static String consumerSecret = Key.CONSUMER_SECRET_FUENTEC;
		
		//Clientes
		public static String clientsUrlApi = Url.CLIENTS;
		public static String queryInsertClients = Query.INSERT_SQL_CLIENTS;
		public static String queryExportClients = Query.EXPORT_SQL_CLIENTS;
		
		//Productos
		public static String productsUrlApi = Url.PRODUCTS;
		public static String queryInsertProducts = Query.INSERT_SQL_PRODUCTS;
		public static String queryExportProducts = Query.EXPORT_SQL_PRODUCTS;
		
		//Orders
		public static String ordersUrlApi = Url.ORDERS;
		public static String queryInsertOrders = Query.INSERT_SQL_ORDERS;
		public static String queryExportOrders = Query.EXPORT_SQL_ORDERS;

}
