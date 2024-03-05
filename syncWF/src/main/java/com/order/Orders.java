package com.order;

import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.connections.ConnectorBBDD;
import com.connections.Fields;
import com.connections.FileMapManager;
import com.connections.HttpApi;
import com.dataConnection.Files;
import com.dataConnection.LoginClient;
import com.dataConnection.Query;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Orders {
	
	public static void toBBDD(LoginClient login) throws Exception {
		int pages;
		int i=1;
		do {
			//API connection
			HttpApi http = new HttpApi(login);
			HttpResponse<String> response=http.get(i, login.orders);
			JsonElement jsonElement = JsonParser.parseString(response.body());
		    JsonArray jsonArray = jsonElement.getAsJsonArray();
			//Insert Orders
            MatchOrder.ordersToBBDD(jsonArray, login);
 
            pages = Fields.pagination(response);
            i=i+1;
		} while (i <= pages);
	}
	

	/*public static void toWooC() throws Exception {
     	System.out.println("--------------------------");
     	System.out.println("Farmatic --->> WooCommerce");
     	System.out.println("--------------------------");
		Map<String, Order> ordersMap = FileMapManager.loadMapOrder(Files.ORDERS);
      	ResultSet resultSet = bbddConnections.bbddQuery(C.queryExportOrders);
     	while (resultSet.next()) {
     		Order order = OrderMapper.mapResultSetToOrder(resultSet);
     		Order orderG= FileMapManager.getOrderById(order.getIdF(),ordersMap);
     		if (orderG != null) {
     			String idW = orderG.getIdW();
     			Order orderF = order.addIdW(idW);
     			if (orderF.equals(orderG)) {
     				System.out.println("idF="+order.getIdF()+" "+order.getBilling().getEmail()+" idW="+idW+"  OK");
     			} else {
     				String urlId = C.ordersUrlApi +"/"+idW;
     				HttpApi.put(order.jsonOrder(), urlId, C.consumerKey, C.consumerSecret);
     				FileMapManager.modifyOrderMap(orderF,orderG);
     				FileMapManager.saveOrder(Files.ORDERS, ordersMap);
     				System.out.println("idF="+orderF.getIdF()+" "+orderF.getBilling().getEmail()+" idW="+idW+"  F --- Actualizado ---> W");
     			}
     		}else {
     			System.out.println(order.jsonOrder());
     			String idW = HttpApi.postAndGetId(order.jsonOrder(), C.ordersUrlApi, C.consumerKey, C.consumerSecret);
     			Order newOrderWithIdW = order.addIdW(idW); 
     			FileMapManager.addOrderMap(newOrderWithIdW, ordersMap);
     			FileMapManager.saveOrder(Files.ORDERS, ordersMap);
     			System.out.println("idF="+order.getIdF()+" "+order.getBilling().getEmail()+" idW="+idW+"  F --- NUEVO ---> W");
     		}
     	}
	}	*/
 
}
	