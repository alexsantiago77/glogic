package com.global.logic.challenge.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private String creationDate;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private List<PhoneDTO> phones;
}
