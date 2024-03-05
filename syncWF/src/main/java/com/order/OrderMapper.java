package com.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.connections.ConnectorBBDD;
import com.dataConnection.LoginClient;
import com.dataConnection.Query;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.order.Order.Billing;
import com.order.Order.LineItem;
import com.order.Order.Shipping;

public class OrderMapper {

	/*public static Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setIdF(resultSet.getString("ID"));
        order.setStatus(resultSet.getString("STATUS"));
        order.setCurrency(resultSet.getString("CURRENCY"));
        order.setDateCreated(resultSet.getString("DATE_CREATED"));
        order.setDateModified(resultSet.getString("DATE_MODIFIED"));
        order.setShippingTotal(resultSet.getString("SHIPPING_TOTAL"));
        order.setShippingTax(resultSet.getString("SHIPPING_TAX"));
        order.setCartTax(resultSet.getString("CART_TAX"));
        order.setTotal(resultSet.getString("TOTAL"));
        order.setTotalTax(resultSet.getString("TOTAL_TAX"));
        order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        order.setPaymentMethod(resultSet.getString("PAYMENT_METHOD"));
        order.setPaymentMethodTitle(resultSet.getString("PAYMENT_METHOD_TITLE"));
        order.setCurrencySymbol(resultSet.getString("CURRENCY_SYMBOL"));

        Billing billing = new Billing();
        billing.setFirstName(resultSet.getString("BILLING_FIRST_NAME"));
        billing.setLastName(resultSet.getString("BILLING_LAST_NAME"));
        billing.setCompany(resultSet.getString("BILLING_COMPANY"));
        billing.setAddress1(resultSet.getString("BILLING_ADDRESS_1"));
        billing.setCity(resultSet.getString("BILLING_CITY"));
        billing.setState(resultSet.getString("BILLING_STATE"));
        billing.setPostcode(resultSet.getString("BILLING_POSTCODE"));
        billing.setCountry(resultSet.getString("BILLING_COUNTRY"));
        billing.setEmail(resultSet.getString("BILLING_EMAIL"));
        billing.setPhone(resultSet.getString("BILLING_PHONE"));
        order.setBilling(billing);

        Shipping shipping = new Shipping();
        shipping.setFirstName(resultSet.getString("SHIPPING_FIRST_NAME"));
        shipping.setLastName(resultSet.getString("SHIPPING_LAST_NAME"));
        shipping.setCompany(resultSet.getString("SHIPPING_COMPANY"));
        shipping.setAddress1(resultSet.getString("SHIPPING_ADDRESS_1"));
        shipping.setCity(resultSet.getString("SHIPPING_CITY"));
        shipping.setState(resultSet.getString("SHIPPING_STATE"));
        shipping.setPostcode(resultSet.getString("SHIPPING_POSTCODE"));
        shipping.setCountry(resultSet.getString("SHIPPING_COUNTRY"));
        shipping.setPhone(resultSet.getString("SHIPPING_PHONE"));
        order.setShipping(shipping);
        
        //Cada fila será un lineItem, es decir, un prodcto
        //A la espera de la distribución de la bbdd de Farmatic
        //Probablemente sean tablas relacionales, y lineItems se una tabla en la que cada fila sea un artículo.
        
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(resultSet.getInt("LINE_ITEM_ID"));
        lineItem.setName(resultSet.getString("LINE_ITEM_NAME"));
        lineItem.setProductId(resultSet.getInt("LINE_ITEM_PRODUCT_ID"));
        lineItem.setQuantity(resultSet.getInt("LINE_ITEM_QUANTITY"));
        lineItem.setTotal(resultSet.getString("LINE_ITEM_TOTAL"));
        lineItem.setTotalTax(resultSet.getString("LINE_ITEM_TOTAL_TAX"));
        lineItem.setPrice(resultSet.getDouble("LINE_ITEM_PRICE"));
        order.setLineItems(lineItem);
        
        return order;
    }*/

	public static Order mapJsonToOrder(JsonElement jsonElement) {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		Order order = new Order();
		order.setIdW(jsonObject.get("id").getAsString());
		order.setStatus(jsonObject.get("status").getAsString());
		order.setCurrency(jsonObject.get("currency").getAsString());
		order.setDateCreated(jsonObject.get("date_created").getAsString());
		order.setDateModified(jsonObject.get("date_modified").getAsString());
		order.setShippingTotal(jsonObject.get("shipping_total").getAsString());
		order.setShippingTax(jsonObject.get("shipping_tax").getAsString());
		order.setCartTax(jsonObject.get("cart_tax").getAsString());
		order.setTotal(jsonObject.get("total").getAsString());
		order.setTotalTax(jsonObject.get("total_tax").getAsString());
		order.setCustomerId(jsonObject.get("customer_id").getAsInt());
		order.setPaymentMethod(jsonObject.get("payment_method").getAsString());
		order.setPaymentMethodTitle(jsonObject.get("payment_method_title").getAsString());
		order.setCurrencySymbol(jsonObject.get("currency_symbol").getAsString());

		JsonObject billingObject = jsonObject.getAsJsonObject("billing");
		Billing billing = new Billing();
		billing.setFirstName(billingObject.get("first_name").getAsString());
		billing.setLastName(billingObject.get("last_name").getAsString());
		billing.setCompany(billingObject.get("company").getAsString());
		billing.setAddress1(billingObject.get("address_1").getAsString());
		billing.setCity(billingObject.get("city").getAsString());
		billing.setState(billingObject.get("state").getAsString());
		billing.setPostcode(billingObject.get("postcode").getAsString());
		billing.setCountry(billingObject.get("country").getAsString());
		billing.setEmail(billingObject.get("email").getAsString());
		billing.setPhone(billingObject.get("phone").getAsString());
		order.setBilling(billing);

		JsonObject shippingObject = jsonObject.getAsJsonObject("shipping");
		Shipping shipping = new Shipping();
		shipping.setFirstName(shippingObject.get("first_name").getAsString());
		shipping.setLastName(shippingObject.get("last_name").getAsString());
		shipping.setCompany(shippingObject.get("company").getAsString());
		shipping.setAddress1(shippingObject.get("address_1").getAsString());
		shipping.setCity(shippingObject.get("city").getAsString());
		shipping.setState(shippingObject.get("state").getAsString());
		shipping.setPostcode(shippingObject.get("postcode").getAsString());
		shipping.setCountry(shippingObject.get("country").getAsString());
		shipping.setPhone(shippingObject.get("phone").getAsString());
		order.setShipping(shipping);

		//Array lineItems
		JsonArray lineItemsArray = jsonObject.getAsJsonArray("line_items");
		List<LineItem> lineItemsList = new ArrayList<>();
		for (JsonElement itemElement : lineItemsArray) {
			JsonObject lineItemObject = itemElement.getAsJsonObject();
			LineItem lineItem = new LineItem();
			lineItem.setLineItemId(lineItemObject.get("id").getAsInt());
			lineItem.setName(lineItemObject.get("name").getAsString());
			lineItem.setProductId(lineItemObject.get("product_id").getAsInt());
			lineItem.setQuantity(lineItemObject.get("quantity").getAsInt());
			lineItem.setTotal(lineItemObject.get("total").getAsString());
			lineItem.setTotalTax(lineItemObject.get("total_tax").getAsString());
			lineItem.setPrice(lineItemObject.get("price").getAsDouble());
			lineItemsList.add(lineItem);
		}
		order.setLineItems(lineItemsList);
		
		
		return order;
	}
	
	public static void OrderToBbdd(LoginClient login, Order order) throws SQLException, ParseException, ClassNotFoundException {
		ConnectorBBDD connector = new ConnectorBBDD(login,Query.INSERT_SQL_ORDERS);
		Connection conexion = connector.getConnection();

		PreparedStatement pstmtPedido = conexion.prepareStatement(Query.INSERT_SQL_PEDIDO);
		PreparedStatement pstmtPedidoAux = conexion.prepareStatement(Query.INSERT_SQL_PEDIDO_AUX);
		PreparedStatement pstmtLineaPedido = conexion.prepareStatement(Query.INSERT_SQL_LINEA_PEDIDO);
		PreparedStatement pstmtLineaPedidoAux = conexion.prepareStatement(Query.INSERT_SQL_LINEA_PEDIDO_AUX);

		/* 1 - IdPedido, 
		 * 2 - XProv_IdProveedor, 
		 * 3 - Modem, 
		 * 4 - NLineas, 
		 * 5 - ImportePvp,
		 * 6 - ImportePuc, 
		 * 7 - Fecha, 
		 * 8 - Hora, 
		 * 9 - Representante, 
		 * 10 - FecVisita, 
		 * 11 - TelfContacto */
		pstmtPedido.setInt(1, Integer.parseInt(order.getIdF()));
		pstmtPedido.setString(2, "0001");
		pstmtPedido.setBoolean(3, true);
		pstmtPedido.setInt(4, order.getLineItems().size());
		pstmtPedido.setDouble(5, Double.parseDouble(order.getTotal()));
		pstmtPedido.setDouble(6, Double.parseDouble(order.getTotal())- Double.parseDouble(order.getTotalTax()));//Pcu = Pvp - Tax
		pstmtPedido.setTimestamp(7, OrderFields.datesManager(order.getDateCreated()));
		pstmtPedido.setTimestamp(8, OrderFields.datesManager(order.getDateModified()));
		pstmtPedido.setString(9, null);
		pstmtPedido.setTimestamp(10, null);
		pstmtPedido.setString(11, null);

		pstmtPedido.executeUpdate();


		/* 1 - IdPedido, 
		 * 2 - IdFormulaOms, 
		 * 3 - IdCondicion, 
		 * 4 - IdProtocolo, 
		 * 5 - PedEspIdCond,
		 * 6 - PedEspIdProv, 
		 * 7 - IdVendedor*/
		pstmtPedidoAux.setInt(1, Integer.parseInt(order.getIdF()));
		pstmtPedidoAux.setInt(2, 0);
		pstmtPedidoAux.setInt(3, 0);
		pstmtPedidoAux.setInt(4, 8);
		pstmtPedidoAux.setInt(5, 0);
		pstmtPedidoAux.setNull(6, Types.INTEGER);
		pstmtPedidoAux.setString(7, "0");

		pstmtPedidoAux.executeUpdate();


		/* 1 - IdPedido,
		 * 2 - IdLinea,
		 * 3 - XArt_IdArticu,
		 * 4 - Unidades,
		 * 5 - GestionFaltas,
		 * 6 - Pvp 
		 * 7 - Puc */

		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = order.getLineItems().get(i);

			pstmtLineaPedido.setInt(1, Integer.parseInt(order.getIdF()));
			pstmtLineaPedido.setInt(2, lineItem.getLineItemId());
			pstmtLineaPedido.setString(3, String.valueOf(lineItem.getProductId()));
			pstmtLineaPedido.setInt(4, lineItem.getQuantity());
			pstmtLineaPedido.setBoolean(5, false);
			pstmtLineaPedido.setDouble(6, Double.parseDouble(lineItem.getTotal()));
			pstmtLineaPedido.setDouble(7, Double.parseDouble(lineItem.getTotal())-Double.parseDouble(lineItem.getTotalTax()));

			pstmtLineaPedido.executeUpdate();
		}


		/* 1 - IdPedidoAux,
		 * 2 - IdPedido,
		 * 3 - XArt_IdArticu,
		 * 4 - PAlbaran,
		 * 5 - DtoG */

		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = order.getLineItems().get(i);

			pstmtLineaPedidoAux.setInt(1,lineItem.getLineItemId() );
			pstmtLineaPedidoAux.setInt(2, Integer.parseInt(order.getIdF()));
			pstmtLineaPedidoAux.setString(3, String.valueOf(lineItem.getProductId()));
			pstmtLineaPedidoAux.setDouble(4, lineItem.getPrice());
			pstmtLineaPedidoAux.setFloat(5, 30);

			pstmtLineaPedidoAux.executeUpdate();
		}
	}
}