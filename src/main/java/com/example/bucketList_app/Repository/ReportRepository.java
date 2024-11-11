package com.example.bucketList_app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.bucketList_app.Domain.Report;

@Repository
public class ReportRepository {
    @Autowired
    NamedParameterJdbcTemplate template;

    private static final RowMapper<Report> REPORT_ROW_MAPPER = (rs, i) -> {
        Report report = new Report();
        report.setId(rs.getInt("id"));
        report.setPriority(rs.getString("priority"));
        return report;
    };

    public List<Report> findAll() {
        String sql = "SELECT * FROM report ";
        List<Report> reportList = template.query(sql, REPORT_ROW_MAPPER);
        return reportList;
    }

    public Report findById(Integer id) {
        String sql = "SELECT * FROM report WHERE id= :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Report report = template.queryForObject(sql, param, REPORT_ROW_MAPPER);
        return report;

    }

}
