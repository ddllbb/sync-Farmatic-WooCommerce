package com.order;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;

public class Order implements Serializable {

	private static final long serialVersionUID = 123456789L;
    
    private String idW;
    
    
    public String getIdW() {
		return idW;
	}

	public void setIdW(String idW) {
		this.idW = idW;
	}

	private String idF;//4357
    private String status; //Order status. Options: pending, processing, on-hold, completed, cancelled, refunded, failed and trash. Default is pending
    private String currency;//EUR --> enumerado, escrito así
    private String dateCreated;//"2023-12-12T10:38:13"
    private String dateModified;//"2023-12-12T10:38:14"
    private String shippingTotal;//"4.00"
    private String shippingTax;//"0.84"
    private String cartTax;//"6.40"
    private String total;//"41.74"
    private String totalTax;//"7.24"
    private int customerId;// 0 --> integer	User ID who owns the order. 0 for guests. Default is 0
    private String paymentMethod;// "bacs" --> string	Payment method ID
    private String paymentMethodTitle;// "Transferencia bancaria directa"
    private String currencySymbol;//"€"
    
    private Billing billing; // object	Billing address. See Order - Billing properties
    /*billing: {"first_name":"José",
     * "last_name":"Alcocer",
     * "company":"",
     * "address_1":"Avenida de la >Encina, 7",
     * "address_2":"","city":"Fuenlabrada",
     * "state":"M",
     * "postcode":"28942",
     * "country":"ES",
     * "email":"jr.alcocer@grupoftp.com",
     * "phone":"618155874"}*/
    
    private Shipping shipping; // object	Shipping address. See Order - Shipping properties
    /*{"first_name":"José",
     * "last_name":"Alcocer",
     * "company":"",
     * "address_1":"Avenida de la >Encina, 7",
     * "address_2":"",
     * "city":"Fuenlabrada",
     * "state":"M",
     * "postcode":"28942",
     * "country":"ES",
     * "phone":""}*/
    private List<LineItem> lineItems;
    /*
     * line_items: [
	{"id":6,"name":"INTERPROX CÓNICO 6 UDS","product_id":4194,"variation_id":0,"quantity":1,"tax_class":"","subtotal":"4.63","subtotal_tax":"0.97","total":"4.63","total_tax":"0.97","taxes":[{"id":1,"total":"0.971901","subtotal":"0.971901"}],"meta_data":[],"sku":"","price":4.6280989999999997,"image":{"id":"4195","src":"https://fuentecarrantona.com/wp-content/uploads/interprox-conical_600x600.jpg"},"parent_name":null},
	{"id":7,"name":"ACCU-CHEK SOFTCLIX II PINCHADOR + 25 LANCETAS","product_id":3200,"variation_id":0,"quantity":1,"tax_class":"","subtotal":"15.21","subtotal_tax":"3.19","total":"15.21","total_tax":"3.19","taxes":[{"id":1,"total":"3.193388","subtotal":"3.193388"}],"meta_data":[],"sku":"","price":15.206612,"image":{"id":"3202","src":"https://fuentecarrantona.com/wp-content/uploads/ACCU-CHEK-SOFTCLIX-2_600x600.jpg"},"parent_name":null},
	{"id":8,"name":"CHAMPU EXTRACTO DE CEBOLLA MI REBOTICA 500 ML","product_id":3639,"variation_id":0,"quantity":1,"tax_class":"","subtotal":"10.66","subtotal_tax":"2.24","total":"10.66","total_tax":"2.24","taxes":[{"id":1,"total":"2.238843","subtotal":"2.238843"}],"meta_data":[],"sku":"","price":10.661156999999999,"image":{"id":"3640","src":"https://fuentecarrantona.com/wp-content/uploads/mirebotica-champu-cebolla_600x600.jpg"},
	"parent_name":null}]
     * */
   
    public Order(String idF, String idW, String status, String currency, String dateCreated, String dateModified,
			String shippingTotal, String shippingTax, String cartTax, String total, String totalTax, int customerId,
			String paymentMethod, String paymentMethodTitle, String currencySymbol, Billing billing, Shipping shipping,
			List<LineItem> lineItem) {
		super();
		this.idF = idF;
		this.idW = idW;
		this.status = status;
		this.currency = currency;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.shippingTotal = shippingTotal;
		this.shippingTax = shippingTax;
		this.cartTax = cartTax;
		this.total = total;
		this.totalTax = totalTax;
		this.customerId = customerId;
		this.paymentMethod = paymentMethod;
		this.paymentMethodTitle = paymentMethodTitle;
		this.currencySymbol = currencySymbol;
		this.billing = billing;
		this.shipping = shipping;
		this.lineItems = lineItem;
	}

    public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order addIdW(String idW) {
        return new Order(idF, idW, status, currency, dateCreated, dateModified, shippingTotal, shippingTax, cartTax,
                total, totalTax, customerId, paymentMethod, paymentMethodTitle, currencySymbol, billing, shipping, lineItems);
    }

    public Order addIdF(String idF) {
        return new Order(idF, idW, status, currency, dateCreated, dateModified, shippingTotal, shippingTax, cartTax,
                total, totalTax, customerId, paymentMethod, paymentMethodTitle, currencySymbol, billing, shipping, lineItems);
    }


    // Inner classes

    public static class Billing implements Serializable{
    	
    	private static final long serialVersionUID = 987654321L;
        private String firstName;
        private String lastName;
        private String company;
        private String address1;
        private String city;
        private String state;
        private String postcode;
        private String country;
        private String email;
        private String phone;
        
        public String jsonBilling() {
            String json = "{" +
                    "\"first_name\": \"" + firstName + "\"," +
                    "\"last_name\": \"" + lastName + "\"," +
                    "\"company\": \"" + company + "\"," +
                    "\"address_1\": \"" + address1 + "\"," +
                    "\"city\": \"" + city + "\"," +
                    "\"state\": \"" + state + "\"," +
                    "\"postcode\": \"" + postcode + "\"," +
                    "\"country\": \"" + country + "\"," +
                    "\"email\": \"" + email + "\"," +
                    "\"phone\": \"" + phone + "\"" +
                    "}";
            
            return json;
        }
        
        public Billing(String firstName, String lastName, String company, String address1,
				String city, String state, String postcode, String country, String email, String phone) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.company = company;
			this.address1 = address1;
			this.city = city;
			this.state = state;
			this.postcode = postcode;
			this.country = country;
			this.email = email;
			this.phone = phone;
		}

		public Billing() {
			// TODO Auto-generated constructor stub
		}

		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}

        
        
        
        
    }

    public static class Shipping implements Serializable{
    	
    	private static final long serialVersionUID = 111223344L;
        private String firstName;
        private String lastName;
        private String company;
        private String address1;
        private String city;
        private String state;
        private String postcode;
        private String country;
        private String phone;
        
        public String jsonShipping() {
            String json = "{" +
                    "\"first_name\": \"" + firstName + "\"," +
                    "\"last_name\": \"" + lastName + "\"," +
                    "\"company\": \"" + company + "\"," +
                    "\"address_1\": \"" + address1 + "\"," +
                    "\"city\": \"" + city + "\"," +
                    "\"state\": \"" + state + "\"," +
                    "\"postcode\": \"" + postcode + "\"," +
                    "\"country\": \"" + country + "\"," +
                    "\"phone\": \"" + phone + "\"" +
                    "}";
            
            return json;
        }

		public Shipping(String firstName, String lastName, String company, String address1,
				String city, String state, String postcode, String country, String phone) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.company = company;
			this.address1 = address1;
			this.city = city;
			this.state = state;
			this.postcode = postcode;
			this.country = country;
			this.phone = phone;
		}
		public Shipping() {
			// TODO Auto-generated constructor stub
		}
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
        
        // Equals and hashCode methods
    }

    public static class LineItem implements Serializable{
    	
    	private static final long serialVersionUID = 555666777L;
        private int lineItemId;
        private String name;
        private int productId;
        private int quantity;
        private String total;
        private String totalTax;
        private double price;
        /*{"id":6,
    	"name":"INTERPROX CÓNICO 6 UDS",
    	"product_id":4194,
    	"variation_id":0,
    	"quantity":1,
    	"tax_class":"",
    	"subtotal":"4.63",
    	"subtotal_tax":"0.97",
    	"total":"4.63",
    	"total_tax":"0.97",
    	"taxes":[{"id":1,"total":"0.971901","subtotal":"0.971901"}],
    	"meta_data":[],
    	"sku":"",
    	"price":4.6280989999999997,
    	"image":{"id":"4195","src":"https://fuentecarrantona.com/wp-content/uploads/interprox-conical_600x600.jpg"},
    	"parent_name":null}
        */
        public String jsonLineItems() {
        	String sku = ""+productId;
            String json = "{" +
                    "\"id\": " + lineItemId + "," +
                    "\"name\": \"" + name + "\"," +
                    "\"product_id\": " + productId + "," +
                    "\"sku\": " + sku + "," +
                    "\"quantity\": " + quantity + "," +
                    "\"total\": \"" + total + "\"," +
                    "\"total_tax\": \"" + totalTax + "\"," +
                    "\"price\": " + price +
                    "}";
            
            return json;
        }


        
		public LineItem() {
			super();
		}
		
		public LineItem(int lineItemId, String name, int productId, int quantity, String total,
				String totalTax, double price) {
			super();
			this.lineItemId = lineItemId;
			this.name = name;
			this.productId = productId;
			this.quantity = quantity;
			this.total = total;
			this.totalTax = totalTax;
			this.price = price;
		}


		public int getLineItemId() {
			return lineItemId;
		}

		public void setLineItemId(int lineItemId) {
			this.lineItemId = lineItemId;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		public String getTotalTax() {
			return totalTax;
		}
		public void setTotalTax(String totalTax) {
			this.totalTax = totalTax;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
    }
    
 // Equals and hashCode methods
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return customerId == other.customerId &&
               Objects.equals(idF, other.idF) &&
               Objects.equals(status, other.status) &&
               Objects.equals(currency, other.currency) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateModified, other.dateModified) &&
               Objects.equals(shippingTotal, other.shippingTotal) &&
               Objects.equals(shippingTax, other.shippingTax) &&
               Objects.equals(cartTax, other.cartTax) &&
               Objects.equals(total, other.total) &&
               Objects.equals(totalTax, other.totalTax) &&
               Objects.equals(paymentMethod, other.paymentMethod) &&
               Objects.equals(paymentMethodTitle, other.paymentMethodTitle) &&
               Objects.equals(currencySymbol, other.currencySymbol) &&
               Objects.equals(billing, other.billing) &&
               Objects.equals(shipping, other.shipping) &&
               Objects.equals(lineItems, other.lineItems) &&
               Objects.equals(idW, other.idW);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idF, status, currency, dateCreated, dateModified, shippingTotal, shippingTax,
                            cartTax, total, totalTax, customerId, paymentMethod, paymentMethodTitle,
                            currencySymbol, billing, shipping, lineItems, idW);
    }

	public String getIdF() {
		return idF;
	}

	public void setIdF(String idF) {
		this.idF = idF;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public String getShippingTotal() {
		return shippingTotal;
	}

	public void setShippingTotal(String shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public String getShippingTax() {
		return shippingTax;
	}

	public void setShippingTax(String shippingTax) {
		this.shippingTax = shippingTax;
	}

	public String getCartTax() {
		return cartTax;
	}

	public void setCartTax(String cartTax) {
		this.cartTax = cartTax;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethodTitle() {
		return paymentMethodTitle;
	}

	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    	
}
