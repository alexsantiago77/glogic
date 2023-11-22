package com.global.logic.challenge.service;

import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;

import java.time.LocalDateTime;

public interface UserService {

    public UserDTO getByEmail(String email);
    public UserDTO saveUser(UserDTO userDTO);
    public UserDTO signUpRequestToUserDTO(SignUpRequest request, LocalDateTime localDateNow);
}
