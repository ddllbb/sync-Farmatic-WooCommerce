package com.main;

import java.net.http.HttpResponse;
import java.util.Map;

import com.connections.FileMapManager;
import com.connections.HttpApi;
import com.dataConnection.Files;
import com.dataConnection.FuenteCarrantona;
import com.dataConnection.LoginClient;
import com.dataConnection.Miro;
import com.dataConnection.Url;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.product.MatchProduct;
import com.product.Product;
import com.product.Products;
public class Tests {

	public static void main(String[] args) throws Exception {
        
		System.out.println(FuenteCarrantona.LOGIN.consumerKey);
		System.out.println(Miro.LOGIN.consumerKey);
		
		
		
}}