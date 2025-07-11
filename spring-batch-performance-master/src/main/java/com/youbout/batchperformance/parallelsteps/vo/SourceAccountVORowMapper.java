package com.youbout.batchperformance.parallelsteps.vo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceAccountVORowMapper implements RowMapper<SourceAccountVO> {
    @Override
    public SourceAccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SourceAccountVO.of(
                rs.getLong("id"),
                rs.getString("account_number"),
                rs.getTimestamp("created_at").toLocalDateTime()
                );
    }
}
