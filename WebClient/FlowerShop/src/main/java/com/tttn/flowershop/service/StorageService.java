package com.tttn.flowershop.service;

import java.util.Arrays;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.Storage;
import com.tttn.flowershop.utils.Global;

public class StorageService {

	public static int getStorageByFlower(int floId) {
		int quantity =-1;
	    try {
	      JsonObject jsonObject = new JsonObject();
	      jsonObject.addProperty("id", floId);

	      String requestBody = Global.gson.toJson(jsonObject);
	      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetStorageByFlower, requestBody);

	      if (body != null){
	        JsonNode node = Global.objectMapper.readTree(body);
	        if (node.get("responseCode").intValue() == 0) {
	         quantity =  (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
	         return quantity;
	        }
	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return quantity;
		// TODO Auto-generated method stub
		
	}
	

}
