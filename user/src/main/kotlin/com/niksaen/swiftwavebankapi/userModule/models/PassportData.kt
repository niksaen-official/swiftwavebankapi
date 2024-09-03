package com.niksaen.swiftwavebankapi.userModule.models

import java.io.Serializable
import java.sql.Date

data class PassportData(
    val number:Long,
    val issuedBy:String,
    val issuedDate:Date,
    val departmentCode:Int
) : Serializable
