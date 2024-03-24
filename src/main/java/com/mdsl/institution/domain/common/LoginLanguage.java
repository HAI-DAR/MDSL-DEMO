package com.mdsl.institution.domain.common;

public enum LoginLanguage
{
    ENGLISH("EN"),
    ARABIC("AR"),
    FRENCH("FR");
    
    private final String value;
    
    LoginLanguage(String value)
    {
	this.value = value;
    }

    public String value()
    {
        return value;
    }
    
    public static LoginLanguage getByCode(String lang) {
	
	if(null == lang)
	    return null;

        for (LoginLanguage language : values()) {
            if (language.value.equals(lang)) {
                return language;
            }
        }
        return null;
    }
}
