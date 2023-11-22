package com.global.logic.challenge.controller;

import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * This Class handle the exposure by controllers
 * @author Freddy Paredes
 *
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginServiceImpl service;

    /**
     * Sign up user
     * @param request
     * @return UserDTO
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpRequest request)  {
        log.info("--> Start endpoint signUp request="+request.toString());
        UserDTO response = service.signUp(request);
        log.info("<-- End endpoint signUp");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }//method closure

    /**
     *  Login from a valid user token
     * @param bearerToken
     * @return UserDTO
     */
    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestHeader(name = "Authorization", required = true) String bearerToken)  {
        log.info("--> Start endpoint login.");
        String token = bearerToken.split("Bearer ")[1];
        UserDTO response = service.login(token);
        log.info("<-- End endpoint login.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

}//method closure
