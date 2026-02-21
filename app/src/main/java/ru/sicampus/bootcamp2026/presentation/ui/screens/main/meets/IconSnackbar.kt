package ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconSnackbar(
    message: String,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
) {
    Snackbar(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(icon, contentDescription = null)
                Spacer(Modifier.width(8.dp))
            }
            Text(message)
        }
    }
}