package com.tttn.api.service;

import com.tttn.api.dbresult.RGetOrder;
import com.tttn.api.entities.request.GetAllTransactionByDatePayLoad;
import com.tttn.api.entities.request.GetTransactionByIdPayLoad;
import com.tttn.api.entities.request.PostOrderPayload;
import com.tttn.api.repository.OrderRepository;

import java.util.List;

public class OrderService {
    private static OrderService orderService = null;

    public static OrderService ins() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public static List<RGetOrder> getOrderByIdTrans(GetTransactionByIdPayLoad request) throws Exception {
        return OrderRepository.getOrderByIdTrans(request);
    }
    public static void create (PostOrderPayload request) throws Exception {
         OrderRepository.create(request);
    }

    public static List<RGetOrder> getAllOrderByIdTransReport(GetAllTransactionByDatePayLoad request) throws Exception {
        return OrderRepository.getAllOrderByIdTransReport(request);
    }
}
