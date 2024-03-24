package com.mdsl.institution.service.institution;

import java.math.BigDecimal;
import java.util.List;

import com.mdsl.institution.domain.institution.Institution;
import com.mdsl.institution.dto.institution.SaveInstitutionRequest;
import com.mdsl.institution.dto.institution.SaveInstitutionResponse;
import com.mdsl.institution.exception.BusinessException;

public interface InstitutionService
{

    SaveInstitutionResponse save(SaveInstitutionRequest request) throws BusinessException;

    SaveInstitutionResponse update(SaveInstitutionRequest request) throws BusinessException;
    
    void delete(BigDecimal id) throws BusinessException;
    
    List<Institution> findAllActive() throws BusinessException;
    
    List<Institution> findAllInActive() throws BusinessException;

    Institution  findById(BigDecimal id) throws BusinessException;

    Iterable<Institution> findByCriteria(Institution entity) throws BusinessException;

}
