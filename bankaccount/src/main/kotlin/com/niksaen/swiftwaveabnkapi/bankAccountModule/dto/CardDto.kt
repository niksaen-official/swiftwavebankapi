package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto

import java.util.Date

data class CardDto(
    val id:Long,
    val accountId:Long,
    val cardholderName:String,
    val pin:Int,
    val cvc:Int,
    val status:Boolean,
    val validDate:Date
)