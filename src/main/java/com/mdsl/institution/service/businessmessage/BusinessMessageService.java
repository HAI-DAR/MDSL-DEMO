package com.mdsl.institution.service.businessmessage;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;
import com.mdsl.institution.exception.BaseException;

/**
 * Service interface for business messages.
 */
public interface BusinessMessageService
{

    /**
     * Returns a translated or default business message.
     *
     * @param msgCode The code of the message.
     * @return The translated or default business message.
     * @throws BaseException If an error occurs while retrieving the message.
     */
    BusinessMessage returnTranslatedOrDefaultMessage(Integer msgCode) throws BaseException;

}
