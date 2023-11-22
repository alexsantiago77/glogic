package com.global.logic.challenge.service.impl;

import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.mappers.AppMappers;
import com.global.logic.challenge.repository.UserLoginRepository;
import com.global.logic.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    AppMappers mapp;

    @Transactional
    public UserDTO getByEmail(String email){
        try {
            return mapp.userEntityToDTO(userLoginRepository.getByEmail(email));
        }catch (Exception e){
            return null;
        }
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO){
        return mapp.userEntityToDTO(
                userLoginRepository.save(mapp.userDTOtoEntity(userDTO)));
    }

    public UserDTO signUpRequestToUserDTO(SignUpRequest request, LocalDateTime localDateNow){
        return mapp.signUpRequestToUserDTO(request, localDateNow);
    }

}
