package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetCart;
import com.tttn.api.entities.request.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.sql.Types.NULL;

public class CartRepository {

  private static CartRepository cartRepository = null;

  public static CartRepository ins() {
    if (cartRepository == null) {
      cartRepository = new CartRepository();
    }
    return cartRepository;
  }

  public static int create(PostCartPayLoad request) throws Exception {
    int cartId = -1;
    String callString = "{call dbo.sp_ShopCart_add(?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getUserId());
      proc.setInt(2, request.getIdFlower());
      proc.setInt(3, request.getQuantity());
      proc.setBigDecimal(4, request.getAmount());
      proc.registerOutParameter(5, Types.INTEGER);
      proc.execute();
      cartId = proc.getInt(5) != NULL ? proc.getInt(5) : cartId;
      connection.commit();
    }
    return cartId;
  }

  public static void update(UpdateCartPayLoad request) throws Exception {
    String callString = "{call dbo.sp_ShopCart_update(?,?,?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getId());
      proc.setInt(2, request.getUserId());
      proc.setInt(3, request.getIdFlower());
      proc.setInt(4, request.getQuantity());
      proc.setBigDecimal(5, request.getAmount());
      proc.setBoolean(6, request.getIsOrdered());
      proc.setBoolean(7, request.getIsDeleted());
      proc.execute();
      connection.commit();
    }
  }

  public static List<RGetCart> getCartsByUserId(GetCartsByUserIdPayload request) throws Exception {
    List<RGetCart> result = new ArrayList<>();
    String callString = "{ ? = call dbo.sp_ShopCart_GetByUser(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setInt(2, request.getUserId());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetCart obj = new RGetCart();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return result;
  }

  public static RGetCart getCartByUserFlo(GetCartByUserFloPayload request) throws Exception {
    RGetCart result = new RGetCart();

    String callString = "{ ? = call dbo.sp_ShopCart_GetByUserFlo(?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setInt(2,request.getUserId());
      proc.setInt(3,request.getFlowerId());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetCart obj = new RGetCart();
        DBResult.fillData(obj, resultSet);
        result = obj;
      }
      connection.commit();
    }
    return result.getId() == null ? null : result;
  }

  public static RGetCart getCartById(GetCartByIdPayload request) throws Exception {
    RGetCart result = new RGetCart();

    String callString = "{ ? = call dbo.sp_ShopCart_GetCartById(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setInt(2, request.getId());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetCart obj = new RGetCart();
        DBResult.fillData(obj, resultSet);
        result = obj;
      }
      connection.commit();
    }
    return result.getId() == null ? null : result;
  }
}
