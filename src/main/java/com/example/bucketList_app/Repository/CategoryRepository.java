package com.example.bucketList_app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.Category;

@Repository
public class CategoryRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;
    
    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setCategory(rs.getString("category"));
        return category;
    };

    public Category findById(Integer id) {
        String sql = "SELECT category FROM category WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
        return category;
    }

    public List<Category> findAll() {
        String sql = "SELECT category FROM category";
        List<Category> categoryList = template.query(sql, CATEGORY_ROW_MAPPER);
        return categoryList;
    }
}
