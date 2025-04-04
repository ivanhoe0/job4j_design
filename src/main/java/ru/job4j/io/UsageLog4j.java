package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        int i = 1;
        long l = 2L;
        double d = 3.0;
        float f = 4.0F;
        short sh = 5;
        byte b = 6;
        char ch = '7';
        boolean flag = true;
        LOG.debug("The variables are {}, {}, {}, {}, {}, {}, {}, {}", i, l, d, f, sh, b, ch, flag);
    }
}