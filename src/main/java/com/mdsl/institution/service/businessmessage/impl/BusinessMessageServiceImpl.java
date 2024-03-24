package com.mdsl.institution.service.businessmessage.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;
import com.mdsl.institution.domain.businessmessage.MessageCodes;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.repository.businessmessage.BusinessMessageRepository;
import com.mdsl.institution.service.businessmessage.BusinessMessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BusinessMessageServiceImpl implements BusinessMessageService
{
    private final BusinessMessageRepository businessMessageRepository;

    @Override
    public BusinessMessage returnTranslatedOrDefaultMessage(Integer msgCode) throws BaseException
    {
	Optional<BusinessMessage> message = businessMessageRepository.findByMsgCode(msgCode);

	if(message.isEmpty())
	{
	 // this will return the default message description
	    MessageCodes defaultMessage = MessageCodes.getByCode(msgCode);
   
	    return new BusinessMessage(
		    defaultMessage.msgCode(),
		    defaultMessage.msgTitle(),
		    defaultMessage.msgDesc()
		    );  
	}
	    
	return message.get();

    }

}
