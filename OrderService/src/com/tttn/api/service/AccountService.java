package com.tttn.api.service;

import com.tttn.api.dbresult.RGetUser;
import com.tttn.api.entities.request.CheckLoginPayload;
import com.tttn.api.entities.request.GetUserByKeyPayload;
import com.tttn.api.entities.request.PostAccountPayLoad;
import com.tttn.api.entities.request.UpdateAccountPayLoad;
import com.tttn.api.repository.AccountRepository;

public class AccountService {

  private static AccountService accountService = null;

  public static AccountService ins() {
    if (accountService == null) {
      accountService = new AccountService();
    }
    return accountService;
  }

  public static int createAccount(PostAccountPayLoad request) throws Exception {
    return AccountRepository.createAccount(request);
  }

  public static Integer checkLogin(CheckLoginPayload request) throws Exception {
    return AccountRepository.checkLogin(request);
  }

  public static RGetUser getUserByKey(GetUserByKeyPayload request) throws Exception {
    return AccountRepository.getUserByKey(request);
  }

  public static void updateAccount(UpdateAccountPayLoad request) throws Exception {
    AccountRepository.updateAccount(request);
  }
}
