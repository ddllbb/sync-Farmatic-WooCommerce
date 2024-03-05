package com.client;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
	
	private static final long serialVersionUID = -3978289590672373380L;
	
	private String id;
    private String fullName;
    private String address;
    private String city;
    private String postcode;
    private String phone;
    private String email;
    private String idW;
    
    public String jsonClient () {
    String json = "{\"first_name\": \"" + fullName + "\", \"billing\": {\"address_1\": \"" + address + "\", \"city\": \"" + city + "\", \"postcode\": \"" + postcode + "\", \"phone\": \"" + phone + "\"}, \"email\": \"" + email + "\"}";
    return json;
    }
    
	public Client() {
		super();
	}
	
	public Client(String id, String fullName, String address, String city, String postcode, String phone,
			String email) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
	}

	public Client(String id, String fullName, String address, String city, String postcode, String phone, String email,
			String idW) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
		this.idW = idW;
	}
	
	public Client addIdW(String idW) {
        return new Client(id, fullName, address, city, postcode, phone, email, idW);
    }

	public Client addIdF(String idF) {
        return new Client(idF, fullName, address, city, postcode, phone, email, idW);
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
        Client other = (Client) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(fullName, other.fullName) &&
               Objects.equals(address, other.address) &&
               Objects.equals(city, other.city) &&
               Objects.equals(postcode, other.postcode) &&
               Objects.equals(phone, other.phone) &&
               Objects.equals(email, other.email) &&
               Objects.equals(idW, other.idW);
    }
	
	@Override
	public int hashCode() {
	    return Objects.hash(id, fullName, address, city, postcode, phone, email, idW);
	}


}
