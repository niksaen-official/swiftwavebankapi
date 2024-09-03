package com.niksaen.swiftwaveabnkapi.bankAccountModule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.niksaen.swiftwavebankapi.configurators.DatabaseConfigurator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class BankAccountModule

val database = JdbcTemplate(DatabaseConfigurator().getDatabaseConfig())
val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

fun main() {
    runApplication<BankAccountModule>()
}