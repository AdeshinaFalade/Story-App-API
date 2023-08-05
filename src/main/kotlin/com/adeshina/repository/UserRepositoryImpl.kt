package com.adeshina.repository

import com.adeshina.security.JwtConfig
import com.adeshina.security.hash
import com.adeshina.service.CreateUserParams
import com.adeshina.service.UserService
import com.adeshina.utils.BaseResponse
import io.ktor.http.*

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if(isEmailExist(params.email)){

            BaseResponse.ErrorResponse(message = "Email already registered", statusCode = HttpStatusCode.Conflict)
        } else {
            val user = userService.registerUser(params)
            if(user != null){
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, statusCode = HttpStatusCode.Created)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        val user = userService.findUserByEmail(email)
            ?: return BaseResponse.ErrorResponse(message = "Invalid username or password", statusCode = HttpStatusCode.Unauthorized)
        if(!userService.verifyPassword(user.id, password)){
            return BaseResponse.ErrorResponse(message = "Invalid username or password", statusCode = HttpStatusCode.Unauthorized)
        }
        val token = JwtConfig.instance.createAccessToken(user.id)
        user.authToken = token
        return BaseResponse.SuccessResponse(data = user)
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}