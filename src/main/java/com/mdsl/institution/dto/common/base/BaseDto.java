package com.mdsl.institution.dto.common.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	@Column(name = "authorized_by")
	private String authorizedBy;

	@Column(name = "authorized_date")
	private LocalDateTime authorizedDate;

	@Column(name = "record_status")
	private String recordStatus;

	public String getCreatedBy()
	{
	    return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
	    this.createdBy = createdBy;
	}

	public String getModifiedBy()
	{
	    return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
	    this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDate()
	{
	    return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate)
	{
	    this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate()
	{
	    return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate)
	{
	    this.modifiedDate = modifiedDate;
	}

	public String getAuthorizedBy()
	{
	    return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy)
	{
	    this.authorizedBy = authorizedBy;
	}

	public LocalDateTime getAuthorizedDate()
	{
	    return authorizedDate;
	}

	public void setAuthorizedDate(LocalDateTime authorizedDate)
	{
	    this.authorizedDate = authorizedDate;
	}

	public String getRecordStatus()
	{
	    return recordStatus;
	}

	public void setRecordStatus(String recordStatus)
	{
	    this.recordStatus = recordStatus;
	}

}
