package com.youbout.batchperformance.parallelsteps.vo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceTransactionVORowMapper implements RowMapper<SourceTransactionVO> {
    @Override
    public SourceTransactionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SourceTransactionVO.of(
                rs.getLong("id"),
                rs.getDate("transaction_date").toLocalDate(),
                rs.getLong("account_id"),
                rs.getDouble("amount"),
                rs.getTimestamp("created_at").toLocalDateTime()
                );
    }
}
