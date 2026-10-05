package com.example.ngobrolinapps.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ngobrolinapps.data.remote.dto.AuthResponse
import com.example.ngobrolinapps.data.repository.ApiResult
import com.example.ngobrolinapps.data.repository.AuthRepository
import com.example.ngobrolinapps.feature.login.LoginUiState.*
import com.example.ngobrolinapps.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface LoginUiState {
    data object Init: LoginUiState
    data object Loading: LoginUiState
    data class Success(val response: AuthResponse): LoginUiState
    data class Error(val message: String): LoginUiState
}

class LoginViewModel (private val repository: AuthRepository = AuthRepository()): ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val  uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            _uiState.value = when (val result = repository.login(email, password)){
                is ApiResult.Success -> LoginUiState.Success(result.data)
                is ApiResult.Error -> LoginUiState.Error(result.message)
            }
        }
    }
}