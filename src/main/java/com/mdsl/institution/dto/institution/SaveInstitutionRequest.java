package com.mdsl.institution.dto.institution;

import java.math.BigDecimal;

import com.mdsl.institution.dto.common.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveInstitutionRequest extends BaseDto
{
    private static final long serialVersionUID = 1L;
    
    private BigDecimal id;

    private String institutionCode;
    
    private String institutionName;
   
}
