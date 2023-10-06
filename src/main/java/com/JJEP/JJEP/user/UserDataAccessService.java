package com.JJEP.JJEP.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(User user) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public User selectUserById(int id) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public User selectUserByUsername(String username) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public User selectUserByEmail(String email) {
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
