package com.connections;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.client.Client;
import com.order.IdFVsIdWOrder;
import com.order.Order;
import com.order.Order.Billing;
import com.order.Order.Shipping;
import com.product.Product;

public class FileMapManager {
	
	public static String generateNewIdF(Map<String, Client> clientsMap) {
        int maxIdF = 0;
        // Itera sobre el mapa para encontrar el idF más alto
        for (Client client : clientsMap.values()) {
            String idF = client.getId();
            int idFValue = Integer.parseInt(idF);
            if (idFValue > maxIdF) {
               maxIdF = idFValue;
            }
         }
        int newId = maxIdF + 1;
        String newIdF = ""+newId;
		return newIdF;
    }
	
	public static String generateNewIdFProduct(Map<String, Product> map) {
        int maxIdF = 0;
        // Itera sobre el mapa para encontrar el idF más alto
        for (Product product : map.values()) {
            String idF = product.getIdF();
            int idFValue = Integer.parseInt(idF);
            if (idFValue > maxIdF) {
               maxIdF = idFValue;
            }
         }
        int newId = maxIdF + 1;
        String newIdF = ""+newId;
		return newIdF;
    }
	
	public static String generateNewIdFOrder(Map<String, Order> map) {
        int maxIdF = 0;
        // Itera sobre el mapa para encontrar el idF más alto
        for (Order order : map.values()) {
            String idF = order.getIdF();
            int idFValue = Integer.parseInt(idF);
            if (idFValue > maxIdF) {
               maxIdF = idFValue;
            }
         }
        int newId = maxIdF + 1;
        String newIdF = ""+newId;
		return newIdF;
    }
	
	public static String generateNewIdFOrderIds(Map<String, IdFVsIdWOrder> map) {
        int maxIdF = 0;
        // Itera sobre el mapa para encontrar el idF más alto
        for (IdFVsIdWOrder idsOrder : map.values()) {
            String idF = idsOrder.getIdF();
            int idFValue = Integer.parseInt(idF);
            if (idFValue > maxIdF) {
               maxIdF = idFValue;
            }
         }
        int newId = maxIdF + 1;
        String newIdF = ""+newId;
		return newIdF;
    }
	
	
	public static String getIdFbyIdW (String idW, Map<String, Client> map) {
		boolean found = false;
	    for (Map.Entry<String, Client> entry : map.entrySet()) {
	        Client client = entry.getValue();
	        if (idW.equals(client.getIdW())) {
	            found = true;
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	public static String getIdFbyIdWProduct (String idW, Map<String, Product> map) {
		boolean found = false;
	    for (Map.Entry<String, Product> entry : map.entrySet()) {
	        Product product = entry.getValue();
	        if (idW.equals(product.getIdW())) {
	            found = true;
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public static String getIdFbyIdWOrder(String idW, Map<String, Order> map) {
		boolean found = false;
	    for (Map.Entry<String, Order> entry : map.entrySet()) {
	        Order order = entry.getValue();
	        if (idW.equals(order.getIdW())) {
	            found = true;
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public static String getIdFbyIdWOrderIds(String idW, Map<String, IdFVsIdWOrder> map) {
		boolean found = false;
	    for (Map.Entry<String, IdFVsIdWOrder> entry : map.entrySet()) {
	    	IdFVsIdWOrder idsOrder = entry.getValue();
	        if (idW.equals(idsOrder.getIdW())) {
	            found = true;
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public static void showMap (Map<String, Client> map){
		System.out.println("Map: ");
        for (Map.Entry<String, Client> entry : map.entrySet()) {
            String id = entry.getKey();
            Client client = entry.getValue();
            String email=client.getEmail();
            String idW = client.getIdW();
            System.out.println("idF="+id + " " + email +" idW="+ idW );
        }
	}
	
	public static void showMapProduct (Map<String, Product> map){
		System.out.println("Map: ");
        for (Map.Entry<String, Product> entry : map.entrySet()) {
            String id = entry.getKey();
            Product product = entry.getValue();
            String name=product.getName();
            String idW = product.getIdW();
            System.out.println("idF="+id + " " + name +" idW="+ idW );
        }
	}
	
	  public static Map<String, String> loadMapProductsEdf(String filePath) throws IOException {
	        File file = new File(filePath);
	        
	        if (file.exists()) {
	            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
	                return (Map<String, String>) inputStream.readObject();
	            } catch (EOFException eofException) {
	                // Manejo específico de EOFException
	                eofException.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        } else {
	            file.createNewFile();
	        }
	        
	        return new HashMap<>();  // Devuelve un nuevo HashMap si el archivo no existe
	    }
	public static Map<String, IdFVsIdWOrder> loadMapIdFVsIdWOrder(String file_dat) throws IOException {
	    File file = new File(file_dat);
	    
	    if (file.exists()) {
	        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
	            return (Map<String, IdFVsIdWOrder>) inputStream.readObject();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    } else {
	        file.createNewFile();
	    }
	    
	    return new HashMap<>();  // Devuelve un nuevo HashMap si el archivo no existe
	}
	public static Map<String, Order> loadMapOrder(String file_dat) throws IOException {
	    File file = new File(file_dat);
	    
	    if (file.exists()) {
	        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
	            return (Map<String, Order>) inputStream.readObject();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    } else {
	        file.createNewFile();
	    }
	    
	    return new HashMap<>();  // Devuelve un nuevo HashMap si el archivo no existe
	}
	public static Map<String, Product> loadMapProduct(String file_dat) throws IOException {
	    File file = new File(file_dat);
	    
	    if (file.exists()) {
	        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
	            return (Map<String, Product>) inputStream.readObject();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    } else {
	        file.createNewFile();
	    }
	    
	    return new HashMap<>();  // Devuelve un nuevo HashMap si el archivo no existe
	}
	
	public static Map<String, Client> loadMap(String file_dat) throws IOException {
	    File file = new File(file_dat);
	    
	    if (file.exists()) {
	        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
	            return (Map<String, Client>) inputStream.readObject();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    } else {
	        file.createNewFile();
	    }
	    
	    return new HashMap<>();  // Devuelve un nuevo HashMap si el archivo no existe
	}

	
	 public static void createFile(String file_dat) throws IOException {
	        File file = new File(file_dat);
	        
	        if (file.createNewFile()) {
	            System.out.println("Archivo creado: " + file.getName());
	        } else {
	            System.out.println("El archivo ya existe.");
	        }
	    }

	 public static void saveEfp(String file_dat,Map<String, String> idEfpMap ) throws Exception{
	    	File file = new File(file_dat);
	        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
	        outputStream.writeObject(idEfpMap);
	}
	 
    public static void save(String file_dat,Map<String, Client> clientsMap ) throws Exception{
    	File file = new File(file_dat);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(clientsMap);
    }

    public static void saveProduct(String file_dat, Map<String, Product> productsMap) throws Exception {
    	File file = new File(file_dat);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(productsMap);
		
	}
    
    public static void saveOrder(String file_dat, Map<String, Order> ordersMap) throws Exception {
    	File file = new File(file_dat);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(ordersMap);
	}
    
    public static void saveOrderIds(String file_dat, Map<String, IdFVsIdWOrder> idsOrdersMap) throws Exception {
    	File file = new File(file_dat);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(idsOrdersMap);
	}
    
    public static void addClientMap(Client client,Map<String,Client> map ) {
    	String id = client.getId();
        map.put(id, client);
    }
    
    public static void addProductMap(Product product, Map<String, Product> map) {
    	String id = product.getIdF();
        map.put(id, product);
	}
    
    public static void addOrderMap(Order order, Map<String, Order> map) {
    	String id = order.getIdF();
        map.put(id, order);
	}
    
    public static void addOrderIdsMap(IdFVsIdWOrder idsOrder, Map<String, IdFVsIdWOrder> map) {
    	String id = idsOrder.getIdF();
        map.put(id, idsOrder);
	}
    
    public static void addIdFMap(Order order, Map<String, Order> map) {
    	String id = order.getIdF();
        map.put(id, order);
		
	}

    public static void modifyClientMap(Client newC,Client oldC) {
            oldC.setFullName(newC.getFullName());
            oldC.setAddress(newC.getAddress());
            oldC.setCity(newC.getCity());
            oldC.setPostcode(newC.getPostcode());
            oldC.setPhone(newC.getPhone());
            oldC.setEmail(newC.getEmail());
    }
    
    public static void modifyProductMap(Product newProduct, Product oldProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setRegularPrice(newProduct.getRegularPrice());
        oldProduct.setSalePrice(newProduct.getSalePrice());
        oldProduct.setStockActual(newProduct.getStockActual());
        oldProduct.setShortDescription(newProduct.getShortDescription());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setIdW(newProduct.getIdW());
    }
    
    public static void modifyOrderMap(Order newOrder, Order oldOrder) {
        oldOrder.setIdW(newOrder.getIdW());
        oldOrder.setIdF(newOrder.getIdF());
        oldOrder.setStatus(newOrder.getStatus());
        oldOrder.setCurrency(newOrder.getCurrency());
        oldOrder.setDateCreated(newOrder.getDateCreated());
        oldOrder.setDateModified(newOrder.getDateModified());
        oldOrder.setShippingTotal(newOrder.getShippingTotal());
        oldOrder.setShippingTax(newOrder.getShippingTax());
        oldOrder.setCartTax(newOrder.getCartTax());
        oldOrder.setTotal(newOrder.getTotal());
        oldOrder.setTotalTax(newOrder.getTotalTax());
        oldOrder.setCustomerId(newOrder.getCustomerId());
        oldOrder.setPaymentMethod(newOrder.getPaymentMethod());
        oldOrder.setPaymentMethodTitle(newOrder.getPaymentMethodTitle());
        oldOrder.setCurrencySymbol(newOrder.getCurrencySymbol());

        // Modificar objetos anidados
        modifyBillingMap(newOrder.getBilling(), oldOrder.getBilling());
        modifyShippingMap(newOrder.getShipping(), oldOrder.getShipping());

        // Puedes implementar métodos similares para otros objetos anidados si es necesario
    }

    // Método para modificar Billing
    private static void modifyBillingMap(Billing newBilling, Billing oldBilling) {
        oldBilling.setFirstName(newBilling.getFirstName());
        oldBilling.setLastName(newBilling.getLastName());
        oldBilling.setCompany(newBilling.getCompany());
        oldBilling.setAddress1(newBilling.getAddress1());
        oldBilling.setCity(newBilling.getCity());
        oldBilling.setState(newBilling.getState());
        oldBilling.setPostcode(newBilling.getPostcode());
        oldBilling.setCountry(newBilling.getCountry());
        oldBilling.setEmail(newBilling.getEmail());
        oldBilling.setPhone(newBilling.getPhone());
    }

    // Método para modificar Shipping
    private static void modifyShippingMap(Shipping newShipping, Shipping oldShipping) {
        oldShipping.setFirstName(newShipping.getFirstName());
        oldShipping.setLastName(newShipping.getLastName());
        oldShipping.setCompany(newShipping.getCompany());
        oldShipping.setAddress1(newShipping.getAddress1());
        oldShipping.setCity(newShipping.getCity());
        oldShipping.setState(newShipping.getState());
        oldShipping.setPostcode(newShipping.getPostcode());
        oldShipping.setCountry(newShipping.getCountry());
        oldShipping.setPhone(newShipping.getPhone());
    }



    public static Client getClientById(String id,Map<String,Client> map) {
        return map.get(id);
    }
    public static Product getProductById(String id,Map<String,Product> map) {
        return map.get(id);
    }
    public static Order getOrderById(String id,Map<String,Order> map) {
        return map.get(id);
    }
    
    public static void deleteAllMap (Map<String,Client> map ) {
    	map.clear();
    }
    
    public static void deleteClientMap (Client client,Map<String,Client> map ) {
    	String id = client.getId();
    	map.remove(id);
    }

	

	
}

