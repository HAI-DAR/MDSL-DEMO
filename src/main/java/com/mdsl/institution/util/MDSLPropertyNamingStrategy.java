package com.mdsl.institution.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;


/**
 * Custom property naming strategy for MDSL (My Domain Specific Language).
 */
public abstract class MDSLPropertyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final PropertyNamingStrategy KEEP_AS_IS = new MDSLPropertyNamingKeepAsIs();

    public static class MDSLPropertyNamingKeepAsIs extends PropertyNamingStrategy.PropertyNamingStrategyBase
    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
         * Converts the getter method name to uppercase and translates the property name.
         *
         * @param config      The configuration for the mapper.
         * @param method      The annotated method.
         * @param defaultName The default name of the property.
         * @return The translated property name.
         */
	@Override
	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
	    if(method.getName().matches("get[_?[A-Z]+[0-9]*]+"))
	    {
		defaultName = defaultName.toUpperCase();
	    }
	    return translate(defaultName);
	}

	/**
         * Converts the setter method name to uppercase and translates the property name.
         *
         * @param config      The configuration for the mapper.
         * @param method      The annotated method.
         * @param defaultName The default name of the property.
         * @return The translated property name.
         */
	@Override
	public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
	    if(method.getName().matches("set[_?[A-Z]+[0-9]*]+"))
	    {
		defaultName = defaultName.toUpperCase();
	    }
	    return translate(defaultName);
	}

	@Override
	public String translate(String propertyName)
	{
	    return propertyName;
	}

    }

}
