package com.tttn.api.repository;

import com.tttn.api.configs.PgDBConfig;
import com.tttn.api.dbresult.DBResult;
import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.dbresult.RGetPaymentTransactionByIdTrans;
import com.tttn.api.entities.request.GetPaymentTransactionByIdTransPayLoad;
import com.tttn.api.entities.request.PostPaymentTransactionPayLoad;

import java.sql.*;

import static java.sql.Types.NULL;

public class PaymentTransactionRepository {
    private static PaymentTransactionRepository paymentTransactionRepository = null;

    public static PaymentTransactionRepository ins() {
        if (paymentTransactionRepository == null) {
            paymentTransactionRepository = new PaymentTransactionRepository();
        }
        return paymentTransactionRepository;
    }

    public static RGetPaymentTransactionByIdTrans getPaymentTransactionByIdTrans(GetPaymentTransactionByIdTransPayLoad request) throws Exception {
        RGetPaymentTransactionByIdTrans result = new RGetPaymentTransactionByIdTrans();
        String callString = "{ ? = call dbo.sp_PaymentTransaction_GetByIdTrans(?) }";
        try (Connection connection = PgDBConfig.getDbConn().getConnection();
             CallableStatement proc = connection.prepareCall(callString);) {
            proc.registerOutParameter(1, Types.OTHER);
            proc.setInt(2, request.getIdTrans());

            proc.execute();
            ResultSet resultSet = proc.getResultSet();
            while (resultSet.next()) {
                RGetPaymentTransactionByIdTrans obj = new RGetPaymentTransactionByIdTrans();
                DBResult.fillData(obj, resultSet);
                result = obj;
            }
            connection.commit();
        }

        return result.getId() == null ? null : result;
    }

    public static void create(PostPaymentTransactionPayLoad request) throws Exception {

        String callString = "{ call dbo.sp_PaymentTransaction_add(?,?,?,?,?) }";
        try (Connection connection = PgDBConfig.getDbConn().getConnection();
             CallableStatement proc = connection.prepareCall(callString);) {

            proc.setInt(1, request.getIdTrans());
            proc.setBigDecimal(2, request.getPointUsed());
            proc.setInt(3, request.getIdGiftVoucher());
            proc.setBigDecimal(4, request.getAmount());
            proc.setInt(5, request.getIdPaymentType());
            proc.execute();
            connection.commit();

        }
    }
}

