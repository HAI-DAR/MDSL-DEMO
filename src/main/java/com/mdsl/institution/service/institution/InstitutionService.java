package com.mdsl.institution.service.institution;

import java.math.BigDecimal;
import java.util.List;

import com.mdsl.institution.domain.institution.Institution;
import com.mdsl.institution.dto.institution.SaveInstitutionRequest;
import com.mdsl.institution.dto.institution.SaveInstitutionResponse;
import com.mdsl.institution.exception.BusinessException;

/**
 * Service interface for institution operations.
 */
public interface InstitutionService
{

    /**
     * Saves an institution.
     *
     * @param request The request to save an institution.
     * @return The response after saving the institution.
     * @throws BusinessException If an error occurs during saving.
     */
    SaveInstitutionResponse save(SaveInstitutionRequest request) throws BusinessException;

    /**
     * Updates an institution.
     *
     * @param request The request to update an institution.
     * @return The response after updating the institution.
     * @throws BusinessException If an error occurs during updating.
     */
    SaveInstitutionResponse update(SaveInstitutionRequest request) throws BusinessException;

    /**
     * Deletes an institution by ID.
     *
     * @param id The ID of the institution to delete.
     * @throws BusinessException If an error occurs during deletion.
     */
    void delete(BigDecimal id) throws BusinessException;

    /**
     * Finds all active institutions.
     *
     * @return A list of active institutions.
     * @throws BusinessException If an error occurs during retrieval.
     */
    List<Institution> findAllActive() throws BusinessException;

    /**
     * Finds all inactive institutions.
     *
     * @return A list of inactive institutions.
     * @throws BusinessException If an error occurs during retrieval.
     */
    List<Institution> findAllInActive() throws BusinessException;

    /**
     * Finds an institution by ID.
     *
     * @param id The ID of the institution to find.
     * @return The found institution.
     * @throws BusinessException If an error occurs during retrieval.
     */
    Institution findById(BigDecimal id) throws BusinessException;

    /**
     * Finds institutions by criteria.
     *
     * @param entity The entity containing search criteria.
     * @return An iterable of institutions matching the criteria.
     * @throws BusinessException If an error occurs during retrieval.
     */
    Iterable<Institution> findByCriteria(Institution entity) throws BusinessException;

}
