package com.adeshina.service

data class CreateUserParams(
    val fullName: String,
    val avatar: String,
    val email: String,
    val password: String
)
