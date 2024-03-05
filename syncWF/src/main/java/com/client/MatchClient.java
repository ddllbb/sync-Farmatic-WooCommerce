package com.client;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connections.FileMapManager;
import com.dataConnection.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class MatchClient {
	
    private static final Logger logger = LoggerFactory.getLogger(MatchClient.class);


	public static void clientsToBBDD (JsonArray jsonArray,PreparedStatement pstmt) throws SQLException, Exception {
		Map<String, Client> clientsMap = FileMapManager.loadMap(Files.CLIENTS);
		System.out.println(" ");
		System.out.println("--------------------------");
		System.out.println("WooCommerce --->> Farmatic");
		System.out.println("--------------------------");
		for (JsonElement jsonElement : jsonArray) {
               Client client = ClientMapper.mapJsonSetToClient(jsonElement);
               String idF = FileMapManager.getIdFbyIdW(client.getIdW(), clientsMap);
               if (idF==null){
            	   idF = FileMapManager.generateNewIdF(clientsMap);
            	   Client newClientWithIdF = client.addIdF(idF);
            	   ClientMapper.pstmtClientToBbdd(pstmt, newClientWithIdF);
            	   System.out.println("idF="+idF+" "+client.getEmail()+" idW="+client.getIdW()+"   W --- NUEVO ---> F");
            	   logger.info("Cliente nuevo: idF={}, idW={}, email={}",idF,client.getIdW(),client.getEmail());
          		   FileMapManager.addClientMap(newClientWithIdF, clientsMap);
          		   FileMapManager.save(Files.CLIENTS, clientsMap);
               }
               else {
            	   Client clientG= FileMapManager.getClientById(idF,clientsMap);
            	   Client clientW = client.addIdF(idF);
            	   if (clientW.equals(clientG)) {
            		   System.out.println("idF="+idF+" "+clientW.getEmail()+" idW="+clientW.getIdW()+"  OK");
            	   } else {
            		   ClientMapper.pstmtClientToBbdd(pstmt, clientW);
            		   FileMapManager.modifyClientMap(clientW,clientG);
            		   FileMapManager.save(Files.CLIENTS, clientsMap);
            		   System.out.println("idF="+idF+" "+clientW.getEmail()+" idW="+clientW.getIdW()+"   W --- Actualizado ---> F");
            		   logger.info("Cliente modificado: idF={}, idW={}, email={}",idF,clientW.getIdW(),clientW.getEmail());
            	   }
               }
		}
	}
}

