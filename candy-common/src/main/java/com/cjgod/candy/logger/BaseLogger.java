package com.cjgod.candy.logger;

import org.apache.log4j.xml.DOMConfigurator;

public class BaseLogger {

    private static final String   RESOURCE_PATH = "/conf/log4j.xml";

    protected static final String INFO          = "LOGGER_INFO";
    protected static final String CONSOLE       = "LOGGER_CONSOLE";
    protected static final String REQUEST       = "REQUEST";
    protected static final String RESPONSE      = "RESPONSE";

    static {
        DOMConfigurator.configure(BaseLogger.class.getResource(RESOURCE_PATH));
    }
}
