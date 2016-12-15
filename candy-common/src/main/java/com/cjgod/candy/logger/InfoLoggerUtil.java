package com.cjgod.candy.logger;

public class InfoLoggerUtil extends BaseLogger {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(INFO);

    public static void d(String message) {
        logger.debug(message);
    }

    public static void d(String message, Throwable t) {
        logger.debug(message, t);
    }

    public static void w(String message) {
        logger.warn(message);
    }

    public static void w(String message, Throwable t) {
        logger.warn(message, t);
    }

    public static void e(String message) {
        logger.error(message);
    }

    public static void e(String message, Throwable t) {
        logger.error(message, t);
    }

    public static void i(String message) {
        logger.info(message);
    }

    public static void i(String message, Throwable t) {
        logger.info(message, t);
    }
}
