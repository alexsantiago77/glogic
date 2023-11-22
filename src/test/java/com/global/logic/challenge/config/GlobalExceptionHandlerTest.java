package com.global.logic.challenge.config;

import com.global.logic.challenge.domain.error.ErrorDTO;
import com.global.logic.challenge.domain.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void buildResponseEntity() {
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(errors);
        ResponseEntity<ErrorResponseDTO> comparer = new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo(400);
        errorDTO.setDetail("Error Test");

        ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
        assertNotNull(response);
        assertEquals(comparer,response);
    }
}