package com.tttn.flowershop.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.tttn.flowershop.apiUtils.CallApiService;
import com.tttn.flowershop.apiUtils.ConstAPI;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.Order;
import com.tttn.flowershop.model.Transaction;
import com.tttn.flowershop.utils.Global;

public class OrderService {

	public static List<Order> getOrderByIdTrans(int idTrans) throws Exception {
		List<Order> listOrder = new ArrayList<>();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", idTrans);

		String requestBody = Global.gson.toJson(jsonObject);

		String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetOrderByIdTrans,
				requestBody);
		if (body != null) {

			JsonNode node = Global.objectMapper.readTree(body);
			if (node.get("responseCode").intValue() == 0) {

				listOrder = (Global.objectMapper.convertValue(node.get("responseData").get("data"),
						new TypeReference<List<Order>>() {
						}));

			}
		}

		return listOrder;
	}

	public static List<Order> getOrderTransReport(String from, String to) throws Exception {
		List<Order> listOrder = new ArrayList<>();
		
		JsonObject jsonObject = new JsonObject();
	     jsonObject.addProperty("from", from);
	     jsonObject.addProperty("to", to);

	     String requestBody = Global.gson.toJson(jsonObject);
	     
		String body = CallApiService.callAPI(ConstAPI.Service_API_Order, ConstAPI.APIOrder.GetOrderTransReport , requestBody);
		if (body != null) {
			
			JsonNode node = Global.objectMapper.readTree(body);
			if (node.get("responseCode").intValue() == 0) {
				listOrder = (Global.objectMapper.convertValue(node.get("responseData").get("data"), new TypeReference<List<Order>>(){}));
			}
		return listOrder;
		}
		return null;
	}
}

