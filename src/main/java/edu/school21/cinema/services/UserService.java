package edu.school21.cinema.services;

import edu.school21.cinema.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
}
