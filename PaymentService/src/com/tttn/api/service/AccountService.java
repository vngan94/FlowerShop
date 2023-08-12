package com.tttn.api.service;

import com.tttn.api.entities.request.PostAccountPayLoad;
import com.tttn.api.repository.AccountRepository;

public class AccountService {

    private static AccountService accountService = null;
    public static AccountService ins() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public static void createAccount(PostAccountPayLoad request) throws Exception {
        AccountRepository.createAccount(request);
    }
}
