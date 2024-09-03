package com.niksaen.swiftwavebankapi.userModule.models

data class EmailVerificationData(
    val email:String,
    val code:Int
)