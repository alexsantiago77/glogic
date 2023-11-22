package com.global.logic.challenge.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    public void testTimeUtils(){
        LocalDateTime localDateNow = LocalDateTime.now();
        String time  =TimeUtils.getSDateFormatted(localDateNow);
        assertNotNull(time);
    }
}