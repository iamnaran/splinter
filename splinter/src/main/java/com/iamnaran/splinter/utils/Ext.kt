/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toHumanReadableDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("d MMMM, hh:mm a", Locale.getDefault())
    return format.format(date)
}