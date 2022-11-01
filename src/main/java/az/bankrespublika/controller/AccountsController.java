package az.bankrespublika.controller;

import az.bankrespublika.request.AccountRequest;
import az.bankrespublika.response.RespStatus;
import az.bankrespublika.service.AccountEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountEntityService accountEntityService;

    @Autowired
    public AccountsController(AccountEntityService accountEntityService) {
        this.accountEntityService = accountEntityService;
    }

    @PostMapping("/openAccount")
    public RespStatus addAccount(@RequestBody AccountRequest accountRequest, HttpServletRequest req) {
        return accountEntityService.openAccount(accountRequest, req);
    }

    @GetMapping("/accountSummary")
    public Object accountSummary(HttpServletRequest req) {
        return accountEntityService.accountSummary(req);
    }

}