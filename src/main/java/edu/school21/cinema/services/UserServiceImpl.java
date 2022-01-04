package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public boolean signUp(String phoneNumber, String password, String firstName, String lastName) {
        if (!phoneNumber.isEmpty() && !password.isEmpty()) {
            if (repository.getUserByPhoneNumber(phoneNumber) == null) {
                User user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setPassword(encoder.encode(password));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                repository.saveUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signIn(String phoneNumber, String password) {
        if (!phoneNumber.isEmpty() && !password.isEmpty()) {
            User user = repository.getUserByPhoneNumber(phoneNumber);
            return user != null && encoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public void updateProfile(User user) {
        repository.updateUser(user);
    }

    @Override
    public User getProfile(String phoneNumber) {
        return repository.getUserByPhoneNumber(phoneNumber);
    }
}
