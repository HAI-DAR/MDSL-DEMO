package com.mdsl.institution.repository.businessmessage;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;

public interface BusinessMessageRepository extends JpaRepository<BusinessMessage, BigDecimal> {
	
	Optional<BusinessMessage> findByMsgCode(Integer code); 
	
}
