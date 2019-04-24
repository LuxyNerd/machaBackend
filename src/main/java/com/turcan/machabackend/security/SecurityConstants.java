package com.turcan.machabackend.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days, TODO mybe 1 hour //300_000; //30 seconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user/register";
	// public static final String SIGN_UP_URLS = "/api/users/**";

}