package com.adeshina

import com.adeshina.db.DatabaseFactory
import com.adeshina.repository.UserRepositoryImpl
import com.adeshina.routes.authRoutes
import com.adeshina.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
    .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation){
        jackson()
    }
    val service = UserServiceImpl()
    val repository = UserRepositoryImpl(service)
    authRoutes(repository)
}


