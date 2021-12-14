package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UserRepository {
    User getUserById(int id);
    void save(User user);
    User getByPhoneNumber(String phoneNumber);
}
