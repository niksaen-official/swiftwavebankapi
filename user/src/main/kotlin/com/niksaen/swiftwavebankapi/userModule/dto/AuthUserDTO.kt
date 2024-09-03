package com.niksaen.swiftwavebankapi.userModule.dto

data class AuthUserDTO(
    val id:Long,
    val email:String,
    val phone:String,
    val password:String,
    val isConfirm:Boolean
)