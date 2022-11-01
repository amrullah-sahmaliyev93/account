package az.bankrespublika.service;

import az.bankrespublika.entity.AccountEntity;
import az.bankrespublika.request.AccountRequest;
import az.bankrespublika.response.RespStatus;

import javax.servlet.http.HttpServletRequest;

public interface AccountEntityService {

    AccountEntity findAccount(String username);
    RespStatus openAccount(AccountRequest accountRequest, HttpServletRequest req);
    Object accountSummary(HttpServletRequest req);

}