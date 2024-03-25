package com.mdsl.institution.domain.institution;

import java.math.BigDecimal;
import java.util.Objects;

import com.mdsl.institution.domain.common.base.BaseEntity;
import com.mdsl.institution.repository.common.MDSLEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing institutions.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mdsl_institutions")
@EntityListeners(MDSLEntityListener.class)
public class Institution extends BaseEntity
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_institution_id_generator")
    @SequenceGenerator(name = "sq_institution_id_generator", sequenceName = "sq_institution_id", allocationSize = 1)
    @Column(precision = 20, scale = 0)
    private BigDecimal id;

    @Column(name = "institution_code", nullable = false, length = 5)
    private String institutionCode;

    @Column(name = "institution_name", nullable = false, length = 50)
    private String institutionName;

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
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	Institution other = (Institution) obj;
	return Objects.equals(institutionCode, other.institutionCode);
    }

    /**
     * Overrides hashCode method.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
	return Objects.hash(institutionCode);
    }

}
