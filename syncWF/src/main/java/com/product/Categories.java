package com.product;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Categories {
	
	 private static HashMap<Integer, Integer> correspondencias = new HashMap<>();

	    // Bloque de inicialización estático para ejecutar el código de inicialización
	    static {
	        correspondencias.put(171, 21);
	        correspondencias.put(211, 31);
	        correspondencias.put(169, 9);
	        correspondencias.put(237, 26);
	        correspondencias.put(224, 7);
	        correspondencias.put(248, 30);
	        correspondencias.put(149, 37);
	        correspondencias.put(176, 3);
	        correspondencias.put(206, 8);
	        correspondencias.put(209, 30);
	    }
	    /*
	     * El bloque static se ejecutará cuando la clase Categories se cargue en memoria
	     * */
	    public static int conver(JsonArray jsonArray) {
	    	int catF=3;
	    	for (JsonElement jsonElement : jsonArray) {
	        	JsonObject jsonObject = (JsonObject) jsonElement;
	        	String idCategoria = jsonObject.get("id").getAsString();
	        	int idCategoriaInt = Integer.parseInt(idCategoria);
	        	if(correspondencias.containsKey(idCategoriaInt)) {
	        	//mapa.get(key) --> nos da el value correspondiente en el mapa
	        	catF=correspondencias.get(idCategoriaInt);
	        	//catF tendrá la última subcategoria registrada en el mapa correspondencias
	        	}
	        }
	    	return catF;
	    }
	    

}
