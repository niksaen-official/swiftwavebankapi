package com.niksaen.swiftwavebankapi.userModule.dto

data class EmailVerificationDTO(
    val email:String,
    val code:Int
)