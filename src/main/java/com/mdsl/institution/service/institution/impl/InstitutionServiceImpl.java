package com.mdsl.institution.service.institution.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdsl.institution.domain.businessmessage.MessageCodes;
import com.mdsl.institution.domain.common.RecordStatus;
import com.mdsl.institution.domain.institution.Institution;
import com.mdsl.institution.domain.institution.QInstitution;
import com.mdsl.institution.dto.institution.SaveInstitutionRequest;
import com.mdsl.institution.dto.institution.SaveInstitutionResponse;
import com.mdsl.institution.exception.BusinessException;
import com.mdsl.institution.repository.institution.InstitutionRepository;
import com.mdsl.institution.service.institution.InstitutionService;
import com.mdsl.institution.util.CommonUtils;
import com.mdsl.institution.util.DateUtil;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class InstitutionServiceImpl implements InstitutionService
{

    private final InstitutionRepository institutionRepository;
    
    @Override
    public SaveInstitutionResponse save(SaveInstitutionRequest request) throws BusinessException
    {
	Institution entity = CommonUtils.convert(request, Institution.class);
	if(!checkInstitutionUniqueness(entity))
	{
	    throw new BusinessException(MessageCodes.DUPLICATE_RECORD.msgCode()); 
	}
	entity.setCreatedDate(DateUtil.returnCurrentDate());
	entity.setRecordStatus(RecordStatus.ACTIVE);
	entity = institutionRepository.save(entity);
	
	return CommonUtils.convert(entity, SaveInstitutionResponse.class);
    }
    
    

    @Override
    public SaveInstitutionResponse update(SaveInstitutionRequest request) throws BusinessException
    {
	Institution entity = CommonUtils.convert(request, Institution.class);
	
	entity.setCreatedDate(DateUtil.returnCurrentDate());
	entity.setRecordStatus(RecordStatus.ACTIVE);
	entity = institutionRepository.save(entity);
	
	return CommonUtils.convert(entity, SaveInstitutionResponse.class);
    }

    @Override
    public void delete(BigDecimal id) throws BusinessException
    {
	Optional<Institution> institution =  institutionRepository.findById(id);
	
	if(institution.isEmpty())
	    throw new BusinessException(MessageCodes.GENERIC_MSG.msgCode(), "Invalid request");
	
	institution.get().setRecordStatus(RecordStatus.INACTIVE);
	
	institutionRepository.save(institution.get());
	
    }

    @Override
    public List<Institution> findAllActive() throws BusinessException
    {
	return institutionRepository.findAllByRecordStatus(RecordStatus.ACTIVE);
    }

    @Override
    public List<Institution> findAllInActive() throws BusinessException
    {
	return institutionRepository.findAllByRecordStatus(RecordStatus.INACTIVE);
    }

    @Override
    public Institution findById(BigDecimal id) throws BusinessException
    {
	Optional<Institution> entity = institutionRepository.findById(id);
	if(entity.isEmpty())
	    throw new BusinessException(MessageCodes.RECORD_NOT_FOUND.msgCode());
	
	return entity.get();
    }
    
    
    @Override
    public Iterable<Institution> findByCriteria(Institution entity) {
        QInstitution institution = QInstitution.institution;
        BooleanBuilder where = new BooleanBuilder();

        if (entity.getInstitutionCode() != null) {
            where.and(institution.institutionCode.eq(entity.getInstitutionCode()));
        }

        if (entity.getInstitutionName() != null) {
            where.and(institution.institutionName.eq(entity.getInstitutionName()));
        }
        
        if (entity.getCreatedBy() != null) {
            where.and(institution.createdBy.eq(entity.getCreatedBy()));
        }
        
        if (entity.getCreatedDate() != null) {
            where.and(institution.createdDate.eq(entity.getCreatedDate()));
        }
        
        if (entity.getModifiedBy() != null) {
            where.and(institution.modifiedBy.eq(entity.getModifiedBy()));
        }
        
        if (entity.getModifiedDate() != null) {
            where.and(institution.modifiedDate.eq(entity.getModifiedDate()));
        }
        
        if (entity.getAuthorizedBy() != null) {
            where.and(institution.authorizedBy.eq(entity.getAuthorizedBy()));
        }
        
        if (entity.getAuthorizedDate() != null) {
            where.and(institution.authorizedDate.eq(entity.getAuthorizedDate()));
        }
        
        if (entity.getRecordStatus() != null) {
            where.and(institution.recordStatus.eq(entity.getRecordStatus()));
        }

        return institutionRepository.findAll(where);
    }

    private boolean checkInstitutionUniqueness(Institution entity)
    {
	return institutionRepository.countByInstitutionCode(entity.getInstitutionCode()).compareTo(BigDecimal.ZERO) > 0 ? false : true;
    }

}
