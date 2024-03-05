package com.order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderFields implements Serializable {

    private static final long serialVersionUID = -3978289590672373381L;

    private String id;
    private String status;
    private String currency;
    private String version;
    private boolean pricesIncludeTax;
    private String dateCreated;
    private String dateModified;
    private String discountTotal;
    private String discountTax;
    private String shippingTotal;
    private String shippingTax;
    private String cartTax;
    private String total;
    private String totalTax;
    private int customerId;
    private String orderKey;
    private Billing billing;
    private Shipping shipping;
    private String paymentMethod;
    private String paymentMethodTitle;
    private String customerIpAddress;
    private String customerUserAgent;
    private String createdVia;
    private String cartHash;
    private String number;
    private String currencySymbol;

   public static Timestamp datesManager(String fechaString) throws ParseException {
       // Formato actual de la cadena
       SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
           // Parsear la cadena a un objeto Date
           Date fechaDate = formatoOriginal.parse(fechaString);
           // Crear un nuevo formato para convertir a la cadena deseada
           SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           // Obtener la cadena en el nuevo formato
           String fechaFormateada = formatoNuevo.format(fechaDate);
           // Convertir la cadena formateada a Timestamp
           Timestamp fechaTimestamp = Timestamp.valueOf(fechaFormateada);
		return fechaTimestamp;
   }

    public static class Billing {
        private String firstName;
        private String lastName;
        private String company;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String postcode;
        private String country;
        private String email;
        private String phone;

        // Getters and setters

        // Equals and hashCode methods
    }

    public static class Shipping {
        private String firstName;
        private String lastName;
        private String company;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String postcode;
        private String country;
        private String phone;

        // Getters and setters

        // Equals and hashCode methods
    }

    public static class MetaData {
        private int id;
        private String key;
        private String value;

        // Getters and setters

        // Equals and hashCode methods
    }

    public static class LineItem {
        private int id;
        private String name;
        private int productId;
        private int variationId;
        private int quantity;
        private String taxClass;
        private String subtotal;
        private String subtotalTax;
        private String total;
        private String totalTax;
        private List<Tax> taxes;
        private String sku;
        private double price;
        private Image image;
        private String parentName;

        // Getters and setters

        // Equals and hashCode methods

        public static class Tax {
            private int id;
            private String total;
            private String subtotal;

            // Getters and setters

            // Equals and hashCode methods
        }

        public static class Image {
            private String id;
            private String src;

            // Getters and setters

            // Equals and hashCode methods
        }
    }

    public static class TaxLine {
        private int id;
        private String rateCode;
        private int rateId;
        private String label;
        private boolean compound;
        private String taxTotal;
        private String shippingTaxTotal;
        private int ratePercent;

        // Getters and setters

        // Equals and hashCode methods
    }

    public static class ShippingLine {
        private int id;
        private String methodTitle;
        private String methodId;
        private String instanceId;
        private String total;
        private String totalTax;

        // Getters and setters

        // Equals and hashCode methods
    }

    public static class Links {
        private List<Self> self;
        private List<Collection> collection;

        // Getters and setters

        // Equals and hashCode methods

        public static class Self {
            private String href;

            // Getters and setters

            // Equals and hashCode methods
        }

        public static class Collection {
            private String href;

            // Getters and setters

            // Equals and hashCode methods
        }
    }
}
