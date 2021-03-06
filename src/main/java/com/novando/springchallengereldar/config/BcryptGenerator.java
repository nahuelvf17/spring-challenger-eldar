package com.novando.springchallengereldar.config;

import com.novando.springchallengereldar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptGenerator {

    @Autowired
    private UserRepository userRepository;

    public String passwordEncoder(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return  hashedPassword;
    }

    public Boolean passwordDecoder(String currentPassword, String ExistingPassword) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(currentPassword, ExistingPassword);
    }
}
