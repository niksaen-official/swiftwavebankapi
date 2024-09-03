package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.mappers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.SavingsAccountDto
import com.niksaen.swiftwaveabnkapi.bankAccountModule.gson
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class SavingsAccountMapper : RowMapper<SavingsAccountDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): SavingsAccountDto? {
        return SavingsAccountDto(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getInt("balance"),
            rs.getBoolean("possible_withdraw"),
            rs.getString("currency"),
            rs.getInt("interest_rate"),
            rs.getDate("open_date"),
            rs.getDate("close_date"),
            rs.getBoolean("status")
        )
    }

}