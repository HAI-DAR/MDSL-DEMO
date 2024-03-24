package com.mdsl.institution.domain.token;

import java.math.BigDecimal;
import java.util.Objects;

import com.mdsl.institution.domain.common.base.BaseEntity;
import com.mdsl.institution.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mdsl_tokens")
public class Token extends BaseEntity {

    	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_token_id_generator")
	@SequenceGenerator(name = "sq_token_id_generator", sequenceName = "sq_token_id", allocationSize = 1)
	@Column(precision = 20, scale = 0)
    	private BigDecimal id;

	@Column(unique = true)
	private String token;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private TokenType tokenType = TokenType.BEARER;

	private boolean revoked;

	private boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public boolean equals(Object obj)
	{
	    if(this == obj)
		return true;
	    if(obj == null)
		return false;
	    if(getClass() != obj.getClass())
		return false;
	    Token other = (Token) obj;
	    return Objects.equals(token, other.token);
	}

	@Override
	public int hashCode()
	{
	    return Objects.hash(token);
	}
}
