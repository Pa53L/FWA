package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public User getUserById(int id) {
        String SELECT_BY_ID = "SELECT * FROM users WHERE Id = ?";
        return template.queryForObject(SELECT_BY_ID, this::mapRowToUser, id);
    }

    @Override
    public void save(User user) {
        String UPDATE_USER = "INSERT INTO users(first_name, last_name, phone_number, password) " +
                "VALUES(?, ?, ?, ?)";
        template.update(UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getPassword());
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return null;
    }

    private User mapRowToUser(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setFirstName(resultSet.getString("last_name"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }

}
