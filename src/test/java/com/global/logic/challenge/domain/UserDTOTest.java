package com.global.logic.challenge.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    public void testEmptyDTO(){
        UserDTO dto = new UserDTO();
        assertNotNull(dto);
    }

    @Test
    public void testUserDTO(){
        UserDTO dto = new UserDTO();
        dto.setLastLogin("a");
        dto.setToken("a");
        dto.setEmail("a");
        dto.setId("a");
        dto.setPassword("a");
        dto.setIsActive(true);
        dto.setName("a");
        dto.setCreationDate("a");
        assertNotNull(dto);
        assertNull(dto.getPhones());
        assertNotNull(dto.getLastLogin());
        assertNotNull(dto.getToken());
        assertNotNull(dto.getId());
        assertNotNull(dto.getCreationDate());
        assertNotNull(dto.getName());
        assertTrue(dto.getIsActive());
    }

}