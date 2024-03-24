package com.mdsl.institution.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StringUtil
{

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    public static String nullToEmpty(Object obj)
    {
	return null == obj ? "" : obj.toString();
    }

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
