package com.example.bucketList_app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Domain.Report;
import com.example.bucketList_app.Domain.User;

@Repository
public class ReportRepository {
    @Autowired
    NamedParameterJdbcTemplate template;

    private static final RowMapper<Report> REPORT_ROW_MAPPER = (rs, i) -> {
        Report report = new Report();
        report.setId(rs.getInt("r_id"));
        report.setReport(rs.getString("r_report"));
        report.setReportCategoryId(rs.getInt("r_report_category_id"));

        Bucket bucket = new Bucket();
        bucket.setId(rs.getInt("b_id"));
        bucket.setTitle(rs.getString("b_title"));
        bucket.setImage(rs.getString("b_image"));
        bucket.setBudget(rs.getInt("b_budget"));
        bucket.setDueDate(rs.getDate("b_dueDate").toLocalDate());
        bucket.setUrl(rs.getString("b_url"));
        bucket.setMemo(rs.getString("b_memo"));
        bucket.setCreationDate(rs.getDate("b_creationDate").toLocalDate());
        bucket.setAchievement(rs.getBoolean("b_achievement"));
        bucket.setPermission(rs.getBoolean("b_permission"));
        report.setReportBucket(bucket);

        User ur_user = new User();
        ur_user.setId(rs.getInt("ur_id"));
        ur_user.setName(rs.getString("ur_name"));
        ur_user.setAge(rs.getInt("ur_age"));
        ur_user.setEmail(rs.getString("ur_email"));
        ur_user.setPassword(rs.getString("ur_password"));
        ur_user.setGender(rs.getString("ur_gender"));
        ur_user.setRole(rs.getString("ur_role"));
        ur_user.setIcon(rs.getString("ur_icon"));
        report.setReportUser(ur_user);

        User us_user = new User();
        us_user.setId(rs.getInt("us_id"));
        us_user.setName(rs.getString("us_name"));
        us_user.setAge(rs.getInt("us_age"));
        us_user.setEmail(rs.getString("us_email"));
        us_user.setPassword(rs.getString("us_password"));
        us_user.setGender(rs.getString("us_gender"));
        us_user.setRole(rs.getString("us_role"));
        us_user.setIcon(rs.getString("us_icon"));
        report.setSuspicionUser(us_user);

        return report;
    };

    public List<Report> findAllExceptionCategoryAndPriority() {
        String sql = """
                SELECT
                    r.id AS r_id,
                    r.report AS r_report,
                    r.report_category_id AS r_report_category_id,

                    b.id AS b_id,
                    b.title AS b_title,
                    b.image AS b_image,
                    b.budget AS b_budget,
                    b.due_date AS b_dueDate,
                    b.url AS b_url,
                    b.memo AS b_memo,
                    b.creation_date AS b_creationDate,
                    b.achievement AS b_achievement,
                    b.permission AS b_permission,

                    ur.id AS ur_id,
                    ur.name AS ur_name,
                    ur.age AS ur_age,
                    ur.email AS ur_email,
                    ur.password AS ur_password,
                    ur.gender AS ur_gender,
                    ur.role AS ur_role,
                    ur.icon AS ur_icon,

                    us.id AS us_id,
                    us.name AS us_name,
                    us.age AS us_age,
                    us.email AS us_email,
                    us.password AS us_password,
                    us.gender AS us_gender,
                    us.role AS us_role,
                    us.icon AS us_icon



                FROM report AS r
                JOIN bucket AS b
                ON r.report_bucket_id = b.id
                JOIN users AS ur
                ON r.report_user_id = ur.id
                JOIN users AS us
                ON r.report_user_id = us.id
                        """;
        List<Report> reportList = template.query(sql, REPORT_ROW_MAPPER);
        return reportList;
    }

    public Report findById(Integer id) {
        String sql = """
                SELECT
                    r.id AS r_id,
                    r.report AS r_report,
                    r.report_category_id AS r_report_category_id,

                    b.id AS b_id,
                    b.title AS b_title,
                    b.image AS b_image,
                    b.budget AS b_budget,
                    b.due_date AS b_dueDate,
                    b.url AS b_url,
                    b.memo AS b_memo,
                    b.creation_date AS b_creationDate,
                    b.achievement AS b_achievement,
                    b.permission AS b_permission,

                    ur.id AS ur_id,
                    ur.name AS ur_name,
                    ur.age AS ur_age,
                    ur.email AS ur_email,
                    ur.password AS ur_password,
                    ur.gender AS ur_gender,
                    ur.role AS ur_role,
                    ur.icon AS ur_icon,

                    us.id AS us_id,
                    us.name AS us_name,
                    us.age AS us_age,
                    us.email AS us_email,
                    us.password AS us_password,
                    us.gender AS us_gender,
                    us.role AS us_role,
                    us.icon AS us_icon



                FROM report AS r
                JOIN bucket AS b
                ON r.report_bucket_id = b.id
                JOIN users AS ur
                ON r.report_user_id = ur.id
                JOIN users AS us
                ON r.report_user_id = us.id
                WHERE r.id=:id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Report report = template.queryForObject(sql, param, REPORT_ROW_MAPPER);
        return report;

    }

    public void update(Report report) {
        String sql = """
                UPDATE report
                SET (report=:report,report_category_id=:reportCategoryId,report_bucket_id=:reportBucket,report_user_id=:reportUser,suspicion_user_id=:suspicionUser)
                """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(report);
        template.update(sql, param);
    }

    public void delete(Integer id) {
        String sql = """
                DELETE FROM report WHERE id=:id
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public void insert(Report report) {
        String sql = """
                INSERT INTO report(report,report_category_id,report_bucket_id,report_user_id,suspicion_user_id)
                VALUES(:report,:reportCategoryId,:reportBucket,:reportUser,:suspicionUser)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(report);
        template.update(sql, param);
    }

}
