package com.mdsl.institution.domain.businessmessage;

import java.math.BigDecimal;
import java.util.Objects;

import com.mdsl.institution.domain.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * Entity class representing business messages.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mdsl_business_messages")
public class BusinessMessage extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with message code, title, and description.
     *
     * @param msgCode       The message code.
     * @param msgTitle      The message title.
     * @param msgDesc       The message description.
     */
    public BusinessMessage(Integer msgCode, String msgTitle, String msgDesc)
    {
	// Default Constructor 
	this.msgCode = msgCode;
	this.msgTitleEn = msgTitle;
	this.msgDescriptionEn = msgDesc;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_bus_msg_id_generator")
    @SequenceGenerator(name = "sq_bus_msg_id_generator", sequenceName = "sq_bus_msg_id", allocationSize = 1)
    @Column(precision = 20, scale = 0)
    private BigDecimal id;

    @NotNull
    @Column(name = "msg_code", nullable = false, length = 20)
    private Integer msgCode;
    
    @NotNull
    @Column(name = "msg_title_en", nullable = false, length = 255)
    private String msgTitleEn;
    
    @NotNull
    @Column(name = "msg_desc_en", nullable = false, length = 1000)
    private String msgDescriptionEn;
    
    @Column(name = "msg_title_ar", nullable = true, length = 255)
    private String msgTitleAr;
    
    @Column(name = "msg_desc_ar", nullable = true, length = 1000)
    private String msgDescriptionAr;
    
    @Column(name = "msg_title_fr", nullable = true, length = 255)
    private String msgTitleFr;
    
    @Column(name = "msg_desc_fr", nullable = true, length = 1000)
    private String msgDescriptionFr;

    /**
     * Overrides equals method.
     *
     * @param obj The object to compare.
     * @return True if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	BusinessMessage other = (BusinessMessage) obj;
	return Objects.equals(msgCode, other.msgCode);
    }


    /**
     * Overrides hashCode method.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(msgCode);
	return result;
    }
    
}
