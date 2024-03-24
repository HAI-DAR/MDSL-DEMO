package com.mdsl.institution.dto.error;

import java.time.Instant;

public class ApiErrorResponse
{
    private Instant timeStamp;
    private Integer status;
    private Integer errorCode;
    private String errorTitle;
    private String errorDesc;
    private String path;
    
    public ApiErrorResponse()
    {
	super();
	this.errorDesc = "";
	this.errorTitle = "";
	this.timeStamp = Instant.now();
    }
    
    public ApiErrorResponse(
	    Instant timeStamp, 
	    Integer status,
	    Integer errorCode,
	    String errorTitle,
	    String errorDesc,
	    String path
	    )
    {
	super();
	this.timeStamp = timeStamp;
	this.status = status;
	this.errorCode = errorCode;
	this.errorTitle = errorTitle;
	this.errorDesc = errorDesc;
	this.path = path;
    }
    
    public Instant getTimeStamp()
    {
        return timeStamp;
    }
    public void setTimeStamp(Instant timeStamp)
    {
        this.timeStamp = timeStamp;
    }
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }

    public String getErrorTitle()
    {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle)
    {
        this.errorTitle = errorTitle;
    }

    public String getErrorDesc()
    {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc)
    {
        this.errorDesc = errorDesc;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }
}
