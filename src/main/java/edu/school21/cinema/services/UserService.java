package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    boolean signUp(String phoneNumber, String password, String firstName, String lastName);
    boolean signIn(String phoneNumber, String password);
    void updateProfile(User user);
    User getProfile(String phoneNumber);
}
