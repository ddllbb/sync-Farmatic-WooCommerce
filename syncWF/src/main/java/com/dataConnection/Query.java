package com.dataConnection;

public class Query {
	
	//Orders
	public static final String INSERT_SQL_PEDIDO=
			" MERGE INTO Pedido AS target\r\n"
			+ "USING (VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)) AS source (\r\n"
			+ "    IdPedido, XProv_IdProveedor, Modem, NLineas, ImportePvp,\r\n"
			+ "    ImportePuc, Fecha, Hora, Representante, FecVisita, TelfContacto\r\n"
			+ ")\r\n"
			+ "ON target.IdPedido = source.IdPedido\r\n"
			+ "WHEN MATCHED THEN \r\n"
			+ "    UPDATE SET \r\n"
			+ "        target.XProv_IdProveedor = source.XProv_IdProveedor,\r\n"
			+ "        target.Modem = source.Modem,\r\n"
			+ "        target.NLineas = source.NLineas,\r\n"
			+ "        target.ImportePvp = source.ImportePvp,\r\n"
			+ "        target.ImportePuc = source.ImportePuc,\r\n"
			+ "        target.Fecha = source.Fecha,\r\n"
			+ "        target.Hora = source.Hora,\r\n"
			+ "        target.Representante = source.Representante,\r\n"
			+ "        target.FecVisita = source.FecVisita,\r\n"
			+ "        target.TelfContacto = source.TelfContacto\r\n"
			+ "WHEN NOT MATCHED THEN \r\n"
			+ "    INSERT (IdPedido, XProv_IdProveedor, Modem, NLineas, ImportePvp,\r\n"
			+ "            ImportePuc, Fecha, Hora, Representante, FecVisita, TelfContacto)\r\n"
			+ "    VALUES (source.IdPedido, source.XProv_IdProveedor, source.Modem, source.NLineas, \r\n"
			+ "            source.ImportePvp, source.ImportePuc, source.Fecha, source.Hora, \r\n"
			+ "            source.Representante, source.FecVisita, source.TelfContacto);\r\n"
			+ "; ";
	
	public static final String INSERT_SQL_PEDIDO_AUX=
			" MERGE INTO PedidoAux AS target\r\n"
			+ "USING (VALUES (?, ?, ?, ?, ?, ?, ?)) AS source (\r\n"
			+ "    IdPedido, IdFormulaOms, IdCondicion, IdProtocolo, PedEspIdCond,\r\n"
			+ "    PedEspIdProv, IdVendedor\r\n"
			+ ")\r\n"
			+ "ON target.IdPedido = source.IdPedido\r\n"
			+ "WHEN MATCHED THEN \r\n"
			+ "    UPDATE SET \r\n"
			+ "        target.IdFormulaOms = source.IdFormulaOms,\r\n"
			+ "        target.IdCondicion = source.IdCondicion,\r\n"
			+ "        target.IdProtocolo = source.IdProtocolo,\r\n"
			+ "        target.PedEspIdCond = source.PedEspIdCond,\r\n"
			+ "        target.PedEspIdProv = source.PedEspIdProv,\r\n"
			+ "        target.IdVendedor = source.IdVendedor\r\n"
			+ "WHEN NOT MATCHED THEN \r\n"
			+ "    INSERT (IdPedido, IdFormulaOms, IdCondicion, IdProtocolo, PedEspIdCond,\r\n"
			+ "            PedEspIdProv, IdVendedor)\r\n"
			+ "    VALUES (source.IdPedido, source.IdFormulaOms, source.IdCondicion, \r\n"
			+ "            source.IdProtocolo, source.PedEspIdCond, source.PedEspIdProv, \r\n"
			+ "            source.IdVendedor);\r\n"
			+ "; ";
	
	public static final String INSERT_SQL_LINEA_PEDIDO =
		    "MERGE INTO LineaPedido AS target\r\n" +
		    "USING (VALUES (?, ?, ?, ?, ?, ?, ?)) AS source (\r\n" +
		    "    IdPedido, IdLinea, XArt_IdArticu, Unidades, GestionFaltas, Pvp, Puc\r\n" +
		    ")\r\n" +
		    "ON target.IdLinea = source.IdLinea AND target.IdPedido = source.IdPedido\r\n" +
		    "WHEN MATCHED THEN \r\n" +
		    "    UPDATE SET \r\n" +
		    "        target.XArt_IdArticu = source.XArt_IdArticu,\r\n" +
		    "        target.Unidades = source.Unidades,\r\n" +
		    "        target.GestionFaltas = source.GestionFaltas,\r\n" +
		    "        target.Pvp = source.Pvp,\r\n" +
		    "        target.Puc = source.Puc\r\n" +
		    "WHEN NOT MATCHED THEN \r\n" +
		    "    INSERT (IdPedido, IdLinea, XArt_IdArticu, Unidades, GestionFaltas, Pvp, Puc)\r\n" +
		    "    VALUES (source.IdPedido, source.IdLinea, source.XArt_IdArticu, \r\n" +
		    "            source.Unidades, source.GestionFaltas, source.Pvp, source.Puc);\r\n" +
		    "; ";

	
	public static final String INSERT_SQL_LINEA_PEDIDO_AUX=
			" MERGE INTO LineaPedidoAux AS target\r\n"
			+ "USING (VALUES (?, ?, ?, ?, ?)) AS source (\r\n"
			+ "    IdPedidoAux, IdPedido, XArt_IdArticu, PAlbaran, DtoG\r\n"
			+ ")\r\n"
			+ "ON target.IdPedidoAux = source.IdPedidoAux\r\n"
			+ "WHEN MATCHED THEN \r\n"
			+ "    UPDATE SET \r\n"
			+ "        target.XArt_IdArticu = source.XArt_IdArticu,\r\n"
			+ "        target.PAlbaran = source.PAlbaran,\r\n"
			+ "        target.DtoG = source.DtoG\r\n"
			+ "WHEN NOT MATCHED THEN \r\n"
			+ "    INSERT (IdPedidoAux, IdPedido, XArt_IdArticu, PAlbaran, DtoG)\r\n"
			+ "    VALUES (source.IdPedidoAux, source.IdPedido, source.XArt_IdArticu, \r\n"
			+ "            source.PAlbaran, source.DtoG);\r\n"
			+ "; ";
	
	public static final String INSERT_SQL_ORDERS=
"MERGE INTO orders AS target\r\n"
			+ "USING (VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)) \r\n"
			+ "     AS source (\r\n"
			+ "        ID, STATUS, CURRENCY, DATE_CREATED, DATE_MODIFIED, SHIPPING_TOTAL, SHIPPING_TAX, CART_TAX, \r\n"
			+ "        TOTAL, TOTAL_TAX, CUSTOMER_ID, PAYMENT_METHOD, PAYMENT_METHOD_TITLE, CURRENCY_SYMBOL,\r\n"
			+ "        BILLING_FIRST_NAME, BILLING_LAST_NAME, BILLING_COMPANY, BILLING_ADDRESS_1, BILLING_ADDRESS_2, \r\n"
			+ "        BILLING_CITY, BILLING_STATE, BILLING_POSTCODE, BILLING_COUNTRY, BILLING_EMAIL, BILLING_PHONE,\r\n"
			+ "        SHIPPING_FIRST_NAME, SHIPPING_LAST_NAME, SHIPPING_COMPANY, SHIPPING_ADDRESS_1, SHIPPING_ADDRESS_2,\r\n"
			+ "        SHIPPING_CITY, SHIPPING_STATE, SHIPPING_POSTCODE, SHIPPING_COUNTRY, SHIPPING_PHONE\r\n"
			+ "     ) \r\n"
			+ "ON target.ID = source.ID\r\n"
			+ "WHEN MATCHED THEN \r\n"
			+ "    UPDATE SET \r\n"
			+ "        target.STATUS = source.STATUS,\r\n"
			+ "        target.CURRENCY = source.CURRENCY,\r\n"
			+ "        target.DATE_CREATED = source.DATE_CREATED,\r\n"
			+ "        target.DATE_MODIFIED = source.DATE_MODIFIED,\r\n"
			+ "        target.SHIPPING_TOTAL = source.SHIPPING_TOTAL,\r\n"
			+ "        target.SHIPPING_TAX = source.SHIPPING_TAX,\r\n"
			+ "        target.CART_TAX = source.CART_TAX,\r\n"
			+ "        target.TOTAL = source.TOTAL,\r\n"
			+ "        target.TOTAL_TAX = source.TOTAL_TAX,\r\n"
			+ "        target.CUSTOMER_ID = source.CUSTOMER_ID,\r\n"
			+ "        target.PAYMENT_METHOD = source.PAYMENT_METHOD,\r\n"
			+ "        target.PAYMENT_METHOD_TITLE = source.PAYMENT_METHOD_TITLE,\r\n"
			+ "        target.CURRENCY_SYMBOL = source.CURRENCY_SYMBOL,\r\n"
			+ "        target.BILLING_FIRST_NAME = source.BILLING_FIRST_NAME,\r\n"
			+ "        target.BILLING_LAST_NAME = source.BILLING_LAST_NAME,\r\n"
			+ "        target.BILLING_COMPANY = source.BILLING_COMPANY,\r\n"
			+ "        target.BILLING_ADDRESS_1 = source.BILLING_ADDRESS_1,\r\n"
			+ "        target.BILLING_ADDRESS_2 = source.BILLING_ADDRESS_2,\r\n"
			+ "        target.BILLING_CITY = source.BILLING_CITY,\r\n"
			+ "        target.BILLING_STATE = source.BILLING_STATE,\r\n"
			+ "        target.BILLING_POSTCODE = source.BILLING_POSTCODE,\r\n"
			+ "        target.BILLING_COUNTRY = source.BILLING_COUNTRY,\r\n"
			+ "        target.BILLING_EMAIL = source.BILLING_EMAIL,\r\n"
			+ "        target.BILLING_PHONE = source.BILLING_PHONE,\r\n"
			+ "        target.SHIPPING_FIRST_NAME = source.SHIPPING_FIRST_NAME,\r\n"
			+ "        target.SHIPPING_LAST_NAME = source.SHIPPING_LAST_NAME,\r\n"
			+ "        target.SHIPPING_COMPANY = source.SHIPPING_COMPANY,\r\n"
			+ "        target.SHIPPING_ADDRESS_1 = source.SHIPPING_ADDRESS_1,\r\n"
			+ "        target.SHIPPING_ADDRESS_2 = source.SHIPPING_ADDRESS_2,\r\n"
			+ "        target.SHIPPING_CITY = source.SHIPPING_CITY,\r\n"
			+ "        target.SHIPPING_STATE = source.SHIPPING_STATE,\r\n"
			+ "        target.SHIPPING_POSTCODE = source.SHIPPING_POSTCODE,\r\n"
			+ "        target.SHIPPING_COUNTRY = source.SHIPPING_COUNTRY,\r\n"
			+ "        target.SHIPPING_PHONE = source.SHIPPING_PHONE\r\n"
			+ "WHEN NOT MATCHED THEN \r\n"
			+ "    INSERT (\r\n"
			+ "        ID, STATUS, CURRENCY, DATE_CREATED, DATE_MODIFIED, SHIPPING_TOTAL, SHIPPING_TAX, CART_TAX, \r\n"
			+ "        TOTAL, TOTAL_TAX, CUSTOMER_ID, PAYMENT_METHOD, PAYMENT_METHOD_TITLE, CURRENCY_SYMBOL,\r\n"
			+ "        BILLING_FIRST_NAME, BILLING_LAST_NAME, BILLING_COMPANY, BILLING_ADDRESS_1, BILLING_ADDRESS_2, \r\n"
			+ "        BILLING_CITY, BILLING_STATE, BILLING_POSTCODE, BILLING_COUNTRY, BILLING_EMAIL, BILLING_PHONE,\r\n"
			+ "        SHIPPING_FIRST_NAME, SHIPPING_LAST_NAME, SHIPPING_COMPANY, SHIPPING_ADDRESS_1, SHIPPING_ADDRESS_2,\r\n"
			+ "        SHIPPING_CITY, SHIPPING_STATE, SHIPPING_POSTCODE, SHIPPING_COUNTRY, SHIPPING_PHONE\r\n"
			+ "    ) \r\n"
			+ "    VALUES (\r\n"
			+ "        source.ID, source.STATUS, source.CURRENCY, source.DATE_CREATED, source.DATE_MODIFIED, \r\n"
			+ "        source.SHIPPING_TOTAL, source.SHIPPING_TAX, source.CART_TAX, source.TOTAL, source.TOTAL_TAX,\r\n"
			+ "        source.CUSTOMER_ID, source.PAYMENT_METHOD, source.PAYMENT_METHOD_TITLE, source.CURRENCY_SYMBOL,\r\n"
			+ "        source.BILLING_FIRST_NAME, source.BILLING_LAST_NAME, source.BILLING_COMPANY, source.BILLING_ADDRESS_1,\r\n"
			+ "        source.BILLING_ADDRESS_2, source.BILLING_CITY, source.BILLING_STATE, source.BILLING_POSTCODE,\r\n"
			+ "        source.BILLING_COUNTRY, source.BILLING_EMAIL, source.BILLING_PHONE, source.SHIPPING_FIRST_NAME,\r\n"
			+ "        source.SHIPPING_LAST_NAME, source.SHIPPING_COMPANY, source.SHIPPING_ADDRESS_1,\r\n"
			+ "        source.SHIPPING_ADDRESS_2, source.SHIPPING_CITY, source.SHIPPING_STATE, source.SHIPPING_POSTCODE,\r\n"
			+ "        source.SHIPPING_COUNTRY, source.SHIPPING_PHONE\r\n"
			+ "    );\r\n"
			+ " ;";
	
	public static final String EXPORT_SQL_ORDERS= "SELECT * FROM Orders";

	//Products
	public static final String INSERT_SQL_PRODUCTS =
			"MERGE INTO Articu AS target " +
					"USING (VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)) AS source (IdArticu, Descripcion, Pvp, PvpAux, Puc, Pmc, StockActual, XFam_IdFamilia, XGrup_IdGrupoIva) " +
					"ON target.IdArticu = source.IdArticu " +
					"WHEN MATCHED THEN " +
					"    UPDATE SET " +
					"        target.Descripcion = source.Descripcion, " +
					"        target.Pvp = source.Pvp, " +
					"        target.PvpAux = source.PvpAux, " +
					"        target.Puc = source.Puc, " +
					"        target.Pmc = source.Pmc, " +
					"        target.StockActual = source.StockActual, " +
					"        target.XFam_IdFamilia = source.XFam_IdFamilia, " +
					"        target.XGrup_IdGrupoIva = source.XGrup_IdGrupoIva "+
					"WHEN NOT MATCHED THEN " +
					"    INSERT (IdArticu, Descripcion, Pvp, PvpAux, Puc, Pmc, StockActual, XFam_IdFamilia, XGrup_IdGrupoIva, Efp, Receta, Psicotropo, Estupefaciente, Frigorifico, Cicero, Caducidad, Visado, Ecm, ExcluidoSS, Baja, UsoH, DiagnosticoH, Tld, StockMinimo, StockMaximo, LoteOptimo) " +
					"    VALUES (source.IdArticu, source.Descripcion, source.Pvp, source.PvpAux, source.Puc, source.Pmc, source.StockActual,source.XFam_IdFamilia, source.XGrup_IdGrupoIva,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);";
	public static final String EXPORT_SQL_PRODUCTS = "SELECT * FROM Articu";

	//Clients
	public static final String INSERT_SQL_CLIENTS =
			"MERGE INTO Cliente AS target " +
					"USING (VALUES (?, ?, ?, ?, ?, ?, ?)) AS source (IDCLIENTE, PER_NOMBRE, OBSERVACIONES, PER_DIRECCION, PER_POBLACION, PER_CODPOSTAL, PER_TELEFONO) " +
					"ON target.IDCLIENTE = source.IDCLIENTE " +
					"WHEN MATCHED THEN " +
					"    UPDATE SET " +
					"    target.PER_NOMBRE = source.PER_NOMBRE, " +
					"    target.OBSERVACIONES = source.OBSERVACIONES, " +
					"    target.PER_DIRECCION = source.PER_DIRECCION, " +
					"    target.PER_POBLACION = source.PER_POBLACION, " +
					"    target.PER_CODPOSTAL = source.PER_CODPOSTAL, " +
					"    target.PER_TELEFONO = source.PER_TELEFONO " +
					"WHEN NOT MATCHED THEN " +
					"    INSERT (IDCLIENTE, PER_NOMBRE, OBSERVACIONES, PER_DIRECCION, PER_POBLACION, PER_CODPOSTAL, PER_TELEFONO) " +
					"    VALUES (source.IDCLIENTE, source.PER_NOMBRE, source.OBSERVACIONES, source.PER_DIRECCION, source.PER_POBLACION, source.PER_CODPOSTAL, source.PER_TELEFONO);";
	public static final String EXPORT_SQL_CLIENTS = "SELECT * FROM Cliente";
}
