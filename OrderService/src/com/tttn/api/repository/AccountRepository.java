package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetUser;
import com.tttn.api.entities.request.CheckLoginPayload;
import com.tttn.api.entities.request.GetUserByKeyPayload;
import com.tttn.api.entities.request.PostAccountPayLoad;
import com.tttn.api.entities.request.UpdateAccountPayLoad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import static java.sql.Types.NULL;

public class AccountRepository {

  private static AccountRepository accountRepository = null;

  public static AccountRepository ins() {
    if (accountRepository == null) {
      accountRepository = new AccountRepository();
    }
    return accountRepository;
  }

  public static int createAccount(PostAccountPayLoad request) throws Exception {
    int userId = -1;
    String callString = "{call dbo.sp_UserAdd(?,?,?,?,?,?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.setString(1, request.getFullName());
      proc.setString(2, request.getEmail());
      proc.setString(3, request.getPhone());
      proc.setString(4, request.getAddress());
      proc.setString(5, request.getPassword());
      proc.setInt(6, request.getIdRole());
      proc.setLong(7, request.getLoyaltyPoint());
      proc.registerOutParameter(8, Types.INTEGER);
      proc.execute();
      userId = proc.getInt(8) != NULL ? proc.getInt(8) : userId;
      connection.commit();
    }
    return userId;
  }

  public static void updateAccount(UpdateAccountPayLoad request) throws Exception {
    String callString = "{call dbo.sp_UserUpdate(?,?,?,?,?,?,?,?,?) }";
    try (
            Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);)
    {
      proc.setInt(1, request.getId());
      proc.setString(2, request.getFullName());
      proc.setString(3, request.getEmail());
      proc.setString(4, request.getPhone());
      proc.setString(5, request.getAddress());
      proc.setString(6, request.getPassword());
      proc.setBoolean(7, request.getIsDeleted());
      proc.setInt(8, request.getIdRole());
      proc.setLong(9, request.getLoyaltyPoint());
      proc.execute();
      connection.commit();
    }
  }

  public static Integer checkLogin(CheckLoginPayload request) throws Exception {
    int userId = -1;
    String callString = "{call dbo.sp_Login(?,?,?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {

      proc.setString(1,request.getLoginName());
      proc.setString(2, request.getPassword());
      proc.registerOutParameter(3, Types.INTEGER);
      proc.execute();
      userId = proc.getInt(3) != 0 ? proc.getInt(3) : userId;
      connection.commit();
    }
    return userId;
  }

  public static RGetUser getUserByKey(GetUserByKeyPayload request) throws Exception {
    RGetUser result = new RGetUser();
    String callString = "{ ? = call dbo.sp_GetUser(?) }";
    try (Connection connection = PgDBConfig.getDbConn().getConnection();
         CallableStatement proc = connection.prepareCall(callString);) {
      proc.registerOutParameter(1, Types.OTHER);
      proc.setString(2, request.getKey().trim());
      proc.execute();
      ResultSet resultSet = proc.getResultSet();
      while (resultSet.next()) {
        RGetUser obj = new RGetUser();
        DBResult.fillData(obj, resultSet);
        result = obj;
      }
      connection.commit();
    }
    return result.getId() == null ? null : result;
  }
}
