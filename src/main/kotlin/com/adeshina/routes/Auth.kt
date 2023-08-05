package com.adeshina.routes

import com.adeshina.repository.UserRepository
import com.adeshina.service.CreateUserParams
import com.adeshina.service.LoginParams
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

            post("/login") {
                val params = call.receive<LoginParams>()
                val result = repository.loginUser(params.email, params.password)
                call.respond(result.statusCode, result)
            }
        }
    }
}

//fun Route.orderRouting(){
//    authenticate {
//        get {
//            if (customerStorage.isNotEmpty()) {
//                call.respond(customerStorage)
//            } else {
//                call.respondText("No customers found", status = HttpStatusCode.OK)
//            }
//        }
//        get("{id?}") {
//            val id = call.parameters["id"] ?: return@get call.respondText(
//                "Missing id",
//                status = HttpStatusCode.BadRequest
//            )
//            val customer =
//                customerStorage.find { it.id == id } ?: return@get call.respondText(
//                    "No customer with id $id",
//                    status = HttpStatusCode.NotFound
//                )
//            call.respond(customer)
//        }
//        get("/products") {
//            if (call.request.queryParameters["price"] == "asc") {
//                // Show products from the lowest price to the highest
//            }
//        }
//        post {
//            val customer = call.receive<Customer>()
//            customerStorage.add(customer)
//            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
//        }
//        delete("{id?}") {
//            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
//            if (customerStorage.removeIf { it.id == id }) {
//                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
//            } else {
//                call.respondText("Not Found", status = HttpStatusCode.NotFound)
//            }
//        }
//    }
//}