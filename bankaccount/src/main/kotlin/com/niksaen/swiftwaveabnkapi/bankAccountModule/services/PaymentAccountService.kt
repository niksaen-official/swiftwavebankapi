package com.niksaen.swiftwaveabnkapi.bankAccountModule.services

import com.niksaen.swiftwaveabnkapi.bankAccountModule.database
import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.core.ServerResponseExt

class PaymentAccountService : AccountService(TABLE_NAME) {
    companion object {
        const val TABLE_NAME = "payment_accounts"
    }

    fun addPhone(id: Long, userId: Long, phone:String):ServerResponse{
        val sql = "UPDATE `$TABLE_NAME` SET `user_phone` = '$phone' WHERE `id` = '$id' AND 'user_id' = '$userId';"
        return try {
            database.execute(sql)
            val data = (getData(id,userId) as ServerResponseExt<*>)
            ServerResponseExt(true,"Phone number add to this account",data.body)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }
}