package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate template;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(int id) {
        String SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
        return template.queryForObject(SELECT_BY_ID, new UserMapper(), id);
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
        String SELECT_BY_PHONE_NUMBER = "SELECT * FROM users WHERE phone_number=?";
        return template.queryForObject(SELECT_BY_PHONE_NUMBER, new UserMapper(), phoneNumber);
    }


    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            try {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                return user;
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
    }

}
