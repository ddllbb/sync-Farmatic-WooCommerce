package com.connections;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.dataConnection.LoginClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.C;

public class HttpApi {
	
	private String consumerKey; 
	private String consumerSecret;
	

	public HttpApi(LoginClient login) {
		this.consumerKey = login.consumerKey;
		this.consumerSecret = login.consumerSecret;
	}
	
	public HttpResponse<String> get(int i,String UrlApi) throws Exception{
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(UrlApi + "?per_page=100&page=" + i))
	            .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes()))
	            .GET()
	            .build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
       return response; 
	}
	   
	public String getIdByEmail(String email, String apiUrl) throws Exception {
		    HttpClient httpClient = HttpClient.newHttpClient();
		    HttpRequest request = HttpRequest.newBuilder()
		            .uri(URI.create(apiUrl + "?email=" + email))
		            .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes()))
		            .GET()
		            .build();
		    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		    // Parsear la respuesta JSON
		    JsonElement jsonElement = JsonParser.parseString(response.body());

		    // Verificar si es un array y si tiene al menos un elemento
		    if (jsonElement.isJsonArray()) {
		        JsonArray jsonArray = jsonElement.getAsJsonArray();

		        // Verificar si la lista tiene al menos un elemento
		        if (jsonArray.size() > 0) {
		            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

		            // Verificar si el objeto JSON tiene la clave "id"
		            if (jsonObject.has("id")) {
		                return jsonObject.get("id").getAsString();
		            } else {
		                // Manejar el caso donde no hay una clave "id"
		                System.out.println("El objeto JSON no tiene una clave 'id'.");
		                return null;
		            }
		        } else {
		            // Manejar el caso donde la lista está vacía
		            System.out.println("La lista JSON está vacía.");
		            return null;
		        }
		    } else {
		        // Manejar el caso donde no es un array JSON
		        System.out.println("La respuesta no es un array JSON.");
		        return null;
		    }
		}

	   public JsonObject getById(String apiUrl, String id) throws Exception {
	        // Construir la URL con el ID
	        String urlWithId = apiUrl + "/" + id;

	        HttpClient httpClient = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(urlWithId))
	                .header("Accept", "application/json")
	                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes()))
	                .GET()
	                .build();

	        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	        String responseBody = response.body();

	        // Parsear el JSON de la respuesta
	        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

	        return jsonObject;
	    }
	   
	   public static void getKeysAndValuesJsonObject (JsonObject jsonObject) {
		   for (java.util.Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
	            String key = entry.getKey();
	            JsonElement value = entry.getValue();

	            // Imprimir la clave y el valor
	            System.out.println(key + ": " + value);
	        }
	   }
	
	   public HttpResponse<String> put(String jsonBody, String apiUrl) throws Exception {
           HttpClient httpClient = HttpClient.newHttpClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(apiUrl))
                   .header("Content-Type", "application/json")
                   .header("Accept", "application/json")
                   .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes()))
                   .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                   .build();
           HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	   }
	   
	   public HttpResponse<String> post(String jsonBody, String apiUrl) throws Exception {
           HttpClient httpClient = HttpClient.newHttpClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(apiUrl))
                   .header("Content-Type", "application/json")
                   .header("Accept", "application/json")
                   .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes()))
                   .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                   .build();
           HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
           return response;
	   }

	   public String postAndGetId(String jsonBody, String apiUrl) throws Exception {
	        HttpClient httpClient = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(apiUrl))
	                .header("Content-Type", "application/json")
	                .header("Accept", "application/json")
	                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes(StandardCharsets.UTF_8)))
	                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
	                .build();
	        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	        // Parsear la respuesta JSON
	        JsonElement jsonElement = JsonParser.parseString(response.body());
	        // Verificar si la respuesta contiene el ID del producto
	        if (jsonElement.isJsonObject()) {
	            JsonObject jsonObject = jsonElement.getAsJsonObject();
	            if (jsonObject.has("id")) {
	                return jsonObject.get("id").getAsString();
	            }
	        }

	        // Manejar el caso donde no se puede obtener el ID
	        System.out.println("No se pudo obtener el ID del producto.");
	        return null;
	    }

}
