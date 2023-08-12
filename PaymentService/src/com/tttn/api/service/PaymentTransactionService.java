package com.tttn.api.service;

import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.dbresult.RGetPaymentTransactionByIdTrans;
import com.tttn.api.entities.request.GetPaymentTransactionByIdTransPayLoad;
import com.tttn.api.entities.request.PostPaymentTransactionPayLoad;
import com.tttn.api.repository.GiftVoucherRepository;
import com.tttn.api.repository.PaymentTransactionRepository;

public class PaymentTransactionService {
    private static PaymentTransactionService paymentTransactionService = null;

    public static PaymentTransactionService ins() {
        if (paymentTransactionService == null) {
            paymentTransactionService = new PaymentTransactionService();
        }
        return paymentTransactionService;
    }


    public static RGetPaymentTransactionByIdTrans getPaymentTransactionByIdTrans(GetPaymentTransactionByIdTransPayLoad request)  throws Exception {

        return PaymentTransactionRepository.getPaymentTransactionByIdTrans(request);
    }

    public static void create(PostPaymentTransactionPayLoad request) throws Exception {
        PaymentTransactionRepository.create(request);
    }
}
