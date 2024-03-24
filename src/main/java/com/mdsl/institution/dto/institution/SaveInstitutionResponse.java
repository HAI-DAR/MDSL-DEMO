package com.mdsl.institution.dto.institution;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveInstitutionResponse
{
    private BigDecimal id;

    private String institutionCode;
    
    private String institutionName;
}
