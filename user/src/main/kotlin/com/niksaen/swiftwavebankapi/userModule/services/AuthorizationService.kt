package com.niksaen.swiftwavebankapi.userModule.services

import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.core.ServerResponseExt
import com.niksaen.swiftwavebankapi.userModule.database
import com.niksaen.swiftwavebankapi.userModule.dto.AuthUserDTO
import com.niksaen.swiftwavebankapi.userModule.dto.mappers.AuthUserMapper
import com.niksaen.swiftwavebankapi.userModule.models.AuthorizationResponse
import com.niksaen.swiftwavebankapi.userModule.models.LoginData
import org.springframework.dao.DataAccessException

class AuthorizationService {
    fun create(authUserDTO: AuthUserDTO): ServerResponse {
        var sql = "INSERT INTO `auth_users` VALUES (NULL, '${authUserDTO.email}', '${authUserDTO.phone}', '${authUserDTO.password}',0);"
        try {
            database.execute(sql)
            sql = "SELECT * FROM `auth_users` WHERE `email` = '${authUserDTO.email}' AND `password` = '${authUserDTO.password}'"
            val id = database.query(sql, AuthUserMapper())[0].id
            return if(id>0){
                AuthorizationResponse(true,"You account is created",id)
            } else {
                ServerResponse(false,"Undefined error")
            }
        }
        catch (e:Exception) {
            return if(e.localizedMessage.toString().contains("Duplicate entry")) {
                if (e.localizedMessage.toString().contains("for key 'auth_users.email'"))
                    ServerResponse(false,"A user with this email address already exists")
                else
                    ServerResponse(false,"A user with this phone number already exists")
            } else
                ServerResponse(false,e.localizedMessage.toString())
        }
    }

    fun login(loginData: LoginData): ServerResponse {
        val sql = "SELECT * FROM `auth_users` WHERE `email` = '${loginData.login}' OR `phone` = '${loginData.login}';"
        return try {
            val users = database.query(sql, AuthUserMapper())
            if(users.size == 1){
                if(users[0].password == loginData.password){
                    AuthorizationResponse(true,"You are logged",users[0].id)
                } else{
                    ServerResponse(false,"Forgot password")
                }
            } else {
                ServerResponse(false, "Not found user")
            }
        } catch (e:Exception){
            ServerResponse(false, e.message.toString())
        }
    }

    fun isConfirmUser(id:Long): ServerResponse {
        val sql = "SELECT * FROM `auth_users` WHERE `id` = \"$id\";"
        return try {
            val users = database.query(sql, AuthUserMapper())
            if(users.size == 1){
                if(users[0].isConfirm){
                    ServerResponseExt(true,"You profile is confirmed",true)
                }
                else{
                    ServerResponseExt(true,"You profile is not confirmed",false)
                }
            } else {
                ServerResponse(false, "Not found user")
            }
        } catch (e:Exception){
            ServerResponse(false, e.message.toString())
        }
    }

    fun setNewPassword(id:Long,password:String): ServerResponse {
        return try {
            database.execute("UPDATE `auth_users` SET `password` = $password WHERE `id` = $id;")
            ServerResponse(true,"Password is updated")
        } catch (e:DataAccessException){
            ServerResponse(false,e.message.toString())
        }
    }

    fun delete(id:Long):Boolean{
        return try{
            database.execute("DELETE FROM `auth_users` WHERE `id` = $id;")
            true
        }
        catch (e:Exception){
            false
        }
    }
}