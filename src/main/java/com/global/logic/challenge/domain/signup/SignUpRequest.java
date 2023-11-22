package com.global.logic.challenge.domain.signup;

import com.global.logic.challenge.domain.PhoneDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author  Freddy Paredes
 * This DTO handle the Sign-up Request
 * @see "PhoneDTO"
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;


    @NotBlank(message = "Email must be informed.")
    @Email(message = "Wrong email format.")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])[a-zA-Z0-9]{8,12}$", message = "Invalid format.")
    private String password; //TODO: change this name depends of security rule. Maybe can be 'pswd'.
    private List<PhoneDTO> phones;
}//class closure
