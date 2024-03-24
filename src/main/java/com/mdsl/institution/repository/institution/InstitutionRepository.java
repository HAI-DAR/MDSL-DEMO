package com.mdsl.institution.repository.institution;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.mdsl.institution.domain.common.RecordStatus;
import com.mdsl.institution.domain.institution.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, BigDecimal> , QuerydslPredicateExecutor<Institution>
{
	
	Optional<Institution> findByInstitutionCode(String code); 
	
	List<Institution> findAllByRecordStatus(RecordStatus status);
	
	BigDecimal countByInstitutionCode(String institutionCode);
}
