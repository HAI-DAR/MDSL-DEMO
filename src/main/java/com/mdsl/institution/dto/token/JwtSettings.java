package com.mdsl.institution.dto.token;

public class JwtSettings
{
    private String secretKey;
    private Long expiration;
    private RefreshToken refreshToken;

    public static class RefreshToken
    {
	private Long expiration;

	public Long getExpiration()
	{
	    return expiration;
	}

	public void setExpiration(Long expiration)
	{
	    this.expiration = expiration;
	}
    }

    public String getSecretKey()
    {
	return secretKey;
    }

    public void setSecretKey(String secretKey)
    {
	this.secretKey = secretKey;
    }

    public RefreshToken getRefreshToken()
    {
	return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken)
    {
	this.refreshToken = refreshToken;
    }

    public Long getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Long expiration)
    {
	this.expiration = expiration;
    }
}
