package com.aaa.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }
}
