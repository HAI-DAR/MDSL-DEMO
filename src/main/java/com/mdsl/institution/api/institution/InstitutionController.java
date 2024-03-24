package com.mdsl.institution.api.institution;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@RestController
@RequestMapping("/v1/institution")
@RequiredArgsConstructor
public class InstitutionController
{
    private final InstitutionService institutionService;

    @PostMapping("/submit")
    public ResponseEntity<SaveInstitutionResponse> submit(@RequestBody SaveInstitutionRequest request) throws BaseException
    {
	SaveInstitutionResponse response = new SaveInstitutionResponse();
	
	if(null == request.getId())
	    response = institutionService.save(request);
	else
	    response = institutionService.update(request);
	
	return ResponseEntity.ok(response);
    }
    
    @PostMapping("/test")
    public ResponseEntity<Void> test() throws BaseException
    {
	Set<Institution> set = new HashSet<Institution>();
	
	Institution obj1 = Institution.builder().institutionCode("001").build();
	Institution obj2 = Institution.builder().institutionCode("001").build();
	
	set.add(obj1);
	set.add(obj2);
	
	set.size();
	
	return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> save(@PathVariable BigDecimal id) throws BaseException
    {
	institutionService.delete(id);
	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Institution> findById(@PathVariable BigDecimal id) throws BaseException
    {
	return ResponseEntity.ok(institutionService.findById(id));
    }
    
    @GetMapping("/find-all-active")
    public ResponseEntity<List<Institution>> findAllActive() throws BaseException
    {
	return ResponseEntity.ok(institutionService.findAllActive());
    }
    
    @GetMapping("/find-all-inactive")
    public ResponseEntity<List<Institution>> findAllInActive() throws BaseException
    {
	return ResponseEntity.ok(institutionService.findAllInActive());
    }
    
    @GetMapping("/search")
    public ResponseEntity<Iterable<Institution>> search(@RequestBody Institution searchCriteria) throws BaseException
    {
	return ResponseEntity.ok(institutionService.findByCriteria(searchCriteria));
    }
}
