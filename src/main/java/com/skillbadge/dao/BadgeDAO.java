package com.skillbadge.dao;

import com.skillbadge.model.SBadge;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class BadgeDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(BadgeDAO.class.getName());

    //@Autowired
    public BadgeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertBadge(SBadge badge) {
        String sql = "INSERT INTO sbadge (badge_id, student_id, badge_type, star_rating) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                badge.getBadgeId(),
                badge.getStudentId(),
                badge.getBadgeType(),
                badge.getStars()
        );

        LOGGER.info("Badge inserted for student ID: " + badge.getStudentId());
    }

    public int generateNewBadgeId() {
        String sql = "SELECT COALESCE(MAX(badge_id), 0) + 1 FROM sbadge";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void saveBadge(SBadge badge) {
        insertBadge(badge);
    }
}
