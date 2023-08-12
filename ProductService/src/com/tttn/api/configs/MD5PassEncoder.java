package com.tttn.api.configs;

import org.springframework.security.crypto.password.PasswordEncoder;

// chưa dùng
public class MD5PassEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return HashUtils.encodeMD5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}

}
