package com.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ClientMapper {

    public static Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getString("IDCLIENTE"));
        client.setFullName(resultSet.getString("PER_NOMBRE"));
        client.setAddress(resultSet.getString("PER_DIRECCION"));
        client.setCity(resultSet.getString("PER_POBLACION"));
        client.setPostcode(resultSet.getString("PER_CODPOSTAL"));
        client.setPhone(resultSet.getString("PER_TELEFONO"));
        client.setEmail(resultSet.getString("OBSERVACIONES"));
        return client;
    }

	public static Client mapJsonSetToClient (JsonElement jsonElement) {
		JsonObject jsonObject = (JsonObject) jsonElement;
        Client client = new Client();
        client.setIdW(jsonObject.get("id").getAsString());
        client.setFullName(jsonObject.get("first_name").getAsString());
        client.setEmail(jsonObject.get("email").getAsString());
        //Anidados
        JsonObject billingObject = jsonObject.getAsJsonObject("billing");
        	client.setAddress(billingObject.get("address_1").getAsString());
        	client.setCity(billingObject.get("city").getAsString());
        	client.setPostcode(billingObject.get("postcode").getAsString());
        	client.setPhone(billingObject.get("phone").getAsString());
		return client;
	}
	
	public static void pstmtClientToBbdd (PreparedStatement pstmt, Client client) throws SQLException {
		   pstmt.setString(1, client.getId());
		   pstmt.setString(2, client.getFullName());
		   pstmt.setString(3, client.getEmail());
		   pstmt.setString(4, client.getAddress());
		   pstmt.setString(5, client.getCity());
		   pstmt.setString(6, client.getPostcode());
		   pstmt.setString(7, client.getPhone());
		   pstmt.executeUpdate();
	}
}