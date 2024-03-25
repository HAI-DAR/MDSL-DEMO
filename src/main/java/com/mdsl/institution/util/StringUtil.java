package com.mdsl.institution.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for string-related operations.
 */
public final class StringUtil
{

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    /**
     * Converts null to an empty string.
     *
     * @param obj The object to be converted.
     * @return An empty string if the object is null; otherwise, the string representation of the object.
     */
    public static String nullToEmpty(Object obj)
    {
	return null == obj ? "" : obj.toString();
    }

    /**
     * Converts null or an empty string to a specified value.
     *
     * @param obj     The object to be converted.
     * @param toValue The value to be returned if the object is null or an empty string.
     * @return The string representation of the object if it is not null or an empty string; otherwise, the specified value.
     */
    public static String nullEmptyToValue(Object obj, Object toValue)
    {
	if(null == obj)
	{
	    return null == toValue ? "" : toValue.toString();
	}
	else
	{
	    if(obj.toString().isEmpty())
	    {
		return null == toValue ? "" : toValue.toString();
	    }
	    else
	    {
		return obj.toString();
	    }
	}
    }

}
