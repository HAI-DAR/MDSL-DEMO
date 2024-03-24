package com.mdsl.institution.domain.businessmessage;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mdsl_business_messages")
public class BusinessMessage
{
    
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
    
    @NotNull
    @Column(name = "msg_title_ar", nullable = true, length = 255)
    private String msgTitleAr;
    
    @NotNull
    @Column(name = "msg_desc_ar", nullable = true, length = 1000)
    private String msgDescriptionAr;
    
    @NotNull
    @Column(name = "msg_title_fr", nullable = true, length = 255)
    private String msgTitleFr;
    
    @NotNull
    @Column(name = "msg_desc_fr", nullable = true, length = 1000)
    private String msgDescriptionFr;
    
}
