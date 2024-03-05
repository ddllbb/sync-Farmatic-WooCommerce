package com.product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connections.FileMapManager;
import com.dataConnection.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class MatchProduct {
	
	private static final Logger logger = LoggerFactory.getLogger(MatchProduct.class);
		
	public static void productsToBBDD (JsonArray jsonArray,PreparedStatement pstmt) throws SQLException, Exception {
		Map<String, Product> productsMap = FileMapManager.loadMapProduct(Files.PRODUCTS);
		System.out.println(" ");
		System.out.println("--------------------------");
		System.out.println("WooCommerce --->> Farmatic");
		System.out.println("--------------------------");
		for (JsonElement jsonElement : jsonArray) {
			Product product = ProductMapper.jsonToProduct(jsonElement);
			//Stock constraint
			//stockStatus = Product.mapStockStatusReverse(stockStatus);

			String idF = FileMapManager.getIdFbyIdWProduct(product.getIdW(), productsMap);
			if (idF==null){
				idF = FileMapManager.generateNewIdFProduct(productsMap);
				Product newProductWithIdF = product.addIdF(idF);
				ProductMapper.pstmtProductToBbdd(pstmt, newProductWithIdF);
				FileMapManager.addProductMap(newProductWithIdF, productsMap);
				FileMapManager.saveProduct(Files.PRODUCTS, productsMap);
				System.out.println("idF="+idF+" "+product.getName()+" idW="+product.getIdW()+"   W --- NUEVO ---> F");
				logger.info("Producto nuevo: idF={}, idW={}, nombre={}",idF,product.getIdW(),product.getName());
			}
			else {
				Product productG = FileMapManager.getProductById(idF, productsMap);
				Product productW = product.addIdF(idF);
				if (productW.equals(productG)) {
					System.out.println("idF="+idF+" "+productW.getName()+" idW="+product.getIdW()+"  OK");
				} else {
					ProductMapper.pstmtProductToBbdd(pstmt, productW);
					FileMapManager.modifyProductMap(productW, productG);
					FileMapManager.saveProduct(Files.PRODUCTS, productsMap);
					System.out.println("idF="+idF+" "+productW.getName()+" idW="+productW.getIdW()+"   W --- Actualizado ---> F");
					logger.info("Producto modificado: idF={}, idW={}, nombre={}",idF,productW.getIdW(),productW.getName());
				}
			}

		}
	}

}

