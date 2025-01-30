package com.lhnpaiva.autoregistro.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lhnpaiva.autoregistro.presentation.theme.WhiteColor

@Composable
fun MainCurvedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    backgroundColor: Color = Color.Blue,
    textColor: Color = WhiteColor,
) {

    Button(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor),
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}