package com.example.bucketList_app.Repository;

import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.User;

@Repository
public class LoginUserRepository {
    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setIcon(rs.getString("icon"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setGender(rs.getString("gender"));
        user.setRole(rs.getString("role"));
        return user;
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id,name,icon,age,email,password,gender,role FROM users WHERE email=:email";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        User user = jdbcTemplate.queryForObject(sql, param, USER_ROW_MAPPER);
        return Optional.ofNullable(user);
    }
}
