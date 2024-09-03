package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.mappers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.CreditAccountDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CreditAccountMapper : RowMapper<CreditAccountDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): CreditAccountDto? {
        return CreditAccountDto(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getInt("max_limit"),
            rs.getInt("balance"),
            rs.getInt("interest_rate"),
            rs.getInt("debt"),
            rs.getInt("accrued_interest"),
            rs.getInt("payments_day"),
            rs.getString("currency"),
            rs.getBoolean("status"),
            rs.getDate("open_date"),
            rs.getDate("close_date"),
            rs.getInt("interest_free_period")
        )
    }
}