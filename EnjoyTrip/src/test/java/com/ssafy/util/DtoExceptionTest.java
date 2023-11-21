package com.ssafy.util;

import com.ssafy.util.DtoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DtoExceptionTest {
    public static void main(String[] args) {
        // MySQL timestamp string
        String mysqlTimestampString = "2023-11-21 04:24:15";

        // Define the date format for MySQL timestamp
        DateTimeFormatter mysqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the MySQL timestamp string to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(mysqlTimestampString, mysqlFormatter);

        // Now, 'localDateTime' is a java.time.LocalDateTime representing the MySQL timestamp
        System.out.println("Converted LocalDateTime: " + localDateTime);
    }
}