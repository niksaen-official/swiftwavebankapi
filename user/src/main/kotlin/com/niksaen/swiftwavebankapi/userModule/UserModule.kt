package com.niksaen.swiftwavebankapi.userModule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.niksaen.swiftwavebankapi.configurators.DatabaseConfigurator
import com.niksaen.swiftwavebankapi.userModule.services.AuthorizationService
import com.niksaen.swiftwavebankapi.userModule.services.EmailSenderService
import com.niksaen.swiftwavebankapi.userModule.services.UserDataService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class UserModule

val database = JdbcTemplate(DatabaseConfigurator().getDatabaseConfig())
val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
val userDataService = UserDataService()
val authorizationService = AuthorizationService()
val emailSenderService = EmailSenderService()