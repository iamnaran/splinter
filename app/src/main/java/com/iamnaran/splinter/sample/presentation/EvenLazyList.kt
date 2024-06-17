/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.sample.ui.theme.Typography

@Composable
fun EventList(events: List<Event>, lazyListState: LazyListState) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(events.size) {
            EventItem(events[it])
        }
    }
}

@Composable
fun EventItem(event: Event) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {

            },
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            Modifier
                .padding(10.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = event.name,
                    style = Typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = event.formattedDate,
                    style = Typography.bodySmall,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Text(
                text = event.properties,
                style = Typography.labelSmall,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

        }

    }


}
