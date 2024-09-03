package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.mappers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.CardDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CardMapper : RowMapper<CardDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): CardDto? {
        return CardDto(
            rs.getLong("id"),
            rs.getLong("account_id"),
            rs.getString("cardholder_name"),
            rs.getInt("pin"),
            rs.getInt("cvc"),
            rs.getBoolean("status"),
            rs.getDate("valid_date")
        )
    }
}