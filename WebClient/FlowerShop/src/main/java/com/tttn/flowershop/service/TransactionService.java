package com.tttn.flowershop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.GetAllTransactionByDate;
import com.tttn.flowershop.model.Transaction;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;

public class TransactionService {

	public static List<Transaction> getAllTransaction() throws Exception {
		List<Transaction> listTransaction = new ArrayList<>();
		JsonObject jsonObject = new JsonObject();

		String requestBody = Global.gson.toJson(jsonObject); //
		String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetAllTransaction,
				requestBody);
		if (body != null) {

			JsonNode node = Global.objectMapper.readTree(body);
			if (node.get("responseCode").intValue() == 0) {
				listTransaction = (Global.objectMapper.convertValue(node.get("responseData").get("data"),
						new TypeReference<List<Transaction>>() {
						}));
				
			}
		}
		return listTransaction;
	}

	public static int acceptTransaction(int id) {
		int check = Const.Transaction.acceptFalse;
		try {

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", id);
			String requestBody = Global.gson.toJson(jsonObject);
			String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.AcceptTransaction,
					requestBody);

			if (body != null) {
				JsonNode node = Global.objectMapper.readTree(body);
				if (node.get("responseCode").intValue() == 0) {
					check = (Global.objectMapper.convertValue(node.get("responseData").get("data"),
							new TypeReference<Integer>() {
							}));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;

	}
	
	public static int cancelTransaction(int id) {
		int check = Const.Transaction.cancelFalse;
		try {

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", id);
			String requestBody = Global.gson.toJson(jsonObject);
			String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.CancelTransaction,
					requestBody);

			if (body != null) {
				JsonNode node = Global.objectMapper.readTree(body);
				if (node.get("responseCode").intValue() == 0) {
					check = (Global.objectMapper.convertValue(node.get("responseData").get("data"),
							new TypeReference<Integer>() {
							}));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;

	}
	
	public static Transaction getTransactionById(int id) {
		Transaction transaction = new Transaction();
	    try {
	      JsonObject jsonObject = new JsonObject();
	      jsonObject.addProperty("id", id);

	      String requestBody = Global.gson.toJson(jsonObject);
	      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetTransactionById , requestBody);

	      if (body != null){
	        JsonNode node = Global.objectMapper.readTree(body);
	        if (node.get("responseCode").intValue() == 0) {
	        	transaction = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Transaction>(){}));
	          
	        }
	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return transaction;
	}

	public static List<Transaction> getAllTransReport(String from, String to)  {
		List<Transaction> listTransaction = new ArrayList<>();
	    
	      JsonObject jsonObject = new JsonObject();
	      jsonObject.addProperty("from", from);
		     jsonObject.addProperty("to", to);
		     

	      String requestBody = Global.gson.toJson(jsonObject);
	      String body;
		try {
			body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetAllTransReport , requestBody);
		
	      if (body != null) {

				JsonNode node = Global.objectMapper.readTree(body);
				if (node.get("responseCode").intValue() == 0) {
					listTransaction = (Global.objectMapper.convertValue(node.get("responseData").get("data"),
							new TypeReference<List<Transaction>>() {
							}));
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return listTransaction;
	}
	     
}
