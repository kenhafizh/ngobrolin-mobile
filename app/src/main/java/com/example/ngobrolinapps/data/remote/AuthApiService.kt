package com.example.ngobrolinapps.data.remote

import com.example.ngobrolinapps.data.remote.dto.AuthResponse
import com.example.ngobrolinapps.data.remote.dto.LoginRequest
import com.example.ngobrolinapps.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

}