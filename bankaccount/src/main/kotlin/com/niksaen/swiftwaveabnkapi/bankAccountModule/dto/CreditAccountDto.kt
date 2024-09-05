package com.niksaen.swiftwaveabnkapi.bankAccountModule.dto

import java.util.*

data class CreditAccountDto(
    val id:Long,
    val userId:Long,
    val maxLimit:Int,
    val balance:Int,
    val interestRate:Int,
    val debt:Int,
    val accruedInterest:Int,
    val paymentsDay:Int,
    val currency:String,
    val openDate: Date,
    val closeDate: Date?,
    val interestFreePeriod:Int
)
