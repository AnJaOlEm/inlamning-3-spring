package me.code.uppgift3projekt.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordConfigTest {

    @Test
    public void testEncodedPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "test";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);

        boolean matches =  passwordEncoder.matches(rawPassword, encodedPassword);

        assertThat(matches).isTrue();
    }


}