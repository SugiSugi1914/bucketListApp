package com.example.bucketList_app.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.User;

@Repository
public class UserRepository {

    // SQLクエリを実行するために使用
    @Autowired
    private NamedParameterJdbcTemplate template;

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

    public List<User> findAll() {
        String sql = "SELECT * FROM users ";
        List<User> userList = template.query(sql, USER_ROW_MAPPER);
        return userList;
    }

    public User findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        try {
            return template.queryForObject(sql, param, USER_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null; // 指定されたIDのユーザーが存在しない場合にnullを返す
        }
    }

    public void insert(User user) {
        String sql = "INSERT INTO users(name,icon,age,email,password,gender,role) VALUES(:name,:icon,:age,:email,:password,:gender,:role)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);

        template.update(sql, params);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public void update(User user) {
        String sql = "UPDATE users SET name=:name,icon=:icon,age=:age,email=:email,password=:password,gender=:gender,role=:role WHERE id=:id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        template.update(sql, param);
    }
}