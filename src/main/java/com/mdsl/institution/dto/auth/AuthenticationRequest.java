package com.mdsl.institution.dto.auth;

import java.util.Objects;

import com.mdsl.institution.dto.common.base.BaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest extends BaseDto
{
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @NotBlank(message = "Field 'email' cannot be empty")
    //@Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    
    @NotNull
    @NotBlank(message = "Field 'password' cannot be empty")
    private String password;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationRequest other = (AuthenticationRequest) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email);
		return result;
	}
}
