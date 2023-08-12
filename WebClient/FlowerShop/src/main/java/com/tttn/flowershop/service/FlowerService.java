package com.tttn.flowershop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.Category;
import com.tttn.flowershop.model.Color;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowerService {

  public static List<Color> getListColors() {
    List<Color> list = new ArrayList<>();
    try {
      JsonObject jsonObject = new JsonObject();

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetALLColor, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          list = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<Color>>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static List<Category> getListCategories() {
    List<Category> list = new ArrayList<>();
    try {
      JsonObject jsonObject = new JsonObject();

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetAllCategory, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          list = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<Category>>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static int  addFlower(Flower flower) throws Exception{
	  
    int idFlower = Const.Flower.idNoExistFlower;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", flower.getName());
    jsonObject.addProperty("price", flower.getPrice());
    jsonObject.addProperty("contents", flower.getContents());
    jsonObject.addProperty("discount", flower.getDiscount());
    jsonObject.addProperty("listImage", String.join(",", flower.getImages()));
    jsonObject.addProperty("listColor", String.join(",", flower.getColors()));
    jsonObject.addProperty("listCategory", String.join(",", flower.getCategories()));
    jsonObject.addProperty("quantity_in", flower.getQuantity());

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Product,ConstAPI.APIProduct.PostFlower,requestBody);

    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idFlower = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idFlower;
  }
  public static int  updateFlower(Flower flower) throws Exception{
    int idFlower = Const.Flower.idExistFlower;
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", flower.getId());
    jsonObject.addProperty("name", flower.getName());
    jsonObject.addProperty("price", flower.getPrice());
    jsonObject.addProperty("contents", flower.getContents());
    jsonObject.addProperty("discount", flower.getDiscount());
    jsonObject.addProperty("listImage", String.join(",", flower.getImages()));
 ;

    String requestBody = Global.gson.toJson(jsonObject);
    String body = CallApiService.callAPI(ConstAPI.Service_API_Product,ConstAPI.APIProduct.UpdateFlower,requestBody);
    
    if (body != null){
      JsonNode node = Global.objectMapper.readTree(body);
      if (node.get("responseCode").intValue() == 0) {
        idFlower = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
      }
    }
    return idFlower;
  }
  
  public static List<Flower> getListFlower() {
    List<Flower> list = new ArrayList<>();
    try {
      JsonObject jsonObject = new JsonObject();

      String requestBody = Global.gson.toJson(jsonObject);
      
      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetAllFlower, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          list = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<Flower>>(){}));
          list.forEach(flower -> flower.setImages(Arrays.asList(flower.getImagesName().split(","))));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return list;
  }

  public static Flower getFlowerById(int id) {
    Flower flower = new Flower();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("id", id);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetFlowerById, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          flower = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Flower>(){}));
          flower.setImages(Arrays.asList(flower.getImagesName().split(",")));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return flower;
  }
  // search
  public static List<Flower> getFlowerByKey(String text) {
    List<Flower> list = new ArrayList<>();
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("key", text);
      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.GetListFlowerByKey, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          list = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<Flower>>(){}));
          list.forEach(flower -> flower.setImages(Arrays.asList(flower.getImagesName().split(","))));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public static Object update(Flower flowerEdit) {
    return new Object();
  }

  public static int delete(int id) {
	  int check =  Const.Flower.deleteFalse;
	    try {
	      
	      JsonObject jsonObject = new JsonObject();
	      jsonObject.addProperty("id", id);
	      String requestBody = Global.gson.toJson(jsonObject);
	      String body = CallApiService.callAPI(ConstAPI.Service_API_Product, ConstAPI.APIProduct.BlockAndUnblockFlower, requestBody);

	      if (body != null){
	          JsonNode node = Global.objectMapper.readTree(body);
	          if (node.get("responseCode").intValue() == 0) {
	            check = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
	          }
	          
	        }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return check;
	    
  }


}
