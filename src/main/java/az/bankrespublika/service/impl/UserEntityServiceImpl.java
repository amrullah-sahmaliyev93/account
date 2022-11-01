package az.bankrespublika.service.impl;

import az.bankrespublika.dao.RoleEntityDao;
import az.bankrespublika.dao.UserEntityDao;
import az.bankrespublika.entity.RoleEntity;
import az.bankrespublika.entity.Status;
import az.bankrespublika.entity.UserEntity;
import az.bankrespublika.exception.ExceptionConstant;
import az.bankrespublika.request.UserRequest;
import az.bankrespublika.response.RespStatus;
import az.bankrespublika.security.jwt.JwtTokenProvider;
import az.bankrespublika.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityDao userEntityDao;
    private final RoleEntityDao roleEntityDao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserEntityServiceImpl(@Lazy UserEntityDao userEntityDao, RoleEntityDao roleEntityDao, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userEntityDao = userEntityDao;
        this.roleEntityDao = roleEntityDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public RespStatus registrationUser(UserEntity userEntity) {
        UserEntity userFindByUsername = userEntityDao.findUserEntityByUsername(userEntity.getUsername());
        if (userFindByUsername != null) {
            return new RespStatus(new ExceptionConstant().ACCOUNT_EXISTS, "Account exists");
        }
        if ((userEntity.getPassword() != null && !userEntity.getPassword().isEmpty() && userEntity.getPassword().length() > 1) && (userEntity.getUsername() != null && !userEntity.getPassword().isEmpty()) && (userEntity.getUsername() != null && !userEntity.getUsername().isEmpty()) && (userEntity.getName() != null && !userEntity.getName().isEmpty())) {
            UserEntity userFind = userEntityDao.findUserEntityByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword());
            if (userFind == null) {
                RoleEntity roleUser = roleEntityDao.findByName("ROLE_USER");
                List<RoleEntity> userRoles = new ArrayList<>();
                userRoles.add(roleUser);
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                userEntity.setRoles(userRoles);
                userEntity.setStatus(Status.ACTIVE);
                userEntity.setCreated(new Date());
                userEntity.setUpdated(new Date());
                userEntityDao.save(userEntity);
                return new RespStatus(new ExceptionConstant().SUCCESS_CODE, "Successfully registered");
            } else {
                return new RespStatus(new ExceptionConstant().ACCOUNT_EXISTS, "User already exists");
            }
        } else {
            return new RespStatus(new ExceptionConstant().ERROR_CODE, "Not register");
        }


    }
    @Override
    public ResponseEntity login(UserRequest userRequest) {
        try {
            String username = userRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userRequest.getPassword()));
            UserEntity user = userEntityDao.findUserEntityByUsername(username);
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }

}