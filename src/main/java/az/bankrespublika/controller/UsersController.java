package az.bankrespublika.controller;

import az.bankrespublika.entity.UserEntity;
import az.bankrespublika.request.UserRequest;
import az.bankrespublika.response.RespStatus;
import az.bankrespublika.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UserEntityService userEntityService;


    @Autowired
    public UsersController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/singUp")
    public RespStatus singUp(@RequestBody UserEntity userEntity) {
        return userEntityService.registrationUser(userEntity);
    }


    @PostMapping("/singIn")
    public ResponseEntity singIn(@RequestBody UserRequest userRequest) {
        return userEntityService.login(userRequest);
    }
}