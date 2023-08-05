package com.adeshina.routes

import com.adeshina.repository.UserRepository
import com.adeshina.service.CreateUserParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: UserRepository){

    routing {
        route("/auth"){
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}