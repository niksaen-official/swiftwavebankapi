package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.mappers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.PaymentAccountDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class PaymentAccountMapper : RowMapper<PaymentAccountDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): PaymentAccountDto? {
        return PaymentAccountDto(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("user_phone"),
            rs.getInt("balance"),
            rs.getString("currency"),
            rs.getDate("open_date"),
            rs.getDate("close_date")
        )
    }
}