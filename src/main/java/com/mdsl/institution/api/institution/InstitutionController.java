package com.mdsl.institution.api.institution;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdsl.institution.domain.institution.Institution;
import com.mdsl.institution.dto.institution.SaveInstitutionRequest;
import com.mdsl.institution.dto.institution.SaveInstitutionResponse;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.service.institution.InstitutionService;

import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling institution-related endpoints.
 */
@RestController
@RequestMapping("/v1/institution")
@RequiredArgsConstructor
public class InstitutionController
{
    private final InstitutionService institutionService;

    /**
     * Endpoint for submitting or updating an institution.
     *
     * @param request The request body containing institution details.
     * @return ResponseEntity containing the response with saved institution details.
     * @throws BaseException If an error occurs during submission or update.
     */
    @PostMapping("/submit")
    public ResponseEntity<SaveInstitutionResponse> submit(@RequestBody SaveInstitutionRequest request)
	    throws BaseException
    {
	SaveInstitutionResponse response = new SaveInstitutionResponse();

	if(null == request.getId())
	    response = institutionService.save(request);
	else
	    response = institutionService.update(request);

	return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for deleting an institution by its ID.
     *
     * @param id The ID of the institution to delete.
     * @return ResponseEntity indicating successful deletion.
     * @throws BaseException If an error occurs during deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> save(@PathVariable BigDecimal id) throws BaseException
    {
	institutionService.delete(id);
	return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint for finding an institution by its ID.
     *
     * @param id The ID of the institution to find.
     * @return ResponseEntity containing the institution details.
     * @throws BaseException If an error occurs during retrieval.
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Institution> findById(@PathVariable BigDecimal id) throws BaseException
    {
	return ResponseEntity.ok(institutionService.findById(id));
    }

    /**
     * Endpoint for finding all active institutions.
     *
     * @return ResponseEntity containing a list of active institutions.
     * @throws BaseException If an error occurs during retrieval.
     */
    @GetMapping("/find-all-active")
    public ResponseEntity<List<Institution>> findAllActive() throws BaseException
    {
	return ResponseEntity.ok(institutionService.findAllActive());
    }

    /**
     * Endpoint for finding all inactive institutions.
     *
     * @return ResponseEntity containing a list of inactive institutions.
     * @throws BaseException If an error occurs during retrieval.
     */
    @GetMapping("/find-all-inactive")
    public ResponseEntity<List<Institution>> findAllInActive() throws BaseException
    {
	return ResponseEntity.ok(institutionService.findAllInActive());
    }

    /**
     * Endpoint for searching institutions based on criteria.
     *
     * @param searchCriteria The criteria for searching institutions.
     * @return ResponseEntity containing a list of institutions matching the criteria.
     * @throws BaseException If an error occurs during retrieval.
     */
    @GetMapping("/search")
    public ResponseEntity<Iterable<Institution>> search(@RequestBody Institution searchCriteria) throws BaseException
    {
	return ResponseEntity.ok(institutionService.findByCriteria(searchCriteria));
    }
}
