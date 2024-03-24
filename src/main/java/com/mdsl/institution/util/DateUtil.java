package com.mdsl.institution.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil
{
    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
    
    public static LocalDateTime returnCurrentDate()
    {
	return LocalDateTime.now(ZoneId.systemDefault());
    }
}
