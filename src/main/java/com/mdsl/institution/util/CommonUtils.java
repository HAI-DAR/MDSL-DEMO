package com.mdsl.institution.util;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mdsl.institution.domain.common.CommonConstants;

public class CommonUtils
{
    public static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static final ObjectMapper OBJECT_MAPPER = initObjectMapper();

    private static ObjectMapper initObjectMapper()
    {
	SimpleDateFormat df = new SimpleDateFormat(CommonConstants.SIMPLE_DATE_FORMAT);
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	mapper.setPropertyNamingStrategy(MDSLPropertyNamingStrategy.KEEP_AS_IS);
	mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
	mapper.setDateFormat(df);
	mapper.registerModule(new JavaTimeModule());
	Version vers = Version.unknownVersion();
	SimpleModule module = new SimpleModule("bigDecimalModule", vers);
	module.addSerializer(BigDecimal.class, new ToStringSerializer());
	mapper.registerModule(module);
	return mapper;
    }

    public static <T> T convertMapToObject(Map<String, Object> map, Class<T> objClass)
    {
	return OBJECT_MAPPER.convertValue(map, objClass);
    }

    public static Map<String, Object> convertObjectToMap(Object obj)
    {
	return OBJECT_MAPPER.convertValue(obj, Map.class);
    }

    public static <T, U> T convert(U source, Class<T> targetType)
    {
	T target = null;

	try
	{
	    target = targetType.getDeclaredConstructor().newInstance();
	}
	catch(Exception e)
	{
	    log.error("Error occured in the function CommonUtils.convert ", e.getMessage());
	    e.printStackTrace();
	}
	BeanUtils.copyProperties(source, target);

	return target;
    }

    public static void copyPropertiesIncluding(Object source, Object target, String... includedProperties)
    {
	BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
	BeanWrapper targetWrapper = new BeanWrapperImpl(target);

	for(String propertyName : includedProperties)
	{
	    Object propertyValue = sourceWrapper.getPropertyValue(propertyName);
	    targetWrapper.setPropertyValue(propertyName, propertyValue);
	}
    }
    
    public static void copyPropertiesExcluding(Object source, Object target, String... excludedProperties) {
	    BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
	    BeanWrapper targetWrapper = new BeanWrapperImpl(target);

	    Set<String> excludedSet = new HashSet<>(Arrays.asList(excludedProperties));

	    for (PropertyDescriptor descriptor : sourceWrapper.getPropertyDescriptors()) {
	        String propertyName = descriptor.getName();
	        if (!excludedSet.contains(propertyName)) {
	            Object propertyValue = sourceWrapper.getPropertyValue(propertyName);
	            targetWrapper.setPropertyValue(propertyName, propertyValue);
	        }
	    }
	}

}
