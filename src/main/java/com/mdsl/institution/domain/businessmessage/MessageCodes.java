package com.mdsl.institution.domain.businessmessage;

public enum MessageCodes
{
    INVALID_LOGIN(1, "Error", "Invalid Login"),
    SESSION_EXPIRED(2, "Warning", "Session Expired"),
    RECORD_NOT_FOUND(3, "Error", "Record Not Found"),
    SECURITY_ERROR(4, "Error", "Security Error, please contact your administrator"),
    DUPLICATE_RECORD(5, "Error", "Duplicate Record"),
    GENERIC_MSG(999, "", "");
    
    private final Integer msgCode;
    private final String msgTitle;
    private final String msgDesc;
    
    MessageCodes(Integer msgCode, String msgTitle, String msgDesc)
    {
	this.msgCode = msgCode;
	this.msgTitle = msgTitle;
	this.msgDesc = msgDesc;
    }

    public Integer msgCode()
    {
        return msgCode;
    }

    public String msgDesc()
    {
        return msgDesc;
    }
    
    public String msgTitle()
    {
        return msgTitle;
    }
    
    public static MessageCodes getByCode(Integer msgCode) {
        for (MessageCodes message : values()) {
            if (message.msgCode.equals(msgCode)) {
                return message;
            }
        }
        return null;
    }
}
