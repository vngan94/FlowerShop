package com.tttn.flowershop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.ShopCart;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;

import java.util.ArrayList;
import java.util.List;

public class CartService {

  public static List<ShopCart> getCartsByUserId(Integer id){
    List<ShopCart> carts = new ArrayList<>();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("userId", id);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetCartsByUserId, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          carts = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<ShopCart>>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return carts != null ? carts : new ArrayList<>();
  }
  public static ShopCart getCartByUserFlo(int userId, int floId){
    ShopCart cart = new ShopCart();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("userId", userId);
      jsonObject.addProperty("flowerId", floId);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetCartByUserFlo, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          cart = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<ShopCart>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return cart;
  }
  public static ShopCart getCartById(int id) {
    ShopCart cart = new ShopCart();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("id", id);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetCartById, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          cart = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<ShopCart>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return cart;
  }

  public static boolean update(ShopCart cart) throws Exception {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", cart.getId());
    jsonObject.addProperty("userId", cart.getUserID());
    jsonObject.addProperty("idFlower", cart.getIdFlower());
    jsonObject.addProperty("quantity", cart.getQuantity());
    jsonObject.addProperty("amount", cart.getAmount());
    jsonObject.addProperty("isOrdered", cart.getIsOrdered());
    jsonObject.addProperty("isDeleted", cart.getIsDeleted());

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.UpdateCart, requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      return node.get("responseCode").intValue() == 0;
    }
    return false;
  }

  public static Integer create(ShopCart cart) throws Exception{
    int idCart = Const.idNoExist;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userId", cart.getUserID());
    jsonObject.addProperty("idFlower", cart.getIdFlower());
    jsonObject.addProperty("quantity", cart.getQuantity());
    jsonObject.addProperty("amount", cart.getAmount());

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Order,ConstAPI.APIOrder.CreateCart,requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idCart = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idCart;
  }

}
