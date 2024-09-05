package com.niksaen.swiftwaveabnkapi.bankAccountModule.services

import com.niksaen.swiftwaveabnkapi.bankAccountModule.database
import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.PaymentAccountDto
import com.niksaen.swiftwaveabnkapi.bankAccountModule.dto.mappers.PaymentAccountMapper
import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.AccountCreatingData
import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.core.ServerResponseExt
import java.time.LocalDate

open class AccountService(private val tableName:String) {
    fun getAll(userId:Long):ServerResponse{
        return try{
            val sql = "SELECT * FROM `${tableName}` WHERE `user_id` = \"$userId\";"
            val res = database.query(sql, PaymentAccountMapper())
            if(res.size != 0) {
                ServerResponseExt<List<PaymentAccountDto>>(true,"",res)
            }
            else{
                ServerResponse(false,"Not found accounts")
            }
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }
    fun getData(id:Long,userId:Long):ServerResponse{
        return try{
            val sql = "SELECT * FROM `${tableName}` WHERE `id` = \"$id\" AND `user_id` = \"$userId\";"
            val res = database.query(sql,PaymentAccountMapper())
            if(res.size != 0) {
                ServerResponseExt<PaymentAccountDto>(true,"",res[0])
            }
            else{
                ServerResponse(false,"Not found account data")
            }
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    open fun create(accountCreatingData: AccountCreatingData):ServerResponse{
        val sql = "INSERT INTO `${tableName}` VALUES (NULL, '${accountCreatingData.userId}', '0', '${accountCreatingData.currency}', '2024-09-05', NULL, NULL);"
        return try{
            database.execute(sql)
            ServerResponse(true, "Account is created")
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    open fun close(id:Long,userId:Long):ServerResponse{
        val sql = "UPDATE `${PaymentAccountService.TABLE_NAME}` SET `close_date` = '${LocalDate.now()}' WHERE `id` = '$id' AND `user_id` = '$userId';"
        database.execute(sql)
        return ServerResponse(true,"Account is closed")
    }
}