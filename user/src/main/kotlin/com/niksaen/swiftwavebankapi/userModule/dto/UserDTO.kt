package com.niksaen.swiftwavebankapi.userModule.dto

import com.niksaen.swiftwavebankapi.userModule.models.AddressData
import com.niksaen.swiftwavebankapi.userModule.models.PassportData
import java.sql.Date

data class UserDTO(
    var id:Long,
    val name:String,
    val surname:String,
    val patronymic:String,
    val birthDate:Date,
    val birthPlace:String,
    val passport: PassportData?,
    val registrationAddress: AddressData?,
    val actualAddress: AddressData?,
    val inn:Long?,
    val snils:Long?
)
