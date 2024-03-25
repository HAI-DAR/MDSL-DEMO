package com.mdsl.institution.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for date-related operations.
 */
public final class DateUtil
{
    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
    
    /**
     * Returns the current date and time.
     *
     * @return The current date and time.
     */
    public static LocalDateTime returnCurrentDate()
    {
	return LocalDateTime.now(ZoneId.systemDefault());
    }
}
