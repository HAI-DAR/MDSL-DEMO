package com.mdsl.institution.exception;

import java.io.Serializable;

public class BusinessException extends BaseException implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer msgCode; 
    
    public BusinessException(Integer msgCode, String message) {
	super(message);
	this.msgCode = msgCode;
    }
    
    public BusinessException(String message) {
	super(message);
    }
    
    public BusinessException(Integer msgCode) {
	super();
	this.msgCode = msgCode;
    }

    public Integer getMsgCode()
    {
        return msgCode;
    }

    public void setMsgCode(Integer msgCode)
    {
        this.msgCode = msgCode;
    }
    
}
