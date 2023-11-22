package com.global.logic.challenge.mappers;

import com.global.logic.challenge.domain.UserDTO;
import com.global.logic.challenge.domain.signup.SignUpRequest;
import com.global.logic.challenge.entity.User;
import com.global.logic.challenge.utils.TimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AppMappers {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper;
    @PostConstruct void init(){
        mapper = new ModelMapper();
    }

    public UserDTO userEntityToDTO(User entity){
        return mapper.map(entity, UserDTO.class);
    }

    public User userDTOtoEntity(UserDTO dto){
        return mapper.map(dto, User.class);
    }

    public UserDTO signUpRequestToUserDTO(SignUpRequest request, LocalDateTime localDateNow){
        UserDTO newUser = new UserDTO();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setName(request.getName());
        newUser.setCreationDate(TimeUtils.getSDateFormatted(localDateNow));
        newUser.setLastLogin(TimeUtils.getSDateFormatted(localDateNow));
        newUser.setIsActive(true);
        newUser.setPhones(request.getPhones());
        return newUser;
    }
}
