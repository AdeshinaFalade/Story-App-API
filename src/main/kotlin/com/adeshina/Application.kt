package com.adeshina

import com.adeshina.db.DatabaseFactory
import com.adeshina.repository.UserRepositoryImpl
import com.adeshina.routes.authRoutes
import com.adeshina.security.JwtConfig
import com.adeshina.security.UserIdPrincipalForUser
import com.adeshina.security.configureSecurity
import com.adeshina.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
    .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(ContentNegotiation){
        jackson()
    }
    configureSecurity()

    val service = UserServiceImpl()
    val repository = UserRepositoryImpl(service)

    authRoutes(repository)

    routing {
        authenticate {
            get("/testurl") {
                val id = call.principal<UserIdPrincipalForUser>()?.id
                call.respond("User id: $id")
            }
        }
    }
}


