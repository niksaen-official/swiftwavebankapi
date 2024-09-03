package com.niksaen.swiftwavebankapi.core

open class ServerResponse(
    val isSuccess: Boolean,
    val message:String,
)

class ServerResponseExt<BodyType>(
    isSuccess: Boolean,
    message: String,
    val body:BodyType
): ServerResponse(isSuccess,message)

class AuthorizationResponse(
    isSuccess: Boolean,
    message: String,
    val id:Long
): ServerResponse(isSuccess, message)