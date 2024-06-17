/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.iamnaran.splinter.sample.R

val appFonts = FontFamily(
    Font(R.font.light, FontWeight.Light),
    Font(R.font.thin, FontWeight.Thin),
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.bold, FontWeight.SemiBold),
    Font(R.font.extra_bold, FontWeight.Black),
    Font(R.font.itallic, FontWeight.Normal, FontStyle.Italic),
)