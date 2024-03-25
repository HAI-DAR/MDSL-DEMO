package com.mdsl.institution.api.instituion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdsl.institution.service.institution.InstitutionService;
import com.mdsl.institution.util.CommonUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class InstitutionControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstitutionService institutionService;

    private static String _token;

    private static ObjectMapper objectMapper = CommonUtils.OBJECT_MAPPER;

    @Test
    public void testSubmit() throws Exception
    {

//	SaveInstitutionRequest request = SaveInstitutionRequest.builder()
//					.institutionCode("010")
//					.institutionName("form UnitTest")
//					.build();
//
//	request.setCreatedDate(DateUtil.returnCurrentDate());
//
//	SaveInstitutionResponse expectedResponse = CommonUtils.convert(request, SaveInstitutionResponse.class);
//	expectedResponse.setId(BigDecimal.ONE);
//	
//	expectedResponse.setCreatedDate(request.getCreatedDate());
//	
//	Mockito.when(institutionService.save(request)).thenReturn(expectedResponse);
//
//	
//	mockMvc.perform(post("/v1/institution/submit")
//		.contentType(MediaType.APPLICATION_JSON)
//		.header(HttpHeaders.AUTHORIZATION, "Bearer " + _token)
//		.content(objectMapper.writeValueAsString(request)))
//		.andExpect(status().isOk())
//		.andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void testDelete() throws Exception
    {
//	SaveInstitutionRequest createRequest = SaveInstitutionRequest.builder()
//		.institutionCode("020")
//		.institutionName("form UnitTest")
//		.build();
//	
//	SaveInstitutionResponse createdInstitution = CommonUtils.convert(createRequest, SaveInstitutionResponse.class);
//	createdInstitution.setId(BigDecimal.ONE);
//	
//	Mockito.when(institutionService.save(createRequest)).thenReturn(createdInstitution);
//
//	MvcResult createResult = mockMvc.perform(post("/v1/institution/submit")
//	        .contentType(MediaType.APPLICATION_JSON)
//	        .header(HttpHeaders.AUTHORIZATION, "Bearer " + _token)
//	        .content(objectMapper.writeValueAsString(createRequest)))
//	        .andExpect(status().isOk())
//	        .andReturn();
//	
//	String createResponse = createResult.getResponse().getContentAsString();
//	createdInstitution = objectMapper.readValue(createResponse, SaveInstitutionResponse.class);
//
//	BigDecimal id = createdInstitution.getId();
//	
//	mockMvc.perform(delete("/v1/institution/delete/" + id)
//		.header(HttpHeaders.AUTHORIZATION, "Bearer " + _token))
//               .andExpect(status().isNoContent());
//	
//	 mockMvc.perform(get("/v1/institution/find-all-inactive")
//	        .contentType(MediaType.APPLICATION_JSON)
//	        .header(HttpHeaders.AUTHORIZATION, "Bearer " + _token))
//	        .andExpect(status().isOk())
//	        .andExpect(result -> {
//	            String inActiveListResultString = result.getResponse().getContentAsString();
//	    	    List<Institution> inActiveList = new ArrayList<Institution>();
//	    	    inActiveList = (List<Institution>) objectMapper.readValue(inActiveListResultString, (TypeReference<Institution>) inActiveList );
//	    	    assertEquals(inActiveList.size() , 1);
//	        });
//	

    }

    @Test
    public void testSubmitDuplicate() throws Exception
    {
//	SaveInstitutionRequest request = SaveInstitutionRequest.builder()
//					.institutionCode("010")
//					.institutionName("form UnitTest")
//					.build();
//	
//	request.setCreatedDate(DateUtil.returnCurrentDate());
//
//	SaveInstitutionResponse expectedResponse = CommonUtils.convert(request, SaveInstitutionResponse.class);
//	expectedResponse.setId(BigDecimal.ONE);
//	
//	Mockito.when(institutionService.save(request)).thenReturn(expectedResponse)
//		.thenThrow(new BusinessException(MessageCodes.DUPLICATE_RECORD.msgCode()));
//
//	mockMvc.perform(post("/v1/institution/submit")
//		.contentType(MediaType.APPLICATION_JSON)
//		.header(HttpHeaders.AUTHORIZATION, "Bearer " + _token)
//		.content(objectMapper.writeValueAsString(request)))
//		.andExpect(status().isOk())
//		.andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
//
//	
//	mockMvc.perform(post("/v1/institution/submit")
//		.contentType(MediaType.APPLICATION_JSON)
//		.header(HttpHeaders.AUTHORIZATION, "Bearer " + _token)
//		.content(objectMapper.writeValueAsString(request)))
//		.andExpect(status().is(CustomHttpStatus.BUSINESS_ERROR.value()))
//		.andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
//		.andExpect(result -> {
//		    BusinessException ex = (BusinessException) result.getResolvedException();
//		    assertEquals(MessageCodes.DUPLICATE_RECORD.msgCode(), ex.getMsgCode());
//		});
    }

    @Autowired
    private void setToken() throws Exception
    {
//	if( null == _token || _token.isEmpty() )
//	{
//	    AuthenticationRequest request = new AuthenticationRequest();
//		request.setEmail("test@gmail.com");
//		request.setPassword("123");
//
//		MvcResult result = mockMvc.perform(post("/v1/auth/login")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(request)))
//				.andExpect(status().isOk())
//				.andReturn();
//
//		String response = result.getResponse().getContentAsString();
//		AuthenticationResponse authResponse = objectMapper.readValue(response, AuthenticationResponse.class);
//
//		_token = authResponse.getAccessToken();  
//	}
	
    }

}
