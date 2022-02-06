package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;

public interface UserService {
    boolean signUp(String phoneNumber, String password, String firstName, String lastName, String address);
    boolean signIn(String phoneNumber, String password, String address);
    void updateProfile(User user);
    User getProfile(String phoneNumber);

    List<AuthHistory> getAuth(String phoneNumber);
}
