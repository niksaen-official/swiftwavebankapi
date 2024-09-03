package com.niksaen.swiftwavebankapi.userModule.dto.mappers

import com.niksaen.swiftwavebankapi.userModule.dto.UserDTO
import com.niksaen.swiftwavebankapi.userModule.gson
import com.niksaen.swiftwavebankapi.userModule.models.AddressData
import com.niksaen.swiftwavebankapi.userModule.models.PassportData
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserMapper : RowMapper<UserDTO>{
    override fun mapRow(rs: ResultSet, rowNum: Int): UserDTO {
        return UserDTO(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("surname"),
            rs.getString("patronymic"),
            rs.getDate("birth_date"),
            rs.getString("birth_place"),
            gson.fromJson(rs.getString("passport"), PassportData::class.java),
            gson.fromJson(rs.getString("registration_address"), AddressData::class.java),
            gson.fromJson(rs.getString("actual_address"), AddressData::class.java),
            rs.getLong("inn"),
            rs.getLong("snils")
        )
    }
}