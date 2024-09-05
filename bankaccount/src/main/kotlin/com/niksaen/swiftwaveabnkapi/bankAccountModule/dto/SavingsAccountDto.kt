package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto

import java.util.Date

data class SavingsAccountDto(
    val id:Long,
    val userId:Long,
    val balance:Int,
    val possibleWithdraw:Boolean,
    val currency:String,
    val interestRate:Int,
    val openDate:Date,
    val closeDate:Date?,
)