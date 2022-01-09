package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import edu.school21.cinema.models.UserRowMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;
    private final UserRowMapper rowMapper;

    public UserRepositoryImpl(JdbcTemplate template, UserRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public User getUserById(long id) {
        String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
        try {
            return template.queryForObject(SELECT_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        String SELECT_BY_PHONE_NUMBER = "SELECT * FROM users WHERE phone_number = ?";
        try {
            return template.queryForObject(SELECT_BY_PHONE_NUMBER, rowMapper, phoneNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        String SAVE_USER = "INSERT INTO users(first_name, last_name, phone_number, password) VALUES(?, ?, ?, ?)";
        try {
            template.execute(SAVE_USER, (PreparedStatementCallback<? extends Object>) ps -> {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhoneNumber());
                ps.setString(4, user.getPassword());
                return ps.execute();
            });
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ? WHERE id = ?";
        try {
            template.execute(UPDATE_USER, (PreparedStatementCallback<? extends Object>) ps -> {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhoneNumber());
                ps.setLong(4, user.getId());
                return ps.execute();
            });
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }
    }
}
