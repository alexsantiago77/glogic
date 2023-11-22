package com.global.logic.challenge.controller;

import com.global.logic.challenge.domain.PhoneDTO;
import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginControllerTest {
    @Mock
    private LoginServiceImpl service;

    @InjectMocks
    LoginController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testSignUp() {
        SignUpRequest signUpRequest = new SignUpRequest();

        UserDTO mockedUserDTO = new UserDTO();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setCityCode(123);
        phoneDTO.setNumber(Long.getLong("123"));
        phoneDTO.setCountryCode("CL");
        mockedUserDTO.setPassword("asdasd");
        mockedUserDTO.setCreationDate("nov. 20, 2023 07:58:01 p.m.");
        mockedUserDTO.setLastLogin("nov. 20, 2023 07:58:01 p.m.");

        List<PhoneDTO> listPhones = new ArrayList<>();
        listPhones.add(phoneDTO);
        mockedUserDTO.setPhones(listPhones);
        signUpRequest.setPhones(listPhones);
        when(service.signUp(signUpRequest)).thenReturn(mockedUserDTO);
        ResponseEntity<UserDTO> responseEntity = controller.signUp(signUpRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockedUserDTO, responseEntity.getBody());
    }

    @Test
    public void testLogin() {

        String bearerToken = "Bearer yourAccessToken";
        UserDTO mockedUserDTO = new UserDTO();
        when(service.login("yourAccessToken")).thenReturn(mockedUserDTO);
        ResponseEntity<UserDTO> responseEntity = controller.login(bearerToken);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedUserDTO, responseEntity.getBody());
        // Puedes agregar más aserciones según tus necesidades
    }

}