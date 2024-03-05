package com.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connections.Fields;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ProductMapper {

	public static Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setIdF(resultSet.getString("ID"));
        product.setName(resultSet.getString("NOMBRE"));
        product.setRegularPrice(resultSet.getDouble("PRECIO"));
        product.setSalePrice(resultSet.getDouble("PRECIO_OFERTA"));
        product.setStockActual(resultSet.getInt("STOCK"));
        product.setShortDescription(resultSet.getString("DESCRIPCION_CORTA"));
        product.setDescription(resultSet.getString("DESCRIPCION"));
        return product;
    }
	
	// Tabla Articu --> Real Farmatic
	public static Product articuResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setIdF(resultSet.getString("IdArticu"));
        product.setName(resultSet.getString("Descripcion"));
        product.setRegularPrice(resultSet.getDouble("Pvp"));
        product.setSalePrice(resultSet.getDouble("PvpAux"));
        product.setStockActual(resultSet.getInt("StockActual"));
        return product;
    }

   
    public static Product jsonToProduct(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        Product product = new Product();
        product.setIdW(getStringOrNull(jsonObject, "id"));
        product.setName(getStringOrNull(jsonObject, "name"));

        product.setRegularPrice(getDoubleOrNull(jsonObject, "regular_price"));
        product.setSalePrice(getDoubleOrNull(jsonObject, "price"));

        if (jsonObject.has("manage_stock") && jsonObject.get("manage_stock").getAsBoolean()) {
            product.setStockActual(getIntegerOrNull(jsonObject, "stock_quantity"));
        }
        //Limpiado etiquetas html
        //product.setShortDescription(Fields.limpiaHTML(jsonObject.get("short_description").getAsString()));
        //product.setDescription(Fields.limpiaHTML(jsonObject.get("description").getAsString()));
        
        //Extraer la categoria
        //Antes tenemos un key:"json" ahora tenemos key:"jsonArray"
        //tratar el jsonArray
        JsonArray jsonArrayCategories = jsonObject.get("categories").getAsJsonArray();
        int catF= Categories.conver(jsonArrayCategories);
        product.setFamily(catF);
        
        //Tax
        String taxF;
        if("taxable".equals(jsonObject.get("tax_status").getAsString())) {
        	taxF = Tax.conver(jsonObject.get("tax_class").getAsString());
        }else {
        	taxF = "01";
        }
        
        product.setTax(taxF);
        
        return product;
    }
 // Métodos de utilidad para obtener valores o nulos
    private static String getStringOrNull(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        return (element != null && !element.isJsonNull()) ? element.getAsString() : null;
    }

    private static Double getDoubleOrNull(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);

        if (element != null && !element.isJsonNull() && element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();

            if (primitive.isNumber()) {
                return primitive.getAsDouble();
            } else if (primitive.isString() && !primitive.getAsString().isEmpty()) {
                // Si es una cadena no vacía, intentar convertir a Double
                try {
                    return Double.parseDouble(primitive.getAsString());
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la cadena no es un número válido
                    System.err.println("Error al convertir la cadena a double: " + e.getMessage());
                }
            }
        }

        return 0.0; // Valor predeterminado si no se puede obtener un Double
    }




    private static Integer getIntegerOrNull(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        return (element != null && !element.isJsonNull()) ? element.getAsInt() : null;
    }

    public static void pstmtProductToBbdd(PreparedStatement pstmt, Product product) throws SQLException {
    	
    	//(1 IdArticu, 2 Descripcion, 3 Pvp, 4 PvpAux, 5 Puc, 6 Pmc, 7 StockActual, 8 XFam_IdFamilia 9 XGrup_IdGrupoIva)
    	
        pstmt.setString(1, product.getIdF());
        pstmt.setString(2, product.getName());
        pstmt.setDouble(3, product.getRegularPrice());
        pstmt.setDouble(4, product.getSalePrice());
        
      //Puc = Pvp - tax --> Supongo un tax de 0,321 viendo la tabla
        pstmt.setDouble(5, product.getRegularPrice()-0.321*product.getRegularPrice());
        pstmt.setDouble(6, product.getSalePrice()-0.321*product.getSalePrice());
        pstmt.setInt(7, product.getStockActual());
        pstmt.setInt(8, product.getFamily());
        pstmt.setString(9, product.getTax());
       
        pstmt.executeUpdate();
    }
}