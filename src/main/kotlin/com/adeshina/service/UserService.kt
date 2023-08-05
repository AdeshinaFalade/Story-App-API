package com.adeshina.service

import com.adeshina.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?

    suspend fun findUserByEmail(email: String): User?
}