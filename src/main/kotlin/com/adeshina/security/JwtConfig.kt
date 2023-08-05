package com.adeshina.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.time.LocalDateTime
import java.util.*

class JwtConfig private constructor(secret: String){
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createAccessToken(id: Int): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withExpiresAt(Date(Date().time + (30 * 60 * 1000)))  //set token to expire after 30 minutes
        .withClaim(CLAIM, id)
        .sign(algorithm)


    companion object{
        private const val ISSUER = "story app"
        private const val AUDIENCE = "story app"
        const val CLAIM = "id"

        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String){
            synchronized(this){
                if(!this::instance.isInitialized){
                    instance = JwtConfig(secret)
                }
            }
        }
    }

}