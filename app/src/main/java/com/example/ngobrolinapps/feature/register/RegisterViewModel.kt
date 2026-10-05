package com.example.ngobrolinapps.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ngobrolinapps.data.remote.dto.AuthResponse
import com.example.ngobrolinapps.data.repository.ApiResult
import com.example.ngobrolinapps.data.repository.AuthRepository
import com.example.ngobrolinapps.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface RegisterUiState {
    data object Init: RegisterUiState
    data object Loading: RegisterUiState
    data class Success(val response: AuthResponse): RegisterUiState
    data class Error(val message: String): RegisterUiState
}

class RegisterViewModel (private val repository: AuthRepository = AuthRepository()): ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Init)
    val  uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(displayName: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            _uiState.value = when (val result = repository.register(
                displayName,
                username,
                email,
                password,
            )){
                is ApiResult.Success -> RegisterUiState.Success(result.data)
                is ApiResult.Error -> RegisterUiState.Error(result.message)
            }
        }
    }
}