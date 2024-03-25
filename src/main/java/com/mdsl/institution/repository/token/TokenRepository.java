package com.mdsl.institution.repository.token;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mdsl.institution.domain.token.Token;
import com.mdsl.institution.domain.user.User;

/**
 * Repository interface for accessing tokens.
 */
public interface TokenRepository extends JpaRepository<Token, BigDecimal>
{

    /**
     * Finds all valid tokens for a user.
     *
     * @param id The ID of the user.
     * @return A list of valid tokens for the user.
     */
    @Query(value = """
    	select t from Token t inner join User u\s
    	on t.user.id = u.id\s
    	where u.id = :id and (t.expired = false or t.revoked = false)\s
    	""")
    List<Token> findAllValidTokenByUser(BigDecimal id);

    /**
     * Finds a token by its token value.
     *
     * @param token The token value.
     * @return An optional containing the token if found, empty otherwise.
     */
    Optional<Token> findByToken(String token);

    /**
     * Deletes tokens associated with a user.
     *
     * @param user The user whose tokens are to be deleted.
     */
    void deleteByUser(User user);
}
