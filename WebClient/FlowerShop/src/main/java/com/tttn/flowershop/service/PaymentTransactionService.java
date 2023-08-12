package com.tttn.flowershop.service;

import java.util.Arrays;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.PaymentTransaction;
import com.tttn.flowershop.utils.Global;

public class PaymentTransactionService {
	public static PaymentTransaction getPaymentTransactionByIdTrans(int idTrans) {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		try {
		      JsonObject jsonObject = new JsonObject();
		      jsonObject.addProperty("idTrans", idTrans);

		      String requestBody = Global.gson.toJson(jsonObject);
		      String body = CallApiService.callAPI(ConstAPI.Service_API_Payment, ConstAPI.APIPayment.getPaymentTransactionByIdTrans, requestBody);

		      if (body != null){
		        JsonNode node = Global.objectMapper.readTree(body);
		        if (node.get("responseCode").intValue() == 0) {
		        	paymentTransaction = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<PaymentTransaction>(){}));
		        	
		        }
		      }

		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		return paymentTransaction;
	}

	public static void create(PaymentTransaction paymentTransaction) {
		// TODO Auto-generated method stub
		System.out.print("check request " + paymentTransaction.toString());
		try {
		      JsonObject jsonObject = new JsonObject();
		      jsonObject.addProperty("idTrans", paymentTransaction.getIdTrans());
		      jsonObject.addProperty("pointUsed", paymentTransaction.getPointUsed());
		      jsonObject.addProperty("idGiftVoucher", paymentTransaction.getIdGiftVoucher());
		      jsonObject.addProperty("amount", paymentTransaction.getAmount());
		      jsonObject.addProperty("idPaymentType", paymentTransaction.getIdPaymentType());
		      
		      
		      String requestBody = Global.gson.toJson(jsonObject);
		      String body = CallApiService.callAPI(ConstAPI.Service_API_Payment, ConstAPI.APIPayment.PostPaymentTransaction, requestBody);

		      if (body != null){
		        JsonNode node = Global.objectMapper.readTree(body);
		        if (node.get("responseCode").intValue() == 0) {
		        	int check = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
		        	
		        }
		      }

		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
	}

}
