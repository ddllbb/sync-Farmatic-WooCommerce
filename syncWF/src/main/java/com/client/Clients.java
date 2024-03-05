package com.client;

import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class Clients {
	
    private static final Logger logger = LoggerFactory.getLogger(Clients.class);

	
	public static void toBBDD(LoginClient login) throws Exception {
		int pages;
		int i=1;
		// BBDD connection
		ConnectorBBDD connector = new ConnectorBBDD(login,Query.INSERT_SQL_CLIENTS);
		PreparedStatement pstmt = connector.getPstmt();
		
		do {
			//API connection
			HttpApi http = new HttpApi(login);
			HttpResponse<String> response=http.get(i, login.clients);
			
			JsonElement jsonElement = JsonParser.parseString(response.body());
		    JsonArray jsonArray = jsonElement.getAsJsonArray();
			
            MatchClient.clientsToBBDD(jsonArray, pstmt);
 
            pages = Fields.pagination(response);
            i=i+1;
		} while (i <= pages);
	}
	

	public static void toWooC(LoginClient login) throws Exception {
     	System.out.println("--------------------------");
     	System.out.println("Farmatic --->> WooCommerce");
     	System.out.println("--------------------------");
      	Map<String,Client> clientsMap = FileMapManager.loadMap(Files.CLIENTS);
      	
      	ConnectorBBDD connector = new ConnectorBBDD(login,Query.EXPORT_SQL_CLIENTS);
      	connector.setResultSet(connector.getPstmt());
      	ResultSet resultSet = connector.getResultSet();
		while (resultSet.next()) {
     		Client client = ClientMapper.mapResultSetToClient(resultSet);
     		Client clientG= FileMapManager.getClientById(client.getId(),clientsMap);
     		if (clientG != null) {
     			String idW = clientG.getIdW();
     			Client clientF = client.addIdW(idW);
     			if (clientF.equals(clientG)) {
     				System.out.println("idF="+clientF.getId()+" "+clientF.getEmail()+" idW="+clientF.getIdW()+"  OK");
     			} else {
     				//PUT
     				HttpApi http = new HttpApi(login);
     				String urlId = login.clients +"/"+idW;
     				http.put(clientF.jsonClient(), urlId);
     				//map
     				FileMapManager.modifyClientMap(clientF,clientG);
     				FileMapManager.save(Files.CLIENTS, clientsMap);
     				System.out.println("idF="+clientF.getId()+" "+clientF.getEmail()+" idW="+clientF.getIdW()+"  F --- Actualizado ---> W");
     				logger.info("Cliente modificado: idF={}, idW={}, email={}",clientF.getId(),clientF.getIdW(),clientF.getEmail());
     			}
     		}else {
     			//POST
     			HttpApi http = new HttpApi(login);
     			String idW = http.postAndGetId(client.jsonClient(), login.clients);
     			//map
     			if(idW==null) idW=http.getIdByEmail(client.getEmail(),login.clients);
     			Client newClientWithIdW = client.addIdW(idW); 
     			FileMapManager.addClientMap(newClientWithIdW, clientsMap);
     			FileMapManager.save(Files.CLIENTS, clientsMap);
     			System.out.println("idF="+client.getId()+" "+client.getEmail()+" idW="+idW+"  F --- NUEVO ---> W");
 				logger.info("Cliente nuevo: idF={}, idW={}, email={}",client.getId(),idW,client.getEmail());
     		}
     	}
	}	
}
	