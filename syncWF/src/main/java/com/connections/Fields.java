package com.connections;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;


public class Fields {
	
		//Products
		public static String limpiaHTML(String jsonDescripcion) {
			// Pasa el contenido HTML a Jsoup para limpiar y parsear las etiquetas HTML
			Document doc = Jsoup.parse(jsonDescripcion);
			// Crea una instancia de Cleaner con la Safelist relajada
	        Cleaner cleaner = new Cleaner(Safelist.relaxed());
	        // Limpia el documento utilizando el Cleaner
	        Document cleanDoc = cleaner.clean(doc);
	        // Obtiene el texto sin etiquetas HTML
	        String descripcionLimpia = cleanDoc.body().text();
			return descripcionLimpia;
		}

		
		public static int pagination(HttpResponse<String> response) {
			Map<String, List<String>> headers = response.headers().map();
			List<String> totalElementsHeader = headers.get("X-WP-Total");
			int totalElements = Integer.parseInt(totalElementsHeader.get(0));
			int pages = (totalElements / 100) + 1;
			return pages;
		}
}