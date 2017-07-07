package com.epam.atm.utils;

import com.epam.atm.pages.HomePage;
import org.slf4j.LoggerFactory;

public class Logger {
    private static HomePage logic;

    public static void main(String[] args) {
        logic = new HomePage();
        logic.open();
    }

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}