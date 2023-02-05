package com.lukic.presentation.ui.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lukic.lungcancerapp.R

private val UbuntuMono = FontFamily(
    Font(R.font.ubuntu_mono_bold, FontWeight.Bold),
    Font(R.font.ubuntu_mono_regular, FontWeight.Normal)
)

val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = UbuntuMono,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)
