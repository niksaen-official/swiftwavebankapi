package com.niksaen.swiftwavebankapi.userModule.services

import com.niksaen.swiftwavebankapi.configurators.EmailSenderConfigurator
import com.niksaen.swiftwavebankapi.userModule.database
import com.niksaen.swiftwavebankapi.userModule.dto.mappers.EmailVerificationMapper
import com.niksaen.swiftwavebankapi.core.ServerResponse
import com.niksaen.swiftwavebankapi.userModule.utils.PasswordGenerator
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component


@Component
class EmailSenderService {

    private val emailSender: JavaMailSender = EmailSenderConfigurator().getEmailSenderConfig()

    fun sendVerificationCode(email:String): ServerResponse {
        val code = (100000..999999).random()
        val sql = "INSERT INTO `confirm_email_codes` VALUES (\"$email\", $code);"
        database.execute(sql)

        val message = SimpleMailMessage()
        message.from = "SwiftWaveBank"
        message.subject = "Verification you email"
        message.setTo(email)
        message.text = "You verification code:\n$code"
        emailSender.send(message)

        return ServerResponse(true,"Verification message is send")
    }

    fun verificationEmail(email: String, code: Int): ServerResponse {
        var sql = "SELECT * FROM `confirm_email_codes` WHERE `email` = \"$email\""
        return try {
            val emailCode = database.query(sql, EmailVerificationMapper())[0].code
            if(emailCode == code){
                sql = "UPDATE `auth_users` SET `isConfirm` = '1' WHERE `email` = \"$email\";"
                database.execute(sql)
                sql = "DELETE FROM `confirm_email_codes` WHERE `email` = '$email';"
                database.execute(sql)
                ServerResponse(true,"You email is confirmed")
            }
            else ServerResponse(true,"Not correct code for this email")
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }

    fun sendNewPassword(email:String): ServerResponse {
        val password = PasswordGenerator.password()
        return try{
            database.execute("UPDATE `auth_users` SET `password` = \"$password\" WHERE `email` = \"$email\"")

            val message = SimpleMailMessage()
            message.from = "SwiftWaveBank"
            message.subject = "Set new password"
            message.setTo(email)
            message.text = "Your new password to log into your account:\n$password"
            emailSender.send(message)

            ServerResponse(true,"A new password has been sent to your email.")
        }
        catch (e:Exception){
            ServerResponse(false,e.message.toString())
        }
    }
}