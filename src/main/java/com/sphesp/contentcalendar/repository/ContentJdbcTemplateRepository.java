package com.sphesp.contentcalendar.repository;

import com.sphesp.contentcalendar.model.Content;
import com.sphesp.contentcalendar.model.Status;
import com.sphesp.contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    // Now we have a JDBC template that we can use to talk to the Database
    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content-type")),
                rs.getObject("date-created", LocalDateTime.class),
                rs.getObject("date-updated", LocalDateTime.class),
                rs.getString("url")
        );
    }

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }

    public void createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content(title, desc,status,content-type, date-created, URL) VALUES (?,?,?,?,NOW(),?)";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL);
    }

    public void updateContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL);
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public boolean existById(int id){
        return getContent(id) != null;
    }

    public Content getContent(int id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        List<Content> contents = jdbcTemplate.query(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        return contents.isEmpty() ? null : contents.get(0);
    }
}
