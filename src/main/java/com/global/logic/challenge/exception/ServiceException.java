package com.global.logic.challenge.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Freddy Paredes
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ServiceException  extends RuntimeException{
    /**
     * error code
     */
    private HttpStatus code;

    /**
     * timestamp
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    /**
     * @param code
     * @param message
     */
    public ServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.timestamp = LocalDateTime.now();

    }



}//class closure
