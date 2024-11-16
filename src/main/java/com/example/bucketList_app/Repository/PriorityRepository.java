package com.example.bucketList_app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.Priority;

@Repository
public class PriorityRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Priority> PRIORITY_ROW_MAPPER = (rs, i) -> {
        Priority priority = new Priority();
        priority.setId(rs.getInt("id"));
        priority.setPriority(rs.getString("priority"));
        return priority;
    };

    public Priority findById(Integer id) {
        String sql = "SELECT id, priority FROM priority WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Priority priority = template.queryForObject(sql, param, PRIORITY_ROW_MAPPER);
        return priority;
    }

    public List<Priority> findAll() {
        String sql = "SELECT id,priority FROM priority";
        List<Priority> priorityList = template.query(sql, PRIORITY_ROW_MAPPER);
        return priorityList;
    }
}
