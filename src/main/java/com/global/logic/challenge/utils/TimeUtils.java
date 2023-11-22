package com.global.logic.challenge.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Freddy Paredes
 * This class handle all time utils
 */
public class TimeUtils {

    /**
     * Get date formatted by challenge
     * @return
     */
    public static String getSDateFormatted(LocalDateTime localDateNow){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss a");
        return localDateNow.format(dateTimeFormatter);
    }


}
