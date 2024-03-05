package com.product;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Tax {
	
	 private static HashMap<String, String> correspondencias = new HashMap<>();

	    // Bloque de inicialización estático para ejecutar el código de inicialización
	    static {
	        correspondencias.put("", "04");
	        correspondencias.put("tasa-reducida-10", "03");
	        correspondencias.put("tasa-reducida-4", "02");
	    }
	    /*
	     * El bloque static se ejecutará cuando la clase Categories se cargue en memoria
	     * */
	    public static String conver(String tax) {
	    	
	    	String idGrupoIva  = "04";
	        	if(correspondencias.containsKey(tax)) {
	        	//mapa.get(key) --> nos da el value correspondiente en el mapa
	        		idGrupoIva=correspondencias.get(tax);
	        	}
	        
	    	return idGrupoIva;
	    }
	    

}
