package com.mdsl.institution.api.instituion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.mdsl.institution.MDSLInstitutionApplication;
import com.mdsl.institution.configuration.SecurityConfigurationIT;
import com.mdsl.institution.dto.institution.SaveInstitutionRequest;
import com.mdsl.institution.dto.institution.SaveInstitutionResponse;
import com.mdsl.institution.util.DateUtil;

@TestPropertySource( locations = { "classpath:application-test.yml" } )
@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, classes = MDSLInstitutionApplication.class )
@Import(SecurityConfigurationIT.class)
public class InstitutionControllerIT
{

    @Autowired
    private TestRestTemplate restTemplate;
    
    private static final String URL = "/v1/institution";
    
    @Test
    public void testSubmit() throws Exception
    {

	SaveInstitutionRequest request = SaveInstitutionRequest.builder()
					.institutionCode("010")
					.institutionName("form UnitTest")
					.createdDate(DateUtil.returnCurrentDate())
					.build();
	
	ResponseEntity<SaveInstitutionResponse> response = restTemplate
		.postForEntity(
		URL + "/submit",
		request, 
		SaveInstitutionResponse.class
		);
	
	assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("010", response.getBody().getInstitutionCode());
        assertEquals("form UnitTest", response.getBody().getInstitutionName());
    }
}
