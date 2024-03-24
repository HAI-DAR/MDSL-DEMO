package com.mdsl.institution.service.businessmessage;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;
import com.mdsl.institution.exception.BaseException;

public interface BusinessMessageService
{

    BusinessMessage returnTranslatedOrDefaultMessage(Integer msgCode) throws BaseException;

}
