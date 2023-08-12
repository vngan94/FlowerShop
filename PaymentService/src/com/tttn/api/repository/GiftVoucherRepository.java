package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.entities.request.GetGiftVoucherByKeyPayload;
import com.tttn.api.entities.request.PostGiftVoucherPayLoad;
import com.tttn.api.entities.request.UpdateGiftVoucherPayLoad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import static java.sql.Types.NULL;

public class GiftVoucherRepository {

  private static GiftVoucherRepository giftVoucherRepository = null;

  public static GiftVoucherRepository ins() {
    if (giftVoucherRepository == null) {
      giftVoucherRepository = new GiftVoucherRepository();
    }
    return giftVoucherRepository;
  }


  public static RGetGiftVoucher getGiftVoucherByKey(GetGiftVoucherByKeyPayload request) throws Exception {
    RGetGiftVoucher result = new RGetGiftVoucher();
    String callString = "{ ? = call dbo.sp_GiftVoucher_GetByKey(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setString(2, request.getKey().trim());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetGiftVoucher obj = new RGetGiftVoucher();
        DBResult.fillData(obj, resultSet);
        result = obj;
      }
      connection.commit();
    }
    return result.getId() == null ? null : result;
  }

  public static int create(PostGiftVoucherPayLoad request) throws Exception{
    int id = -1;
    String callString = "{call dbo.sp_GiftVoucher_add(?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setString(1, request.getTitle());
      proc.setString(2, request.getValue());
      proc.registerOutParameter(3, Types.INTEGER);
      proc.execute();
      id = proc.getInt(3) != NULL ? proc.getInt(3) : id;
      connection.commit();
    }
    return id;
  }

  public static void update(UpdateGiftVoucherPayLoad request) throws Exception{
    String callString = "{call dbo.sp_GiftVoucher_update(?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setInt(1, request.getId());
      proc.setBoolean(2, request.getisUsed());
      proc.setBoolean(3, request.getIsDeleted());
      proc.execute();
      connection.commit();
    }
  }
}
