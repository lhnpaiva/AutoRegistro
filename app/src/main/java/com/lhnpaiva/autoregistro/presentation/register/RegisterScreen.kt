package com.lhnpaiva.autoregistro.presentation.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.lhnpaiva.autoregistro.R
import com.lhnpaiva.autoregistro.presentation.components.MainAlert
import com.lhnpaiva.autoregistro.presentation.components.MainCurvedButton
import com.lhnpaiva.autoregistro.presentation.theme.GrayColor
import com.lhnpaiva.autoregistro.presentation.theme.PrimaryColor
import com.lhnpaiva.autoregistro.presentation.theme.WhiteColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToHome: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                RegisterUiEvent.RegisterSuccess -> navigateToHome()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_background),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MainAlert(uiState.error.isNotEmpty(), uiState.error)

            Spacer(modifier = Modifier.weight(1f))

            val scope = rememberCoroutineScope()
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = uiState.onEmailChange,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12),
                    label = {
                        Text(
                            text = "Email",
                            color = PrimaryColor
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = PrimaryColor,
                        unfocusedBorderColor = GrayColor,
                        containerColor = WhiteColor.copy(alpha = 0.75f)
                    )
                )

                Spacer(modifier = Modifier.size(16.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = uiState.onPasswordChange,
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12),
                    label = {
                        Text(
                            text = "Senha",
                            color = PrimaryColor
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = PrimaryColor,
                        unfocusedBorderColor = GrayColor,
                        containerColor = WhiteColor.copy(alpha = 0.75f)
                    )
                )

                Spacer(modifier = Modifier.size(16.dp))

                OutlinedTextField(
                    value = uiState.confirmedPassword,
                    onValueChange = uiState.onConfirmPasswordChange,
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12),
                    label = {
                        Text(
                            text = "Confirmar Senha",
                            color = PrimaryColor
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = PrimaryColor,
                        unfocusedBorderColor = GrayColor,
                        containerColor = WhiteColor.copy(alpha = 0.75f)
                    ),
                )

                Spacer(modifier = Modifier.size(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    MainCurvedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        backgroundColor = PrimaryColor,
                        text = "Registrar",
                        onClick = {
                            scope.launch {
                                viewModel.register()
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}
