package az.bankrespublika.service.impl;

import az.bankrespublika.dao.AccountEntityDao;
import az.bankrespublika.dao.UserEntityDao;
import az.bankrespublika.entity.AccountEntity;
import az.bankrespublika.entity.UserEntity;
import az.bankrespublika.exception.ExceptionConstant;
import az.bankrespublika.request.AccountRequest;
import az.bankrespublika.response.AccountResponse;
import az.bankrespublika.response.RespStatus;
import az.bankrespublika.security.jwt.JwtTokenProvider;
import az.bankrespublika.service.AccountEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class AccountEntityServiceImpl implements AccountEntityService {

    private final AccountEntityDao accountEntityDao;
    private final UserEntityDao userEntityDao;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AccountEntityServiceImpl(AccountEntityDao accountEntityDao, UserEntityDao userEntityDao, JwtTokenProvider jwtTokenProvider) {
        this.accountEntityDao = accountEntityDao;
        this.userEntityDao = userEntityDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AccountEntity findAccount(String username) {
        return accountEntityDao.findAccountByUsername(username);
    }


    public Object accountSummary(HttpServletRequest req) {
        String token = jwtTokenProvider.resolveToken(req);
        String usernameReq = jwtTokenProvider.getUsername(token);
        if (usernameReq.isEmpty()) {
            return new RespStatus(new ExceptionConstant().NOT_CORRECT, "Username is not correct");
        }
        UserEntity user = userEntityDao.findUserEntityByUsername(usernameReq);
        if (user == null) {
            return new RespStatus(new ExceptionConstant().INVALID_DATA, "Invalid username");
        }
        AccountEntity accountEntity = findAccount(user.getUsername());
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setSurname(accountEntity.getUserEntity().getSurname());
        accountResponse.setName(accountEntity.getUserEntity().getName());
        accountResponse.setAccountNumber(accountEntity.getAccountNumber());
        accountResponse.setBalance(accountEntity.getBalance());
        return accountResponse;
    }


    @Override
    public RespStatus openAccount(AccountRequest accountRequest, HttpServletRequest req) {
        String token = jwtTokenProvider.resolveToken(req);
        String usernameReq = jwtTokenProvider.getUsername(token);
        if (accountRequest.getBalance() < 0 || accountRequest.getAccountNumber().isEmpty()) {
            return new RespStatus(new ExceptionConstant().INVALID_DATA, "Invalid data");
        } else {
            UserEntity user = userEntityDao.findUserEntityByUsername(usernameReq);
            AccountEntity account = accountEntityDao.findAccountByUsername(user.getUsername());
            if (user != null) {
                if (account != null) {
                    return new RespStatus(new ExceptionConstant().ACCOUNT_EXISTS, "Account exists");
                }
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setUserEntity(user);
                accountEntity.setAccountNumber(accountRequest.getAccountNumber());
                accountEntity.setBalance(accountRequest.getBalance());
                AccountEntity accountResp = accountEntityDao.save(accountEntity);
                if (accountResp != null) {
                    return new RespStatus(new ExceptionConstant().SUCCESS_CODE, "Account added successfully");
                } else {
                    return new RespStatus(new ExceptionConstant().ERROR_CODE, "Account is not registered");
                }
            } else {
                return new RespStatus(new ExceptionConstant().INVALID_USERNAME_OR_PASSWORD, "Invalid username");
            }
        }
    }


}