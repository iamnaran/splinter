/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.presentation

import com.iamnaran.splinter.data.model.Event
data class MainState(
    val allEventsList: List<Event> = emptyList()
)