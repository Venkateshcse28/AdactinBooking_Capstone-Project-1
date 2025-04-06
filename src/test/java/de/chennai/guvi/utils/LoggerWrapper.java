package de.chennai.guvi.utils;

import org.apache.log4j.Logger;

import de.chennai.guvi.report.ITestListenerImpl;

public class LoggerWrapper {
    private static final Logger logger = Logger.getLogger(LoggerWrapper.class.getSimpleName());

    // Static method for info logging
    public  void info(String message) {
        ITestListenerImpl.info(message, logger);
    }

    // Static method for error logging without exception
    public  void error(String message) {
        ITestListenerImpl.error(message, logger);
    }

    // Static method for error logging with exception
    public void error(String message, Exception e) {
        ITestListenerImpl.error(message + ": " + e.getMessage(), logger);
    }
}