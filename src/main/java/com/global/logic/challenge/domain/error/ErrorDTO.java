package com.global.logic.challenge.domain.error;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author Freddy Paredes
 * This class handle error data
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private Timestamp timestamp;
    private int codigo; //TODO: change this name to "code" to hold english standard
    private String detail;
}
