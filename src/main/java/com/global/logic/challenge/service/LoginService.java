package com.global.logic.challenge.service;


import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;

/**
 * @author Freddy Paredes
 *
 */
public interface LoginService {

    public UserDTO signUp(SignUpRequest request);

    public UserDTO login(String token);
}//method closure
