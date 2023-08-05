package com.adeshina.repository

import com.adeshina.models.User
import com.adeshina.service.CreateUserParams
import com.adeshina.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}