package com.product;

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
import com.main.C;

public class Products {
	
    private static final Logger logger = LoggerFactory.getLogger(Products.class);

	
	public static void toBBDD(LoginClient login) throws Exception {
		int pages;
		int i=1;
		// BBDD connection
		ConnectorBBDD connector = new ConnectorBBDD (login,Query.EXPORT_SQL_PRODUCTS);
		PreparedStatement pstmt = connector.getPstmt();
		do {
			//API connection
			HttpApi http = new HttpApi(login);
			HttpResponse<String> response=http.get(i, login.products);
			
			JsonElement jsonElement = JsonParser.parseString(response.body());
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			
            MatchProduct.productsToBBDD(jsonArray, pstmt);
 
            pages = Fields.pagination(response);
            i=i+1;
		} while (i <= pages);
		System.out.println("Productos actualizados. Páginas: "+pages);
	}
	
	public static void toWooC2(LoginClient login) throws Exception {
		PreparedStatement pstmtExport = null;
		ResultSet resultSet = null;
		try{
	     	System.out.println("--------------------------");
	     	System.out.println("Farmatic --->> WooCommerce");
	     	System.out.println("--------------------------");
	     	
	     	Map<String,String> idEfpMap = FileMapManager.loadMapProductsEdf(Files.ID_EFP);
	     	
	     	ConnectorBBDD connector = new ConnectorBBDD (login,Query.EXPORT_SQL_PRODUCTS);
	     	connector.setResultSet(connector.getPstmt());
	     	resultSet = connector.getResultSet();
	     	
	     	while (resultSet.next()) {
	     		//Por cada registro de Farmatic
	     		//Si Efp = 1
	     		if(resultSet.getBoolean("Efp")) {
	     			Product product = ProductMapper.articuResultSetToProduct(resultSet);
	     			String id = resultSet.getString("IdArticu");
	     			if(idEfpMap.containsKey(id)){
	     				String idW = idEfpMap.get(id);
	     				String urlId = login.products +"/"+idW;
	     				HttpApi http = new HttpApi(login);
	     				http.put(product.jsonProduct(), urlId);
	     				System.out.println("idF="+product.getIdF()+" "+product.getName()+" idW="+idW+"  F --- Actualizado ---> W");
	     				logger.info("Producto modificado: idF={}, idW={}, nombre={}",product.getIdF(),idW,product.getName());
	     			}else {
	     				HttpApi http = new HttpApi(login);
	     				String idW=http.postAndGetId(product.jsonProduct(), login.products);
	     				idEfpMap.put(id, idW);
	     				FileMapManager.saveEfp(Files.ID_EFP, idEfpMap);
	     				System.out.println("idF="+product.getIdF()+" "+product.getName()+" idW="+idW+"  F --- NUEVO ---> W");
	         			logger.info("Producto nuevo: idF={}, idW={}, nombre={}",product.getIdF(),idW,product.getName());
	     			}
	     		}
	     	}
		}finally {
		    if (resultSet != null) {
		        resultSet.close();
		    }
		    if (pstmtExport != null) {
		        pstmtExport.close();
		    }
		  }
	}
	
	public static void toWooC(LoginClient login) throws Exception {
	PreparedStatement pstmtExport = null;
	ResultSet resultSet = null;
	try{
     	System.out.println("--------------------------");
     	System.out.println("Farmatic --->> WooCommerce");
     	System.out.println("--------------------------");
     	Map<String,Product> productsMap = FileMapManager.loadMapProduct(Files.PRODUCTS);
     	ConnectorBBDD connector = new ConnectorBBDD (login,Query.EXPORT_SQL_PRODUCTS);
     	resultSet = connector.getResultSet();
     	while (resultSet.next()) {
     		//Por cada registro de Farmatic
     		Product product = ProductMapper.mapResultSetToProduct(resultSet);
     		Product productG= FileMapManager.getProductById(product.getIdF(),productsMap);

     		//stock en WooCommerce solo admite 3 valores: "instock", "outofstock", "onbackorder"
     		//stockStatus = Product.mapStockStatus(stockStatus);
     		
     		if (productG != null) {
     			String idW = productG.getIdW();
     			Product productF = product.addIdW(idW);
     			if (productF.equals(productG)) {
     				System.out.println("idF="+product.getIdF()+" "+product.getName()+" idW="+idW+"  OK");
     			} else {
     				String urlId = C.productsUrlApi +"/"+idW;
     				HttpApi http = new HttpApi(login);
     				http.put(productF.jsonProduct(), urlId);
     				FileMapManager.modifyProductMap(productF,productG);
     				FileMapManager.saveProduct(Files.PRODUCTS, productsMap);
     				System.out.println("idF="+product.getIdF()+" "+product.getName()+" idW="+idW+"  F --- Actualizado ---> W");
     				logger.info("Producto modificado: idF={}, idW={}, nombre={}",product.getIdF(),idW,product.getName());
     			}
     		}else {
     			HttpApi http = new HttpApi(login);
     			String idW = http.postAndGetId(product.jsonProduct(), login.products);
     			Product newProductWithIdW = product.addIdW(idW);
     			FileMapManager.addProductMap(newProductWithIdW, productsMap);
     			FileMapManager.saveProduct(Files.PRODUCTS, productsMap);
     			System.out.println("idF="+product.getIdF()+" "+product.getName()+" idW="+idW+"  F --- NUEVO ---> W");
     			logger.info("Producto nuevo: idF={}, idW={}, nombre={}",product.getIdF(),idW,product.getName());

     		}
     	}
	}finally {
	    if (resultSet != null) {
	        resultSet.close();
	    }
	    if (pstmtExport != null) {
	        pstmtExport.close();
	    }}	
	}
	
}
	