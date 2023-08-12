package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetStorage;
import com.tttn.api.entities.request.GetStorageByFlowerPayLoad;
import com.tttn.api.entities.request.PostStoragePayLoad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import static java.sql.Types.NULL;

public class StorageRepository {
    private static StorageRepository storageRepository = null;

    public static StorageRepository ins() {
        if (storageRepository == null) {
            storageRepository = new StorageRepository();
        }
        return storageRepository;
    }



    public static Integer getStorageByFlower(GetStorageByFlowerPayLoad request) throws  Exception {

        int storageQuantity = -1;
        String callString = "{call dbo.sp_Storage_getByFlower(?,?) }";
        try (Connection connection = PgDBConfig.getDbConn().getConnection();
             CallableStatement proc = connection.prepareCall(callString);) {
            proc.setInt(1, request.getId());

            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            storageQuantity = proc.getInt(2);
            connection.commit();
        }
        System.out.println(storageQuantity + "in");
        return storageQuantity;
    }

    public static Integer addStorage_CancelOrder(PostStoragePayLoad request) throws Exception{
        System.out.println("check " + request.toString());
        int storageId = -1;
        String callString = "{call dbo.sp_Storage_Add_CancelOrder(?,?,?,?,?) }";
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
}
