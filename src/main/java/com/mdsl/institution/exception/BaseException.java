package com.mdsl.institution.exception;

import java.io.Serializable;

public class BaseException extends Exception implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BaseException()
    {
	super();
    }

    public BaseException(String message)
    {
	super(message);
    }

}
