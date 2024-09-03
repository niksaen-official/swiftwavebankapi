package com.niksaen.swiftwavebankapi.userModule.controllers

import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.core.ServerResponseExt
import com.niksaen.swiftwavebankapi.userModule.authorizationService
import com.niksaen.swiftwavebankapi.userModule.dto.UserDTO
import com.niksaen.swiftwavebankapi.userModule.gson
import com.niksaen.swiftwavebankapi.userModule.models.AddressData
import com.niksaen.swiftwavebankapi.userModule.models.PassportData
import com.niksaen.swiftwavebankapi.userModule.userDataService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @PostMapping("user/create")
    fun create(@RequestHeader("id") id:Long, @RequestBody user: UserDTO): ServerResponse {
        if(id<=0) return ServerResponse(false,"Not authorized")
        if(user.name.isEmpty() || user.surname.isEmpty() || user.patronymic.isEmpty() || user.birthPlace.isEmpty() || user.birthDate.toString() == "")
            return ServerResponse(false,"Not valid user data")

        val res = authorizationService.isConfirmUser(id) as ServerResponseExt<*>
        return if (res.body == true) {
            user.id = id
            userDataService.create(user)
        } else
            ServerResponse(false,"Profile is not confirm")
    }

    @GetMapping("user/data")
    fun get(@RequestHeader("id") id:Long): ServerResponse {
        return userDataService.getData(id)
    }

    @PatchMapping("user/passport")
    fun addOrUpdatePassport(@RequestHeader("id") id:Long, @RequestBody passportData: PassportData): ServerResponse {
        return try{
            val passportDataJson = gson.toJson(passportData)
            userDataService.addOrUpdatePassport(id,passportDataJson)
        }
        catch (e:Exception){
            ServerResponse(false,"Is not correct passport data")
        }
    }

    @PatchMapping("user/registration_address")
    fun addOrUpdateRegistrationAddress(@RequestHeader("id") id:Long, @RequestBody addressData: AddressData): ServerResponse {
        return try{
            val addressDataJson = gson.toJson(addressData)
            userDataService.addOrUpdateRegistrationAddress(id,addressDataJson)
        }
        catch (e:Exception){
            ServerResponse(false,"Is not correct address")
        }
    }

    @PatchMapping("user/actual_address")
    fun addOrUpdateActualAddress(@RequestHeader("id") id:Long, @RequestBody addressData: AddressData): ServerResponse {
        return try{
            val addressDataJson = gson.toJson(addressData)
            userDataService.addOrUpdateActualAddress(id,addressDataJson)
        }
        catch (e:Exception){
            ServerResponse(false,"Is not correct address")
        }
    }

    @PatchMapping("user/inn")
    fun addOrUpdateInn(@RequestHeader("id") id:Long, @RequestBody innData:String): ServerResponse {
        return try {
            val inn = innData.toLong()
            if (inn > 0) {
                userDataService.addOrUpdateInn(id, inn)
            } else ServerResponse(false, "Is not correct INN number")
        }
        catch (e:Exception){
            ServerResponse(false, "Is not correct INN number")
        }
    }

    @PatchMapping("user/snils")
    fun addOrUpdateSnils(@RequestHeader("id") id:Long, @RequestBody snilsData:String): ServerResponse {
        return try {
            val snils = snilsData.toLong()
            if (snils > 0) {
                userDataService.addOrUpdateSnils(id, snils)
            } else ServerResponse(false, "Is not correct SNILS number")
        }
        catch (e:Exception){
            ServerResponse(false, "Is not correct SNILS number")
        }
    }

    @DeleteMapping("user/delete")
    fun delete(@RequestHeader("id") id:Long): ServerResponse {
        val isAuthDataDeleted = authorizationService.delete(id)
        val isUserDataDeleted = userDataService.delete(id)

        return if(isAuthDataDeleted && isUserDataDeleted)
            ServerResponse(true,"User is deleted")
        else
            ServerResponse(false, "Undefined error")
    }
}