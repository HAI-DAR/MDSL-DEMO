package com.mdsl.institution.repository.businessmessage;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;

/**
 * Repository interface for accessing business messages.
 */
public interface BusinessMessageRepository extends JpaRepository<BusinessMessage, BigDecimal>
{

    /**
     * Finds a business message by its code.
     *
     * @param code The code of the business message.
     * @return An optional containing the business message if found, empty otherwise.
     */
    Optional<BusinessMessage> findByMsgCode(Integer code);

}
