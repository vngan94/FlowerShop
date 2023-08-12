package com.tttn.api.service;

import com.tttn.api.DTO.Order;
import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.RGetAllTransactionByDate;
import com.tttn.api.dbresult.RGetTransaction;
import com.tttn.api.entities.request.*;
import com.tttn.api.repository.OrderRepository;
import com.tttn.api.repository.TransRepository;

import java.sql.Connection;
import java.util.List;

public class TransService {

  private static TransService transService = null;

  public static TransService ins() {
    if (transService == null) {
      transService = new TransService();
    }
    return transService;
  }

  public static Integer create(PostTransactionPayLoad request) {
    int id = -1;
    try {
      Connection connection = PgDBConfig.getDbConn().getConnection();
      id = TransRepository.create(request, connection);
//      for (Order order : request.getOrderList()){
//        order.setTransactionID(id);
//        OrderRepository.create(order, connection);
//      }
      connection.commit();
    } catch (Exception e){
      e.printStackTrace();
      id = -1;
    }
    return id;
  }

  public static List<RGetTransaction> getAllTransaction() throws Exception {
    return TransRepository.getAllTransaction();
  }

    public static void accept(UpdateTransactionPayload request) {
      TransRepository.accept(request);
    }
  public static void cancel(UpdateTransactionPayload request) {
    TransRepository.cancel(request);
  }

  public static RGetTransaction getTransactionById(GetTransactionByIdPayLoad request) throws Exception {
    return TransRepository.getTransById(request);
  }



  public static List<RGetAllTransactionByDate> getAllTransactionByDate(GetAllTransactionByDatePayLoad request) throws Exception {
    return TransRepository.getAllTransactionByDate(request);
  }
}
