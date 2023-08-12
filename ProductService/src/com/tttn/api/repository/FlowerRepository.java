package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.*;
import com.tttn.api.entities.request.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class FlowerRepository {
  public static List<RGetColor> getAllColor() throws Exception {
    List<RGetColor> result = new ArrayList<>();

    String callString = "{ ? = call dbo.sp_GetAllColor() }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetColor obj = new RGetColor();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return result;
  }

  public static List<RGetCategory> getAllCategory() throws Exception {
    List<RGetCategory> result = new ArrayList<>();

    String callString = "{ ? = call dbo.sp_GetAllCategory() }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetCategory obj = new RGetCategory();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return result;
  }

  public static int addFlower(PostFlowerPayLoad request) throws Exception {

    int flowerId = -1;
    String callString = "{call dbo.sp_Flower_add(?,?,?,?,?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setString(1, request.getName());
      proc.setBigDecimal(2, request.getPrice());
      proc.setString(3, request.getContents());
      proc.setInt(4, request.getDiscount());
      proc.setString(5, request.getListImage());
      proc.setString(6, request.getListColor());
      proc.setString(7, request.getListCategory());
      proc.setInt(8, request.getQuantity_in());
      proc.registerOutParameter(9, Types.INTEGER);
      proc.execute();
      flowerId = proc.getInt(9) != NULL ? proc.getInt(9) : flowerId;
      connection.commit();
    }
    return flowerId;
  }

  public static List<RGetFlower> getAllFlower() throws Exception {
    List<RGetFlower> result = new ArrayList<>();

    String callString = "{ ? = call dbo.sp_Flower_GetAll() }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetFlower obj = new RGetFlower();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return result;
  }

  public static RGetFlower getFlowerById(GetFlowerByIdPayLoad request) throws Exception {
    RGetFlower result = new RGetFlower();

    String callString = "{ ? = call dbo.sp_Flower_GetById(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setInt(2, request.getId());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        DBResult.fillData(result, resultSet);
      }
      connection.commit();
    }
    return result;
  }

  public static List<RGetFlower> getListFlowerByKey(GetListFlowerByKeyPayLoad request) throws Exception {
    List<RGetFlower> result = new ArrayList<>();

    String callString = "{ ? = call dbo.sp_Flower_GetByKey(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setString(2, request.getKey());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetFlower obj = new RGetFlower();
        DBResult.fillData(obj, resultSet);
        result.add(obj);
      }
      connection.commit();
    }
    return result;
  }

  public static Integer addStorage(PostStoragePayLoad request) throws Exception {
    int storageId = -1;
    String callString = "{call dbo.sp_Storage_add(?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getIdFlower());
      proc.setInt(2, request.getQuantity_in());
      proc.setString(3, request.getDetail());
      proc.setBigDecimal(4, request.getPrice());
      proc.registerOutParameter(5, Types.INTEGER);
      proc.execute();
      storageId = proc.getInt(5) != NULL ? proc.getInt(5) : storageId;
      connection.commit();
    }
    return storageId;
  }

  public static Integer updateFlower(UpdateFlowerPayLoad request) throws Exception {

    String callString = "{call dbo.sp_Flower_update(?,?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setString(1, request.getName());
      proc.setBigDecimal(2, request.getPrice());
      proc.setString(3, request.getContents());
      proc.setInt(4, request.getDiscount());
      proc.setString(5, request.getListImage());
      proc.setInt(6, request.getId());
      proc.execute();

      connection.commit();
    }
    return request.getId();

  }

  public static void blockAndUnblockFlower(BlockAndUnblockPayLoad request) {
    String callString = "{call dbo.sp_Flower_block_unblock (?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getId());
      proc.execute();
      connection.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
