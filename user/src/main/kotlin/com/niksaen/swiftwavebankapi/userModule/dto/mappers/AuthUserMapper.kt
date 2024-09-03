package com.niksaen.swiftwavebankapi.userModule.dto.mappers

import com.niksaen.swiftwavebankapi.userModule.dto.AuthUserDTO
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class AuthUserMapper : RowMapper<AuthUserDTO> {
    override fun mapRow(rs: ResultSet, rowNum: Int): AuthUserDTO {
        return AuthUserDTO(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("password"),
            rs.getBoolean("isConfirm")
        )
    }
}