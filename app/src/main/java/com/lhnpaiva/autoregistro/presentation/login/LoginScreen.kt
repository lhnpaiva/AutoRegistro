package com.lhnpaiva.autoregistro.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 36.dp)
        ) {
            CurvedMainButton(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                backgroundColor = Color.White,
                text = "Log In with Google",
                textColor = Color.Black,
                onClick = {})

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = "Or")

            Spacer(modifier = Modifier.size(16.dp))

            MainTextField(
                modifier = Modifier.fillMaxWidth(),
                text = email.value,
                onValueChange = { email.value = it },
                trailingIcon = Icons.Outlined.Email
            )

            Spacer(modifier = Modifier.size(16.dp))

            MainTextField(
                modifier = Modifier.fillMaxWidth(),
                text = password.value,
                onValueChange = { password.value = it },
                trailingIcon = Icons.Outlined.Lock
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = "Forgot Password")

            Spacer(modifier = Modifier.size(16.dp))

            CurvedMainButton(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                text = "Log In",
                onClick = {})

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = "Dont't have an account? Sign Up")

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun CurvedMainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    backgroundColor: Color = Color.Blue,
    textColor: Color = Color.White,
) {

    Button(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor),
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}

@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit = {},
    height: Dp = 60.dp,
    trailingIcon: ImageVector? = null
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(height)
                .background(Color(0xFFEFEEEE)),
            textStyle = TextStyle(color = Color.Black),
            trailingIcon = {
                trailingIcon?.let {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = Color(0xFF828282)
                        )
                    }
                }
            }
        )
    }
}

