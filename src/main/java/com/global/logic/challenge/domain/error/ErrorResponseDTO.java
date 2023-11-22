package com.global.logic.challenge.domain.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle error general response
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private List<ErrorDTO> error;
}//method closure
