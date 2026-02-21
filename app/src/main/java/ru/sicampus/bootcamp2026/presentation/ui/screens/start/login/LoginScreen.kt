package ru.sicampus.bootcamp2026.presentation.ui.screens.start.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import ru.sicampus.bootcamp2026.presentation.ui.navigation.routes.MenuRoute

@Composable
fun LoginScreen(
    nav: NavHostController,
    onBackClick: () -> Unit = { nav.popBackStack() },
    viewModel: LoginViewModel = koinViewModel()
) {


    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        LoginContent(
            viewModel = viewModel,
            nav = nav,
            onLoginClick = { viewModel.authWithCredentials() },
            onBackClick = onBackClick,
            onForgotPasswordClick = { /* TODO */ }
        )
    }

}

@Composable
private fun LoginContent(
    viewModel: LoginViewModel = koinViewModel(),
    nav: NavHostController,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    val isFormValid = viewModel.login.isNotBlank() && viewModel.password.isNotBlank()
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.state, LocalLifecycleOwner.current) {
        viewModel.state.collect { state ->
            when(state) {
                is ScreenIntent.Send -> {
                    nav.navigate(MenuRoute)
                    viewModel.onNavigated()
                }
                else -> {}
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Стрелка назад
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Заголовок
        Text(
            text = "Войти",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Поле логина
        OutlinedTextField(
            value = viewModel.login,

            onValueChange = {
                viewModel.login = it
                isError = false

            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    "Email/Логин",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                if (viewModel.login.isNotEmpty()) {
                    IconButton(onClick = { viewModel.login = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Очистить",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            isError = isError,
            supportingText = if (isError) {
                { Text("Неверный логин или пароль") }
            } else null,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = MaterialTheme.colorScheme.error
            ),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Поле пароля
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {
                viewModel.password = it
                isError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    "Пароль",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (passwordVisible) {
                            "Скрыть пароль"
                        } else {
                            "Показать пароль"
                        },
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            isError = isError,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = MaterialTheme.colorScheme.error
            ),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        // Забыли пароль?
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = "Забыли пароль?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка Войти
        Button(
            onClick = {
                if (isFormValid) {
                    onLoginClick()
                } else {
                    isError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Text(
                text = "Войти",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

// ==== PREVIEW ====
//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun LoginScreenPreview() {
//    AndroidBootcamp2026FrontendTheme {
//        LoginContent(
//            onLoginClick = {},
//            onBackClick = {},
//            onForgotPasswordClick = {}
//        )
//    }
//}
//
//// Превью в тёмной теме
//@Preview(
//    showBackground = true,
//    backgroundColor = 0xFF111318,
//    showSystemUi = true,
//    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
//)
//@Composable
//fun LoginScreenDarkPreview() {
//    AndroidBootcamp2026FrontendTheme {
//        LoginContent(
//            onLoginClick = {},
//            onBackClick = {},
//            onForgotPasswordClick = {}
//        )
//    }
//}