package com.mdsl.institution.repository.institution;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.mdsl.institution.domain.common.RecordStatus;
import com.mdsl.institution.domain.institution.Institution;

/**
 * Repository interface for accessing institutions.
 */
public interface InstitutionRepository
	extends JpaRepository<Institution, BigDecimal>, QuerydslPredicateExecutor<Institution>
{

    /**
     * Finds an institution by its code.
     *
     * @param code The code of the institution.
     * @return An optional containing the institution if found, empty otherwise.
     */
    Optional<Institution> findByInstitutionCode(String code);

    /**
     * Finds all institutions by their record status.
     *
     * @param status The record status of the institutions.
     * @return A list of institutions with the given record status.
     */
    List<Institution> findAllByRecordStatus(RecordStatus status);

    /**
     * Counts institutions by their code.
     *
     * @param institutionCode The code of the institution.
     * @return The count of institutions with the given code.
     */
    BigDecimal countByInstitutionCode(String institutionCode);
}
