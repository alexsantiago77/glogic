package com.global.logic.challenge.service.impl;

import com.global.logic.challenge.domain.MyUserDetails;
import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.exception.ServiceException;
import com.global.logic.challenge.service.UserService;
import com.global.logic.challenge.utils.JWTUtils;
import com.global.logic.challenge.utils.TimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class LoginServiceImplTest {

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    UserService userService;
    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void signUpException() {
        LocalDateTime localDateNow = LocalDateTime.now();
        SignUpRequest request = new SignUpRequest();
        request.setEmail("abcd@asd.cl");
        request.setPassword("qweqwe");
        UserDetails userDetails = new MyUserDetails();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("abcd@asd.cl");
        doReturn(userDTO).when(userService).signUpRequestToUserDTO(request,localDateNow);
        when(userService.getByEmail(userDTO.getEmail())).thenReturn(userDTO);
        when(userService.saveUser(userDTO)).thenReturn(userDTO);
        ServiceException response = assertThrows(ServiceException.class, () -> loginService.signUp(request));
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getCode());
    }


    @Test
    void createToken(){
        UserDTO user = new UserDTO();
        user.setToken("asd");
        user.setEmail("email@gmail.com");
        user.setPassword("adasdasdasda");
        LocalDateTime localDateNow = LocalDateTime.now();
        MyUserDetails myUd = new MyUserDetails();
        myUd.setUsr(user.getEmail());
        myUd.setPswd(user.getPassword());
        myUd.isEnabled();

        Map<String, Object> claims = new HashMap<>();
        claims.put("created", TimeUtils.getSDateFormatted(localDateNow));
        String responseToken = "IsATokenDummy";
        doReturn(responseToken).when(jwtUtils).generateToken(myUd,claims);

        String response = loginService.createToken(user,localDateNow);
        assertNotNull(response);

    }

}