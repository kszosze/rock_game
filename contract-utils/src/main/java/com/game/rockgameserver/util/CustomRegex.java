package com.game.rockgameserver.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Pattern;

public final class CustomRegex {
    protected static final Pattern CUSTOM_POSITIVE_INT = Pattern.compile("([0-9]\\d*)");
    private static final Log log = LogFactory.getLog(CustomRegex.class);

    public CustomRegex() {
        log.warn("WARNING: RegexPatterns shouldn't be instantiated. Use its static methods instead.");
    }

    public static Pattern customPositiveInt() {
        return CUSTOM_POSITIVE_INT;
    }
}
