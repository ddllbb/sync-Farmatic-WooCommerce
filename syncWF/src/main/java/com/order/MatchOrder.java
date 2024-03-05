package com.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connections.ConnectorBBDD;
import com.connections.FileMapManager;
import com.dataConnection.Files;
import com.dataConnection.LoginClient;
import com.dataConnection.Query;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class MatchOrder {
	
    private static final Logger logger = LoggerFactory.getLogger(MatchOrder.class);

	
	public static void ordersToBBDD (JsonArray jsonArray,LoginClient login) throws SQLException, Exception {
		Map<String, IdFVsIdWOrder> idsOrdersMap = FileMapManager.loadMapIdFVsIdWOrder(Files.IDF_VS_IDW_ORDERS);
		//Se cargan todos los idF para que luego no se pisen al generar nuevos con nuevos idW*
		bbddToFile(login,idsOrdersMap);
		System.out.println(" ");
		System.out.println("--------------------------");
		System.out.println("WooCommerce --->> Farmatic");
		System.out.println("--------------------------");
		for (JsonElement jsonElement : jsonArray) {
			Order order = OrderMapper.mapJsonToOrder(jsonElement);
			String idF = asignarIdF(order,idsOrdersMap);
			Order newOrderWithIdF = order.addIdF(idF);
			OrderMapper.OrderToBbdd(login, newOrderWithIdF);
		}
		System.out.println(" ");
		System.out.println("Pedidos actualizados correctamente");
	}
	
	private static String asignarIdF (Order order,Map<String, IdFVsIdWOrder> map) throws Exception{
		String idF = FileMapManager.getIdFbyIdWOrderIds(order.getIdW(), map);
		if (idF ==null){
			//*aquí
			idF = FileMapManager.generateNewIdFOrderIds(map);
			IdFVsIdWOrder idsOrder = new IdFVsIdWOrder(idF,order.getIdW());
			FileMapManager.addOrderIdsMap(idsOrder, map);
			FileMapManager.saveOrderIds(Files.IDF_VS_IDW_ORDERS, map);
			System.out.println("idF="+idF+" "+order.getBilling().getEmail()+" idW="+order.getIdW()+"   W --- ACUALIZADO ---> F");
	        logger.info("Nuevo pedido. Detalles: idF={}, idW={}, email={}", idF, order.getIdW(), order.getBilling().getEmail());
		}
	return idF;	
	}
	private static void bbddToFile(LoginClient login,Map<String, IdFVsIdWOrder> idsOrdersMap) throws Exception {
		ConnectorBBDD connector = new ConnectorBBDD(login,Query.EXPORT_SQL_ORDERS);
		connector.setResultSet(connector.getPstmt());
		ResultSet resultSet = connector.getResultSet();
		while (resultSet.next()) {
			String idF=resultSet.getString("ID");
			if (!idsOrdersMap.containsKey(idF)) {
				IdFVsIdWOrder idsOrder = new IdFVsIdWOrder(idF,null);
				FileMapManager.addOrderIdsMap(idsOrder, idsOrdersMap);
				FileMapManager.saveOrderIds(Files.IDF_VS_IDW_ORDERS, idsOrdersMap);
			}
		}
	}
}

