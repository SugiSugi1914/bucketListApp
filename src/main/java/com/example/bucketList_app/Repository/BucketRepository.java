package com.example.bucketList_app.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Domain.Category;
import com.example.bucketList_app.Domain.Priority;
import com.example.bucketList_app.Domain.User;


@Repository
public class BucketRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Bucket> BUCKET_ROW_MAPPER = (rs, i) -> {
        Bucket bucket = new Bucket();
        bucket.setId(rs.getInt("b_id"));
        bucket.setTitle(rs.getString("b_title"));
        bucket.setImage(rs.getString("b_image"));
        bucket.setBudjet(rs.getInt("b_budjet"));
        bucket.setDueDate(rs.getDate("b_due_date").toLocalDate());
        bucket.setUrl(rs.getString("b_url"));
        bucket.setMemo(rs.getString("b_memo"));
        bucket.setCreationDate(rs.getDate("b_creation_date").toLocalDate());
        bucket.setAchevement(rs.getBoolean("b_achevement"));

        Category category = new Category();
            category.setId(rs.getInt("c_id"));
            category.setCategory(rs.getString("c_category"));

        User user = new User();
            user.setId(rs.getInt("u_id"));
            user.setName(rs.getString("u_name"));
            user.setAge(rs.getInt("u_age"));
            user.setEmail(rs.getString("u_email"));
            user.setPassword(rs.getString("u_password"));
            user.setGender (rs.getString("u_gender"));
            List<String> roleList = new ArrayList<>();
            String role = rs.getString("u_role");
            roleList.add(role);
            user.setRole(roleList); 
            user.setIcon(rs.getString("u_icon"));
        
        Priority priority = new Priority();
            priority.setId(rs.getInt("p_id"));
            priority.setPriority(rs.getString("p_priority"));
            
        return bucket;
    };

    public Bucket findById(Integer id) {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    WHERE b.id = :id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Bucket bucket = template.queryForObject(sql, param, BUCKET_ROW_MAPPER);
        return bucket;
    }

    public List<Bucket> findByPriorityId(Integer priorityId) {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    WHERE priority_id = :priorityId
                """;
        SqlParameterSource param = new MapSqlParameterSource("priorityId", priorityId);
        List<Bucket> bucketList = template.query(sql, param, BUCKET_ROW_MAPPER);
        return bucketList;
    }
    
    public List<Bucket> findByCategoryId(Integer categoryId) {
                String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    WHERE category_id = :categoryId
                """;
        SqlParameterSource param = new MapSqlParameterSource("categoryId", categoryId);
        List<Bucket> bucketList = template.query(sql, param, BUCKET_ROW_MAPPER);
        return bucketList;
    }

    public List<Bucket> findAll() {
                String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    }

    //※優先度が低い順で全件取得
    public List<Bucket> findAllByPriorityASC() {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    ORDER BY priority_id ASC
                """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    }  

    //※優先度が高い順で全件取得
    public List<Bucket> findAllByPriorityDESC() {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    ORDER BY priority_id DESC
                """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    }

    //※予算が安い順で全件取得
    public List<Bucket> findByBudjetASC() {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    ORDER BY budjet ASC
                """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    }  

    //※予算が高い順で全件取得
    public List<Bucket> findByBudjetDESC() {
        String sql = """
                    SELECT
                        b.id AS b_id
                        b.title AS b_title,
                        b.image AS b_image,
                        b.budjet AS b_budjet,
                        b.due_date AS b_due_date,
                        b.url AS b_url,
                        b.memo AS b_memo,
                        b.creation_date AS b_creation_date,
                        b.achevement AS b_achevement,

                        c.id AS c_id
                        c.category AS c_category,

                        u.id AS u_id,
                        u.name AS u_name,
                        u.age AS u_age,
                        u.email AS u_email,
                        u.password AS u_password,
                        u.gender AS u_gender,
                        u.role AS u_role,
                        u.icon AS u_icon,

                        p.id AS p_id,
                        p.priority AS p_priority,

                    FROM bucket AS b 
                    LEFT OUTER JOIN category AS c
                    ON b.category_id = c.id 
                    LEFT OUTER JOIN users AS u
                    ON b.user_id = u.id
                    LEFT OUTER JOIN priority AS p
                    ON b.priority_id = p.id
                    ORDER BY budjet DESC
                """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    }  

    //※期日が近い順で全件取得
    public List<Bucket> findByDueDateASC() {
        String sql = """
            SELECT
                b.id AS b_id
                b.title AS b_title,
                b.image AS b_image,
                b.budjet AS b_budjet,
                b.due_date AS b_due_date,
                b.url AS b_url,
                b.memo AS b_memo,
                b.creation_date AS b_creation_date,
                b.achevement AS b_achevement,

                c.id AS c_id
                c.category AS c_category,

                u.id AS u_id,
                u.name AS u_name,
                u.age AS u_age,
                u.email AS u_email,
                u.password AS u_password,
                u.gender AS u_gender,
                u.role AS u_role,
                u.icon AS u_icon,

                p.id AS p_id,
                p.priority AS p_priority,

            FROM bucket AS b 
            LEFT OUTER JOIN category AS c
            ON b.category_id = c.id 
            LEFT OUTER JOIN users AS u
            ON b.user_id = u.id
            LEFT OUTER JOIN priority AS p
            ON b.priority_id = p.id
            ORDER BY due_date ASC
        """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;
    } 

    //※期日が遠い順で全件取得
    public List<Bucket> findByDueDateDESC() {
        String sql = """
            SELECT
                b.id AS b_id
                b.title AS b_title,
                b.image AS b_image,
                b.budjet AS b_budjet,
                b.due_date AS b_due_date,
                b.url AS b_url,
                b.memo AS b_memo,
                b.creation_date AS b_creation_date,
                b.achevement AS b_achevement,

                c.id AS c_id
                c.category AS c_category,

                u.id AS u_id,
                u.name AS u_name,
                u.age AS u_age,
                u.email AS u_email,
                u.password AS u_password,
                u.gender AS u_gender,
                u.role AS u_role,
                u.icon AS u_icon,

                p.id AS p_id,
                p.priority AS p_priority,

            FROM bucket AS b 
            LEFT OUTER JOIN category AS c
            ON b.category_id = c.id 
            LEFT OUTER JOIN users AS u
            ON b.user_id = u.id
            LEFT OUTER JOIN priority AS p
            ON b.priority_id = p.id
            ORDER BY due_date DESC
        """;
        List<Bucket> bucketList = template.query(sql, BUCKET_ROW_MAPPER);
        return bucketList;    
    }  

    public void insert(Bucket bucket) {
        String sql = """
                    INSERT INTO bucket(id, title, image, category_id, user_id, budjet, due_date, priority_id, url, memo, creation_date, achievement)
                                VALUSE(:id, :title, :image, :category, :user, :budjet, :dueDate, :priority, :url, :memo, :creationDate, :achievement)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(bucket);
        template.update(sql, param);
    }

    public void update(Bucket bucket) {
        String sql = """
                    UPDATE bucket
                    SET id=:id, title=:title, image=:image, category_id=:category, user_id=:user, budjet=:budjet, due_date=:dueDate, priority=:priority, url=:url, memo=:memo, creation_date=:creationDate, achievement=:achievement)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(bucket);
        template.update(sql, param);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM bucket WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
