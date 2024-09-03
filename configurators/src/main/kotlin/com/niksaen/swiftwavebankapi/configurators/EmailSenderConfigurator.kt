package com.niksaen.swiftwavebankapi.configurators

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
@ComponentScan("com.niksaen.swiftwavebankapi")
class EmailSenderConfigurator {
    @Bean
    fun getEmailSenderConfig():JavaMailSender{
        val mailSenderConfig = JavaMailSenderImpl()
        mailSenderConfig.host = "smtp.gmail.com"
        mailSenderConfig.port = 587
        mailSenderConfig.username = "niksaen2003@gmail.com"
        mailSenderConfig.password = "mhcc kojw suhv yffv"

        val properties = mailSenderConfig.javaMailProperties
        properties["mail.transport.protocol"] = "smtp"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.debug"] = "true"

        return mailSenderConfig
    }
}