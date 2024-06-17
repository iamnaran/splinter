/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iamnaran.splinter.data.model.Event

@Composable
fun EventList(events: List<Event>) {

    LazyColumn {

        items(events.size) {

            EventItem(events[it])

        }

    }
}

@Composable
fun EventItem(event: Event) {

    Text(text = event.name, modifier = Modifier.padding(10.dp))

}
