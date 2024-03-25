package com.mdsl.institution.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mdsl.institution.domain.businessmessage.BusinessMessage;
import com.mdsl.institution.domain.businessmessage.CustomHttpStatus;
import com.mdsl.institution.domain.common.LoginLanguage;
import com.mdsl.institution.dto.error.ApiErrorResponse;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.exception.BusinessException;
import com.mdsl.institution.service.businessmessage.BusinessMessageService;
import com.mdsl.institution.util.SecurityUtil;
import com.mdsl.institution.util.StringUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Global exception handler for handling exceptions across the application.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final BusinessMessageService businessMessageService;

    /**
     * Handles BusinessException and constructs a response with business error details.
     *
     * @param ex The BusinessException instance.
     * @param request The HttpServletRequest.
     * @return ResponseEntity containing the constructed error response.
     * @throws BaseException If an error occurs during construction.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request)
	    throws BaseException
    {
	log.error(ex.getMessage(), ex);
	return constructTranslatedErrorResponse(CustomHttpStatus.BUSINESS_ERROR.value(), ex.getMsgCode(),
		ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles generic exceptions and constructs a generic error response.
     *
     * @param ex The Exception instance.
     * @param request The HttpServletRequest.
     * @return ResponseEntity containing the constructed error response.
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request)
    {
	log.error(ex.getMessage(), ex);
	return constructGenericErrorResponse(ex.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ApiErrorResponse> constructGenericErrorResponse(String errorMessage, String path)
    {
	ApiErrorResponse response = new ApiErrorResponse();
	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	response.setErrorTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	response.setErrorDesc(errorMessage);
	response.setPath(path);

	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }

    private ResponseEntity<ApiErrorResponse> constructTranslatedErrorResponse(Integer statusCode, Integer msgCode,
	    String msgDesc, String path) throws BaseException
    {
	ApiErrorResponse response = new ApiErrorResponse();

	response.setStatus(statusCode);
	response.setPath(path);
	response.setErrorCode(msgCode);

	response = appendTranslatedMessageData(response, msgCode, msgDesc);

	return ResponseEntity.status(statusCode).body(response);
    }

    private ApiErrorResponse appendTranslatedMessageData(ApiErrorResponse response, Integer msgCode, String msgDesc)
	    throws BaseException
    {

	LoginLanguage lang = SecurityUtil.getLoginLanguage();

	BusinessMessage message = businessMessageService.returnTranslatedOrDefaultMessage(msgCode);

	switch (lang)
	{
	    case ENGLISH ->
	    {
		response.setErrorTitle(message.getMsgTitleEn());
		response.setErrorDesc(message.getMsgDescriptionEn());
	    }
	    case ARABIC ->
	    {
		response.setErrorTitle(StringUtil.nullEmptyToValue(message.getMsgTitleAr(), message.getMsgTitleEn()));
		response.setErrorDesc(
			StringUtil.nullEmptyToValue(message.getMsgDescriptionAr(), message.getMsgDescriptionEn()));
	    }
	    case FRENCH ->
	    {
		response.setErrorTitle(StringUtil.nullEmptyToValue(message.getMsgTitleFr(), message.getMsgTitleEn()));
		response.setErrorDesc(
			StringUtil.nullEmptyToValue(message.getMsgDescriptionFr(), message.getMsgDescriptionEn()));
	    }
	    default ->
	    {
		response.setErrorTitle(message.getMsgTitleEn());
		response.setErrorDesc(message.getMsgDescriptionEn());
	    }
	}
	;

	return response;
    }

}
