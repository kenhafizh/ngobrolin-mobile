package com.example.ngobrolinapps.feature.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ngobrolinapps.R
import com.example.ngobrolinapps.feature.login.LoginUiState
import com.example.ngobrolinapps.ui.theme.NgobrolinAppsTheme
import com.example.ngobrolinapps.ui.theme.PrimaryColor

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterSuccess: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is RegisterUiState.Success) onRegisterSuccess()
    }

    var displayName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var displayNameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    fun validateDisplayName(displayName: String): String? {
        if (displayName.isBlank()) return "Nama tampilan tidak boleh kosong"
        return null
    }

    fun validateUsername(username: String): String? {
        if (username.isBlank()) return "Username tidak boleh kosong"
        val usernameRegex = "^[A-Za-z0-9_.]{3,}$".toRegex()
        if (!usernameRegex.matches(username)) return "Username minimal 3 karakter, tanpa spasi"
        return null
    }

    fun validateEmail(email: String): String? {
        if (email.isBlank()) return "Email tidak boleh kosong"
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!emailRegex.matches(email)) return "Format email tidak valid"
        return null
    }

    fun validatePassword(password: String): String? {
        if (password.isBlank()) return "Password tidak boleh kosong"
        if (password.length < 8) return "Password minimal 8 karakter"
        return null
    }

    Scaffold(modifier = modifier) { innerpadding ->
        Column(
            Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.obrolin_logo),
                "Logo",
                modifier = Modifier
                    .size(130.dp)
            )
            Text("Buat Akun Baru", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.padding(top = 8.dp))
            Text(
                "Daftar untuk mulai menggunakan Ngobrolin",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.padding(top = 16.dp))
            TextField(
                value = displayName,
                onValueChange = {
                    displayName = it
                    displayNameError = validateDisplayName(it)
                },
                label = { Text("Nama Tampilan") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                isError = displayNameError != null,
                supportingText = {
                    if (displayNameError != null) Text(
                        displayNameError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier.padding(top = 8.dp))
            TextField(
                value = username,
                onValueChange = {
                    username = it
                    usernameError = validateUsername(it)
                },
                label = { Text("Username") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                isError = usernameError != null,
                supportingText = {
                    if (usernameError != null) Text(
                        usernameError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier.padding(top = 8.dp))
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = validateEmail(it)
                },
                label = { Text("Email") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) Text(
                        emailError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier.padding(top = 8.dp))
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = validatePassword(it)
                },
                label = { Text("Password") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = passwordError != null,
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                supportingText = {
                    if (passwordError != null) Text(
                        passwordError!!,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (passwordVisible) "Sembunyikan Password" else "Tampilkan Password"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    displayNameError = validateDisplayName(displayName)
                    usernameError = validateUsername(username)
                    emailError = validateEmail(email)
                    passwordError = validatePassword(password)

                    if (displayNameError == null && usernameError == null &&
                        emailError == null && passwordError == null
                    ) {
                        viewModel.register(displayName, username, email, password)
                    }
                },
                enabled = uiState !is RegisterUiState.Loading,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                if (uiState is RegisterUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                } else {
                    Text("Daftar", modifier.padding(6.dp))
                }

                if (uiState is RegisterUiState.Error) {
                    Text(
                        (uiState as RegisterUiState.Error).message,
                        color = Color.Red,
                        fontSize = 13.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Text(
                    "or continue with",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Button(
                onClick = {
                    // TODO: Google sign up logic
                }, shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painterResource(R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(16.dp), tint = Color.Unspecified
                )
                Spacer(Modifier.width(8.dp))
                Text("Google", modifier = Modifier.padding(6.dp), color = Color.Black)
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row {
                Text("Sudah punya akun?", fontSize = 14.sp)
                Text(
                    "Masuk",
                    fontSize = 14.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onNavigateToLogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    NgobrolinAppsTheme {
        RegisterScreen()
    }
}
