package com.mdsl.institution.dto.institution;

import java.math.BigDecimal;
import java.util.Objects;

import com.mdsl.institution.dto.common.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SaveInstitutionRequest extends BaseDto
{
    private static final long serialVersionUID = 1L;
    
    private BigDecimal id;

    private String institutionCode;
    
    private String institutionName;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveInstitutionRequest other = (SaveInstitutionRequest) obj;
		return Objects.equals(institutionCode, other.institutionCode);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(institutionCode);
		return result;
	}
   
}
