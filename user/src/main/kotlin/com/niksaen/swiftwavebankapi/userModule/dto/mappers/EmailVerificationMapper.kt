package com.niksaen.swiftwavebankapi.userModule.dto.mappers

import com.niksaen.swiftwavebankapi.userModule.dto.EmailVerificationDTO
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class EmailVerificationMapper : RowMapper<EmailVerificationDTO> {
    override fun mapRow(rs: ResultSet, rowNum: Int): EmailVerificationDTO {
        return EmailVerificationDTO(
            rs.getString("email"),
            rs.getInt("code"))
    }

}