package com.product;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private static final long serialVersionUID = 123456789L;

    private String idF;
    private String name;
    private Double regularPrice;
    private Double salePrice;
    private int stockActual;
    private String shortDescription;
    private String description;
    private int family;
    private String tax;
    private String idW;
    
    

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}
	
	public String stockStatus(int stockActual) {
    	if (stockActual == 0) {
    		return "outofstock";
    	}return "instock";
    }
	
	public String jsonProduct() {
        String json = "{" +
                "\"name\": \"" + name + "\"," +
                "\"regular_price\": \"" + regularPrice + "\"," +
                "\"sale_price\": \"" + salePrice + "\"," +
                "\"stock_status\": \"" + stockStatus(stockActual) + "\"}";
        return json;
    }
    
    public Product addIdW(String idW) {
        return new Product(idF, name, regularPrice, salePrice, stockActual, shortDescription, description,family,tax, idW);
    }

	public Product addIdF(String idF) {
        return new Product(idF, name, regularPrice, salePrice, stockActual, shortDescription, description,family,tax, idW);
    }


    public Product() {
        super();
    }

    public Product(String idF, String name, Double regularPrice, Double salePrice, int stockActual,
                   String shortDescription, String description,int family,String tax, String idW) {
        
        this.idF = idF;
        this.name = name;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.stockActual = stockActual;
        this.shortDescription = shortDescription;
        this.description = description;
        this.family = family;
        this.tax = tax;
        this.idW = idW;
    }
    
    public Product(String idF, String name, Double regularPrice, Double salePrice, int stockStatus,
    		String shortDescription, String description,int family,String tax) {

    	this.idF = idF;
    	this.name = name;
    	this.regularPrice = regularPrice;
    	this.salePrice = salePrice;
    	this.stockActual = stockStatus;
    	this.shortDescription = shortDescription;
    	this.description = description;
    	this.family = family;
    	this.tax = tax;
    }

    public String getIdF() {
        return idF;
    }

    public void setIdF(String idF) {
        this.idF = idF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockStatus) {
        this.stockActual = stockStatus;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdW() {
        return idW;
    }

    public void setIdW(String idW) {
        this.idW = idW;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return Objects.equals(idF, other.idF) &&
               Objects.equals(name, other.name) &&
               Objects.equals(regularPrice, other.regularPrice) &&
               Objects.equals(salePrice, other.salePrice) &&
               Objects.equals(stockActual, other.stockActual) &&
               Objects.equals(shortDescription, other.shortDescription) &&
               Objects.equals(description, other.description) &&
               Objects.equals(family, other.family) &&
               Objects.equals(tax, other.tax) &&
               Objects.equals(idW, other.idW);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idF, name, regularPrice, salePrice, stockActual, shortDescription, description,family,tax,idW);
    }
    
    
    
    public static String mapStockStatus(String stockColumnValue) {
        String lowerCaseValue = stockColumnValue.toLowerCase();
        String stockStatus2;
        switch (lowerCaseValue) {
            case "si":
            	stockStatus2 = "instock";
                break;
            case "no":
            	stockStatus2 = "outofstock";
                break;
            case "pedido realizado":
            	stockStatus2 = "onbackorder";
                break;
            default:
                // Si el valor no es uno de los esperados, establecer como "instock"
            	stockStatus2 = "instock";
                break;
        }
        return stockStatus2;
    }
    public static String mapStockStatusReverse(String stockApiValue) {
        String lowerCaseValue = stockApiValue.toLowerCase();
        String stockStatus2;
        switch (lowerCaseValue) {
            case "instock":
            	stockStatus2 = "si";
                break;
            case "outofstock":
            	stockStatus2 = "no";
                break;
            case "onbackorder":
            	stockStatus2 = "pedido realizado";
                break;
            default:
                // Si el valor no es uno de los esperados, establecer como "instock"
            	stockStatus2 = "si";
                break;
        }
        return stockStatus2;
    }
}
