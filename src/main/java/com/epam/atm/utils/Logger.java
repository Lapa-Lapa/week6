package com.epam.atm.utils;

import org.slf4j.LoggerFactory;

public class Logger {

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void error(String message) {
        logger.error(message);
    }

    public static void info(String message) {
        logger.info(message);
    }
}