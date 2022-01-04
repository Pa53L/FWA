package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UserRepository {
    User getUserById(long id);
    User getUserByPhoneNumber(String phoneNumber);
    void saveUser(User user);
    void updateUser(User user);
}
