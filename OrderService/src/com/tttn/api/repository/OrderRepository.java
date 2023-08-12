package com.tttn.api.repository;

import com.tttn.api.DTO.Order;
import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetAllTransactionByDate;
import com.tttn.api.dbresult.RGetOrder;
import com.tttn.api.entities.request.GetAllTransactionByDatePayLoad;
import com.tttn.api.entities.request.GetTransactionByIdPayLoad;
import com.tttn.api.entities.request.PostOrderPayload;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderRepository {
  private static OrderRepository orderRepository = null;

  public static OrderRepository ins() {
    if (orderRepository == null) {
      orderRepository = new OrderRepository();
    }
    return orderRepository;
  }


  public static void create(Order order, Connection connection) throws SQLException {
    String callString = "{call dbo.sp_Order_add(?,?,?,?) }";
    CallableStatement proc = connection.prepareCall(callString);
    proc.setInt(1, order.getTransactionID());
    proc.setInt(2, order.getIdFlower());
    proc.setInt(3, order.getQuantity());
    proc.setBigDecimal(4, order.getAmount());
    proc.execute();
  }

  public static List<RGetOrder> getOrderByIdTrans(GetTransactionByIdPayLoad request) throws Exception{
    List<RGetOrder> result = new ArrayList<>();

    String callString = "{  call dbo.sp_Order_GetByIdTrans(?)}";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {

      proc.setInt(1, (request.getId()));

      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetOrder obj = new RGetOrder();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return  result;
  }

  public static void create(PostOrderPayload request) throws Exception{

    String callString = "{call dbo.sp_Order_add(?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getTransactionID());
      proc.setInt(2, request.getIdFlower());
      proc.setInt(3, request.getQuantity());
      proc.setBigDecimal(4, request.getAmount());
      proc.execute();
      connection.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public static List<RGetOrder>  getAllOrderByIdTransReport(GetAllTransactionByDatePayLoad request) throws Exception{
    List<RGetOrder> result = new ArrayList<>();


      String callString = "{  call dbo.sp_TransOrder_Report(?,?) }";
      try (Connection connection = PgDBConfig.getDbConn().getConnection();
           CallableStatement proc = connection.prepareCall(callString);) {

        proc.setString(1, (request.getFrom()));
        proc.setString(2,(request.getTo()));
        proc.execute();
        ResultSet resultSet = proc.getResultSet();
        while (resultSet.next()) {
          RGetOrder obj = new RGetOrder();
          DBResult.fillData(obj, resultSet);
          result.add(obj);
        }
        connection.commit();
      }

      return  result;
  }
}
