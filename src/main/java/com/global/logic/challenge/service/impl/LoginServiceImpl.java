package com.global.logic.challenge.service.impl;

import com.global.logic.challenge.domain.MyUserDetails;
import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.exception.ServiceException;
import com.global.logic.challenge.service.LoginService;
import com.global.logic.challenge.service.UserService;
import com.global.logic.challenge.utils.JWTUtils;
import com.global.logic.challenge.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;

    /**
     * SIGN-UP
     *
     * @param request
     * @return
     * @throws Exception
     */

    public UserDTO signUp(SignUpRequest request) throws ServiceException {
        log.info("--> Start method signUp service");
        LocalDateTime localDateNow = LocalDateTime.now();
        UserDTO user = userService.signUpRequestToUserDTO(request, localDateNow);
        String userEmail = request.getEmail();

        log.info("    Check if user {} exist.", userEmail);
        if (userService.getByEmail(userEmail) == null) {
           //user don't exist. Then Sign-up
            UserDTO response = userService.saveUser(user);
            response.setToken(createToken(user, localDateNow));
            return response;
        } else {
            throw new ServiceException( HttpStatus.BAD_REQUEST ,"Error. User " + userEmail + " already exist!.");
        }
    }//method closure


    /**
     * LOGIN
     *
     * @param token
     * @return UserDTO
     * @throws ServiceException
     */
      public UserDTO login(String token) throws ServiceException {
        log.info("--> Start method login.");

        String username = jwtUtils.getClaimByKey(token, "sub");
        String created = jwtUtils.getClaimByKey(token, "created");
        //Get user from DB
        UserDTO userDTO ;
        try {
            userDTO = userService.getByEmail(username);
        } catch (Exception e) {
            throw new ServiceException( HttpStatus.UNAUTHORIZED ,"User don't exist in DB");
        }
        //If user lastLogin == claim Created jwt then build new JWT and Update DB
        if (userDTO.getLastLogin().equals(created)) {
            log.info("     user {} with token valid, ={}", userDTO.getPhones(), created);

            LocalDateTime localDateNow = LocalDateTime.now();
            userDTO.setLastLogin(TimeUtils.getSDateFormatted(localDateNow));
            userDTO = userService.saveUser(userDTO);
            if (userDTO == null)
                throw new ServiceException( HttpStatus.BAD_REQUEST ,"Error updating user");

            userDTO.setToken(createToken(userDTO, localDateNow));
        } else {
            throw new ServiceException( HttpStatus.BAD_REQUEST ,"token no active");
        }
        log.info("<-- End method login.");
        return userDTO;
    }//method closure

    /**
     * This method creates a token JWT
     *
     * @param user
     * @param localDateNow
     * @return
     */
    public String createToken(UserDTO user, LocalDateTime localDateNow) {
        log.info(" Start method create token for user {}", user);
        MyUserDetails myUd = new MyUserDetails();
        myUd.setUsr(user.getEmail());
        myUd.setPswd(user.getPassword());
        Map<String, Object> claims = new HashMap<>();
        //set claim created
        claims.put("created", TimeUtils.getSDateFormatted(localDateNow));
        return jwtUtils.generateToken(myUd, claims);
    }//method closure

}//class closure
