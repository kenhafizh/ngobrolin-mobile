package com.example.ngobrolinapps.data.repository

import com.example.ngobrolinapps.data.remote.AuthApiService
import com.example.ngobrolinapps.data.remote.dto.AuthResponse
import com.example.ngobrolinapps.data.remote.dto.LoginRequest
import com.example.ngobrolinapps.data.remote.NetworkModule
import com.example.ngobrolinapps.data.remote.dto.RegisterRequest
import okio.IOException
import retrofit2.HttpException

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val message: String) : ApiResult<Nothing>
}

class AuthRepository(private val apiService: AuthApiService = NetworkModule.authApiService) {
    suspend fun login(email: String, password: String): ApiResult<AuthResponse> = try {
        ApiResult.Success(apiService.login(LoginRequest(email, password)))
    } catch (e: HttpException) {
        ApiResult.Error(e.message ?: "Login gagal")
    } catch (e: IOException) {
        ApiResult.Error("Koneksi internet bermasalah")
    }

    suspend fun register(
        displayName: String,
        username: String,
        email: String,
        password: String
    ): ApiResult<AuthResponse> = try {
        ApiResult.Success(
            apiService.register(
                RegisterRequest(
                    displayName,
                    username,
                    email,
                    password
                )
            )
        )
    } catch (e: HttpException) {
        ApiResult.Error(e.message ?: "Register gagal")
    } catch (e: IOException) {
        ApiResult.Error("Koneksi internet bermasalah")
    }

}