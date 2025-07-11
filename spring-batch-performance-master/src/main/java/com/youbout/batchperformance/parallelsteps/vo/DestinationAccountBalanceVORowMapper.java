package com.youbout.batchperformance.parallelsteps.vo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationAccountBalanceVORowMapper implements RowMapper<DestinationAccountBalanceVO> {
    @Override
    public DestinationAccountBalanceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DestinationAccountBalanceVO.of(
                rs.getLong("account_id"),
                rs.getDouble("balance")
                );
    }
}
