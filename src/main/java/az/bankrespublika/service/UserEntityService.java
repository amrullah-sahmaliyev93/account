package az.bankrespublika.service;

import az.bankrespublika.entity.UserEntity;
import az.bankrespublika.request.UserRequest;
import az.bankrespublika.response.RespStatus;
import org.springframework.http.ResponseEntity;

public interface UserEntityService {

    RespStatus registrationUser(UserEntity userEntity);

    ResponseEntity login(UserRequest userRequest);


}