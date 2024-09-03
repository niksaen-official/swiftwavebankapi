package com.niksaen.swiftwavebankapi.configurators

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
@ComponentScan("com.niksaen.swiftwavebankapi")
class DatabaseConfigurator {
    @Bean
    fun getDatabaseConfig(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        dataSource.url = "jdbc:mysql://localhost:3306/swift_wave_bank_db"
        dataSource.username = "root"
        dataSource.password = "0000"
        return dataSource
    }
}