package com.adeshina.models

import com.adeshina.db.UserTable
import com.adeshina.db.UserTable.autoIncrement

data class User(
    val id: Int,
    val fullName: String,
    val avatar: String,
    val email: String,
    val authToken: String? = null,
    val createdAt: String
)
