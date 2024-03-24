package com.mdsl.institution.domain.businessmessage;

public enum CustomHttpStatus
{
    BUSINESS_ERROR(601);

    private final int value;
    
    CustomHttpStatus(int value)
    {
	this.value = value;
    }

    public int value()
    {
        return value;
    }
    
    
}
