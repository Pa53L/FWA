package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public boolean signUp(String phoneNumber, String password, String firstName, String lastName, String address) {
        if (!phoneNumber.isEmpty() && !password.isEmpty()) {
            String tmpNumber = clearPhoneNumber(phoneNumber);
            if (repository.getUserByPhoneNumber(tmpNumber) == null) {
                User user = new User();
                user.setPhoneNumber(tmpNumber);
                user.setPassword(encoder.encode(password));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                repository.saveUser(user);
                repository.addSignUpInfo(user, address);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signIn(String phoneNumber, String password, String address) {
        if (!phoneNumber.isEmpty() && !password.isEmpty()) {
            User user = repository.getUserByPhoneNumber(clearPhoneNumber(phoneNumber));
            if (user != null) repository.addSignInInfo(user, address);
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
        return repository.getUserByPhoneNumber(clearPhoneNumber(phoneNumber));
    }

    private String clearPhoneNumber(String phoneNumber) {
        String tmpNumber = phoneNumber.replace("-", "").replace("(", "").replace(")", "").replace("+", "").replaceAll("\\s+", "");
        if (tmpNumber.startsWith("8") || tmpNumber.startsWith("7")) {
            if (tmpNumber.startsWith("8")) {
                tmpNumber = tmpNumber.replaceFirst("8", "");
            } else if (tmpNumber.startsWith("7")) {
               tmpNumber = tmpNumber.replaceFirst("7", "");
            }
        }
        return tmpNumber;
    }

    @Override
    public List<AuthHistory> getAuth(String login) {
        return repository.getAuthInfo(login);
    }
}
