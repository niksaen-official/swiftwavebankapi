package com.niksaen.swiftwavebankapi.userModule.services

import com.niksaen.swiftwavebankapi.userModule.database
import com.niksaen.swiftwavebankapi.userModule.dto.UserDTO
import com.niksaen.swiftwavebankapi.userModule.dto.mappers.UserMapper
import com.niksaen.swiftwavebankapi.userModule.gson
import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.core.ServerResponseExt

class UserDataService {
    fun create(user: UserDTO): ServerResponse {
        val passport = if(user.passport != null) gson.toJson(user.passport) else "NULL"
        val registrationAddress = if(user.registrationAddress != null) gson.toJson(user.registrationAddress) else "NULL"
        val actualAddress = if(user.actualAddress != null) gson.toJson(user.actualAddress) else "NULL"
        val inn = user.inn ?: 0
        val snils = user.snils ?: 0

        val sql = "INSERT INTO `data` VALUES (" +
                "'${user.id}'," +
                "'${user.name}'," +
                "'${user.surname}'," +
                "'${user.patronymic}'," +
                "'${user.birthDate}'," +
                "'${user.birthPlace}'," +
                "'$passport'," +
                "'$registrationAddress'," +
                "'$actualAddress'," +
                "$inn," +
                "$snils);"
        return try{
            database.execute(sql)
            ServerResponseExt(true,"User is created",user)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun update(user: UserDTO): ServerResponse {
        val passport = if(user.passport != null) gson.toJson(user.passport) else ""
        val registrationAddress = if(user.registrationAddress != null) gson.toJson(user.registrationAddress) else ""
        val actualAddress = if(user.actualAddress != null) gson.toJson(user.actualAddress) else ""
        val inn = user.inn ?: 0
        val snils = user.snils ?: 0

        var sql = "UPDATE `data`" +
                "SET `name` = '${user.name}'," +
                "`surname` = '${user.surname}'," +
                "`patronymic` = '${user.patronymic}'," +
                "`birth_date` = '${user.birthDate}'," +
                "`birth_place` = '${user.birthPlace}'"

        if(passport != "") sql += ",`passport` = '$passport'"
        if(registrationAddress != "") sql += ",`registration_address` = '$registrationAddress'"
        if(actualAddress != "") sql += ",`actual_address` = '$actualAddress'"
        if(inn != 0L) sql += ",`inn` = '$inn'"
        if(snils != 0L) sql += ",`snils` = '$snils'"

        sql += "WHERE `id` = ${user.id}"

        return try{
            database.execute(sql)
            ServerResponseExt(true,"User data is updated",user)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun delete(id:Long):Boolean{
        return try{ database.execute("DELETE FROM `data` WHERE `id` = $id;"); true }
        catch (e:Exception){ false }
    }

    fun addOrUpdatePassport(id:Long,passportDataJson: String): ServerResponse {
        return try{
            var sql = "UPDATE `data` SET `passport` = '$passportDataJson' WHERE `id` = $id;"
            database.execute(sql)
            sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true,"Passport is updated",user)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun addOrUpdateRegistrationAddress(id:Long,addressDataJson: String): ServerResponse {
        return try{
            var sql = "UPDATE `data` SET `registration_address` = '$addressDataJson' WHERE `id` = $id;"
            database.execute(sql)
            sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true,"Registration address is updated",user)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun addOrUpdateActualAddress(id:Long,addressDataJson: String): ServerResponse {
        return try{
            var sql = "UPDATE `data` SET `actual_address` = '$addressDataJson' WHERE `id` = $id;"
            database.execute(sql)
            sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true,"Actual address is updated",user)
        } catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun addOrUpdateInn(id:Long, inn: Long): ServerResponse {
        return try {
            var sql = "UPDATE `data` SET `inn` = '$inn' WHERE `id` = $id"
            database.execute(sql)
            sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true,"Inn is updated",user)
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun addOrUpdateSnils(id:Long,snils:Long): ServerResponse {
        return try {
            var sql = "UPDATE `data` SET `snils` = '$snils' WHERE `id` = $id"
            database.execute(sql)
            sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true,"Snils is updated",user)
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun getData(id:Long): ServerResponse {
        return try {
            val sql = "SELECT * FROM `data` WHERE `id` = $id;"
            val user = database.query(sql, UserMapper())[0]
            ServerResponseExt(true, "User data is get", user)
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }
}