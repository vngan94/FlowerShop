package com.tttn.flowershop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.LoginModel;
import com.tttn.flowershop.model.User;
import com.tttn.flowershop.utils.Global;

import static com.tttn.flowershop.utils.Const.User.idNoExistUser;


public class UserService {
  public static Integer checkLogin(LoginModel login){
    int idUser = idNoExistUser;
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("loginName", login.getLoginName().trim());
      jsonObject.addProperty("password", login.getPassword().trim());

      String requestBody = Global.gson.toJson(jsonObject);
     
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order,ConstAPI.APIOrder.Checklogin,requestBody);
//      System.out.println("check" + body);
      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          idUser = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return idUser;
  }

  public static User getUserByKey(String key){
    User user = null;
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("key", key);

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetUserByKey, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          user = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<User>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public static int addUser(User user){
    int idUser = idNoExistUser;
    try {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("fullName", user.getFullName());
      jsonObject.addProperty("email", user.getEmail());
      jsonObject.addProperty("phone", user.getPhone());
      jsonObject.addProperty("address", user.getAddress());
      jsonObject.addProperty("password", user.getPassword());
      jsonObject.addProperty("idRole", user.getIdRole());
      jsonObject.addProperty("loyaltyPoint", user.getLoyaltyPoint());

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.AddUser, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        if (node.get("responseCode").intValue() == 0) {
          idUser = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<Integer>(){}));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return idUser;
  }

  public static int updateUser(User user) throws Exception {
    int result = -1;
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("id", user.getId());
      jsonObject.addProperty("fullName", user.getFullName());
      jsonObject.addProperty("email", user.getEmail());
      jsonObject.addProperty("phone", user.getPhone());
      jsonObject.addProperty("address", user.getAddress());
      jsonObject.addProperty("password", user.getPassword());
      jsonObject.addProperty("isDeleted", user.getIsDeleted());
      jsonObject.addProperty("idRole", user.getIdRole());
      jsonObject.addProperty("loyaltyPoint", user.getLoyaltyPoint());

      String requestBody = Global.gson.toJson(jsonObject);
      String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.UpdateUser, requestBody);

      if (body != null){
        JsonNode node = Global.objectMapper.readTree(body);
        result = node.get("responseCode").intValue();
      }
    return result;
  }
}
