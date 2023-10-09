package com.JJEP.JJEP.user;

import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;

    public UserDataAccessService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public int insertUser(User user) {
        String sql = """
                     INSERT INTO users (username, email, password, full_name)
                     VALUES (?, ?, ?, ?)
                     """;
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getFullName());
    }

    @Override
    public Optional<User> selectUserById(int id) {
        String sql = """
                     SELECT * FROM users
                     WHERE id = ?
                     """;

        return jdbcTemplate.query(sql, new UserMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int updateUserById(int id, User user) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int deleteUserById(int id) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
