package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.*;
import com.tttn.api.entities.request.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class TransRepository {

  private static TransRepository transRepository = null;

  public static TransRepository ins() {
    if (transRepository == null) {
      transRepository = new TransRepository();
    }
    return transRepository;
  }

  public static int create(PostTransactionPayLoad request, Connection connection) throws Exception {
    int id = -1;
    String callString = "{call dbo.sp_Transaction_add(?,?,?,?,?,?,?,?,?) }";
    CallableStatement proc = connection.prepareCall(callString);
    proc.setInt(1, request.getUserID());
    proc.setString(2, request.getRecipientName());
    proc.setString(3, request.getRecipientEmail());
    proc.setString(4, request.getRecipientPhone());
    proc.setString(5, request.getRecipientAddress());
    proc.setBigDecimal(6, request.getAmount());
    proc.setString(7, request.getMessage());
    proc.setString(8, request.getNote());
    proc.registerOutParameter(9, Types.INTEGER);
    proc.execute();
    id = proc.getInt(9) != NULL ? proc.getInt(9) : id;

    return id;
  }

    public static List<RGetTransaction> getAllTransaction() throws Exception {
      List<RGetTransaction> result = new ArrayList<>();

      String callString = "{ ? = call dbo.sp_Transaction_GetAll() }";
      try (Connection connection = PgDBConfig.getDbConn().getConnection();
           CallableStatement proc = connection.prepareCall(callString);) {
        proc.registerOutParameter(1, Types.OTHER);
        proc.execute();
        ResultSet resultSet = proc.getResultSet();
        while (resultSet.next()) {
          RGetTransaction obj = new RGetTransaction();
          DBResult.fillData(obj, resultSet);
          result.add(obj);
        }
        connection.commit();
      }
      return result;
    }

    public static void accept(UpdateTransactionPayload request) {
      String callString = "{call dbo.sp_Transaction_accept (?) }";
      try (Connection connection = PgDBConfig.getDbConn().getConnection();
           CallableStatement proc = connection.prepareCall(callString);) {
        proc.setInt(1, request.getId());
        proc.execute();
        connection.commit();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

  public static void cancel(UpdateTransactionPayload request) {
    String callString = "{call dbo.sp_Transaction_cancel (?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getId());
      proc.execute();
      connection.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static RGetTransaction getTransById(GetTransactionByIdPayLoad request) throws Exception {
    RGetTransaction rGetTransaction = new RGetTransaction();

    String callString = "{ ? = call dbo.sp_Transaction_GetById(?)}";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setInt(2, request.getId());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetTransaction obj = new RGetTransaction();
        DBResult.fillData(obj, resultSet);
        rGetTransaction = obj;
      }
      connection.commit();
    }
    return rGetTransaction.getId() == null ? null : rGetTransaction;
  }

    public static List<RGetAllTransactionByDate> getAllTransactionByDate(GetAllTransactionByDatePayLoad request) throws Exception {
      List<RGetAllTransactionByDate> listTrans = new ArrayList<>();
      String callString = "{  call dbo.sp_Transaction_Report_GetAllByDate(?,?) }";
      try (Connection connection = PgDBConfig.getDbConn().getConnection();
           CallableStatement proc = connection.prepareCall(callString);) {

        proc.setString(1, (request.getFrom()));
        proc.setString(2,(request.getTo()));
        proc.execute();
        ResultSet resultSet = proc.getResultSet();
        while (resultSet.next()) {
          RGetAllTransactionByDate obj = new RGetAllTransactionByDate();
          DBResult.fillData(obj, resultSet);
          listTrans.add(obj);
        }
        connection.commit();
      }

      return listTrans;
    }
}

//  public static void update(UpdateCartPayLoad request) throws Exception {
//    String callString = "{call dbo.sp_ShopCart_update(?,?,?,?,?,?,?) }";
//    try (Connection connection = PgDBConfig.getDbConn().getConnection();
//         CallableStatement proc = connection.prepareCall(callString);) {
//      proc.setInt(1, request.getId());
//      proc.setInt(2, request.getUserId());
//      proc.setInt(3, request.getIdFlower());
//      proc.setInt(4, request.getQuantity());
//      proc.setBigDecimal(5, request.getAmount());
//      proc.setBoolean(6, request.getIsOrdered());
//      proc.setBoolean(7, request.getIsDeleted());
//      proc.execute();
//      connection.commit();
//    }
//  }
//
//  public static List<RGetCart> getCartsByUserId(GetCartsByUserIdPayload request) throws Exception {
//    List<RGetCart> result = new ArrayList<>();
//    String callString = "{ ? = call dbo.sp_ShopCart_GetByUser(?) }";
//    try (Connection connection = PgDBConfig.getDbConn().getConnection();
//         CallableStatement proc = connection.prepareCall(callString);) {
//      proc.registerOutParameter(1, Types.OTHER);
//      proc.setInt(2, request.getUserId());
//      proc.execute();
//      ResultSet resultSet = proc.getResultSet();
//      while (resultSet.next()) {
//        RGetCart obj = new RGetCart();
//        DBResult.fillData(obj, resultSet);
//        result.add(obj);
//      }
//      connection.commit();
//    }
//    return result;
//  }
//
//  public static RGetCart getCartByUserFlo(GetCartByUserFloPayload request) throws Exception {
//    RGetCart result = new RGetCart();
//
//    String callString = "{ ? = call dbo.sp_ShopCart_GetByUserFlo(?,?) }";
//    try (Connection connection = PgDBConfig.getDbConn().getConnection();
//         CallableStatement proc = connection.prepareCall(callString);) {
//      proc.registerOutParameter(1, Types.OTHER);
//      proc.setInt(2,request.getUserId());
//      proc.setInt(3,request.getFlowerId());
//      proc.execute();
//      ResultSet resultSet = proc.getResultSet();
//      while (resultSet.next()) {
//        RGetCart obj = new RGetCart();
//        DBResult.fillData(obj, resultSet);
//        result = obj;
//      }
//      connection.commit();
//    }
//    return result.getId() == null ? null : result;
//  }
//
//  public static RGetCart getCartById(GetCartByIdPayload request) throws Exception {
//    RGetCart result = new RGetCart();
//
//    String callString = "{ ? = call dbo.sp_ShopCart_GetCartById(?) }";
//    try (Connection connection = PgDBConfig.getDbConn().getConnection();
//         CallableStatement proc = connection.prepareCall(callString);) {
//      proc.registerOutParameter(1, Types.OTHER);
//      proc.setInt(2, request.getId());
//      proc.execute();
//      ResultSet resultSet = proc.getResultSet();
//      while (resultSet.next()) {
//        RGetCart obj = new RGetCart();
//        DBResult.fillData(obj, resultSet);
//        result = obj;
//      }
//      connection.commit();
//    }
//    return result.getId() == null ? null : result;
//  }


