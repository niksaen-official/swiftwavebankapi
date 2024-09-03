package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto

import java.util.Date

data class PaymentAccountDto(
    val id:Long,
    val userId:Long,
    val userPhone:String,
    val balance:Int,
    val currency:String,
    val openDate:Date,
    val closeDate:Date,
    val status:Boolean
)
