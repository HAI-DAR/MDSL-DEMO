package com.mdsl.institution.configuration;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mdsl.institution.domain.common.CommonConstants;
import com.mdsl.institution.util.MDSLPropertyNamingStrategy;

@Configuration
public class JacksonConfiguration
{
    
    @Bean
    Jackson2ObjectMapperBuilderCustomizer J2OMBC()
    {
	return builder -> {
	    builder.propertyNamingStrategy(MDSLPropertyNamingStrategy.KEEP_AS_IS);
	    builder.simpleDateFormat(CommonConstants.SIMPLE_DATE_FORMAT);
	    //builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConstants.SIMPLE_DATE_FORMAT)));
	};
    }
}
