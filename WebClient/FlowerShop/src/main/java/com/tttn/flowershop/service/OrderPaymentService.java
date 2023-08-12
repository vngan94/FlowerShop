package com.tttn.flowershop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.*;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;


public class OrderPaymentService {

  public static GiftVoucher getGiftVoucherByKey(String code) {
    GiftVoucher giftVoucher = new GiftVoucher();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("key", code);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Payment, ConstAPI.APIPayment.GetGiftVoucherByKey, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          giftVoucher = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<GiftVoucher>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return giftVoucher.getId() == null ? null : giftVoucher;
  }

  public static int updateGiftVoucher(GiftVoucher giftVoucher) throws Exception {
    int result = -1;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", giftVoucher.getId());
    jsonObject.addProperty("isUsed", giftVoucher.getIsUsed());
    jsonObject.addProperty("isDeleted", giftVoucher.getIsDeleted() != null ? giftVoucher.getIsDeleted() : false);

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Payment, ConstAPI.APIPayment.UpdateGiftVoucher, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      result = node.get("responseCode").intValue();
    }
    return result;
  }

  public static Integer addGiftVoucher(GiftVoucher giftVoucher) throws Exception {
    int id = Const.idNoExist;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("title", giftVoucher.getTitle());
    jsonObject.addProperty("value", giftVoucher.getValue());
    
    System.out.println(giftVoucher.toString());
    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Payment, ConstAPI.APIPayment.PostGiftVoucher, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        id = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    System.out.print("check id PMH " +id  );
    return id;
  }

  public static int createTransaction(Transaction trans) throws Exception {
    int idTrans = Const.idNoExist;
    String requestBody = Global.gson.toJson(trans);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.PostTransaction, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idTrans = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idTrans;
  }

  public static Integer addStorage(Storage storage) throws Exception {
    int idStorage = Const.idNoExist;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("idFlower", storage.getIdFlower());
    jsonObject.addProperty("quantity_in", storage.getQuantity_in());
    jsonObject.addProperty("detail", storage.getDetail());
    jsonObject.addProperty("price", storage.getPrice());

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.AddStorage, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idStorage = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idStorage;
    
  }
  public static Integer addStorage_CancelOrder(Storage storage) throws Exception {
	    int idStorage = Const.idNoExist;
	    JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("idFlower", storage.getIdFlower());
	    jsonObject.addProperty("quantity_in", storage.getQuantity_in());
	    jsonObject.addProperty("detail", storage.getDetail());
	    jsonObject.addProperty("price", storage.getPrice());
	    System.out.println("check " + storage.toString());

	    String requestBody = Global.gson.toJson(jsonObject);
	    String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.AddStorage_CancelOrder, requestBody);

	    if (body != null){
	      JsonNode node = Global.objectMapper.readTree(body);
	      if (node.get("responseCode").intValue() == 0) {
	        idStorage = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
	      }
	    }
	    return idStorage;
  }

public static Integer createOrder(Order order) throws Exception {
	int idOrder = Const.idNoExist;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("transactionID", order.getTransactionID());
    jsonObject.addProperty("idFlower", order.getIdFlower());
    jsonObject.addProperty("quantity", order.getQuantity());
    jsonObject.addProperty("amount", order.getAmount());


    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.PostOrder, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idOrder = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idOrder;
}
}
