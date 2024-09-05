package com.niksaen.swiftwavebankapi.userModule.models

import com.niksaen.swiftwavebankapi.core.ServerResponse

class AuthorizationResponse(
    isSuccess: Boolean,
    message: String,
    val id:Long
): ServerResponse(isSuccess, message)