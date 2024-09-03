package com.niksaen.swiftwavebankapi.userModule.controllers

import com.niksaen.swiftwavebankapi.userModule.authorizationService
import com.niksaen.swiftwavebankapi.userModule.dto.AuthUserDTO
import com.niksaen.swiftwavebankapi.userModule.emailSenderService
import com.niksaen.swiftwavebankapi.userModule.models.EmailVerificationData
import com.niksaen.swiftwavebankapi.userModule.models.LoginData
import com.niksaen.swiftwavebankapi.userModule.utils.DataValidator
import org.springframework.web.bind.annotation.*
import com.niksaen.swiftwavebankapi.core.ServerResponse

@RestController
class AuthorizationController {

    @PostMapping("auth/create")
    fun create(@RequestBody userDTO: AuthUserDTO): ServerResponse {
        return if(DataValidator.emailVerification(userDTO.email)) {
            if (DataValidator.phoneVerification(userDTO.phone)) {
                authorizationService.create(userDTO)
            }
            else ServerResponse(false,"Phone number is not valid format")
        }
        else ServerResponse(false,"Email is not valid format")
    }

    @GetMapping("auth/login")
    fun login(@RequestBody loginData: LoginData): ServerResponse {
        return if(DataValidator.emailVerification(loginData.login) || DataValidator.phoneVerification(loginData.login)){
            authorizationService.login(loginData)
        }
        else ServerResponse(false, "Login is not valid format")
    }

    @PatchMapping("auth/setNewPassword")
    fun setNewPassword(@RequestHeader("id") id:Long, @RequestBody newPassword:String): ServerResponse {
        return if(id>0){
            authorizationService.setNewPassword(id, newPassword)
        }
        else ServerResponse(false,"Not authorized")
    }

    @PatchMapping("auth/resetPassword")
    fun resetPassword(@RequestBody email: String): ServerResponse {
        return if(DataValidator.emailVerification(email)){
            emailSenderService.sendNewPassword(email)
        }
        else ServerResponse(false,"Email is not valid format")

    }

    @PostMapping("auth/sendVerificationCode")
    fun sendVerificationCode(@RequestBody email:String): ServerResponse {
        return if(DataValidator.emailVerification(email)) emailSenderService.sendVerificationCode(email)
        else ServerResponse(false,"Email is not valid format")
    }

    @PostMapping("auth/confirmEmail")
    fun confirmEmail(@RequestBody emailVerificationData: EmailVerificationData): ServerResponse {
        return if(DataValidator.emailVerification(emailVerificationData.email))
            emailSenderService.verificationEmail(emailVerificationData.email, emailVerificationData.code)
        else ServerResponse(false,"Email is not valid format")
    }
}