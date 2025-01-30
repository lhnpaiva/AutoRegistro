package com.lhnpaiva.autoregistro.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lhnpaiva.autoregistro.presentation.theme.BlackColor
import com.lhnpaiva.autoregistro.presentation.theme.RedColor
import com.lhnpaiva.autoregistro.presentation.theme.WhiteColor

@Composable
fun MainAlert(isVisible: Boolean = false, errorMessage: String) {
    AnimatedVisibility(isVisible) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .border(width = 0.5.dp, color = RedColor, shape = RoundedCornerShape(8.dp))
                .background(WhiteColor), contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorMessage,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                color = BlackColor,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Center
            )
        }
    }
}