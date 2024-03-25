package com.mdsl.institution.domain.user;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mdsl.institution.domain.common.LoginLanguage;
import com.mdsl.institution.domain.common.base.BaseEntity;
import com.mdsl.institution.domain.token.Token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing users.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mdsl_users")
public class User extends BaseEntity implements UserDetails
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user_id_generator")
    @SequenceGenerator(name = "sq_user_id_generator", sequenceName = "sq_user_id", allocationSize = 1)
    @Column(name = "id", precision = 20, scale = 0)
    private BigDecimal id;

    @NotNull
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private LoginLanguage loginLang;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
	return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    // @JsonIgnore
    public String getPassword()
    {
	return password;
    }

    @Override
    public String getUsername()
    {
	return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
	return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
	return true;
    }

    @Override
    public boolean isEnabled()
    {
	return true;
    }

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
	User other = (User) obj;
	return Objects.equals(email, other.email);
    }

    /**
     * Overrides hashCode method.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
	return Objects.hash(email);
    }

}
