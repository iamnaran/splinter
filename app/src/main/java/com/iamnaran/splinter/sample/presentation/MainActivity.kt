/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.iamnaran.splinter.core.SplinterAgent
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.SplinterParams
import com.iamnaran.splinter.sample.ui.theme.SampleTheme
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : ComponentActivity() {

    private val eventsState: MutableState<List<Event>> = mutableStateOf(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SampleTheme {
                MainContent(eventsState.value)
            }
        }

        lifecycleScope.launch {
            SplinterAgent.getInstance(this@MainActivity).getSavedEvents().collect {
                eventsState.value = it
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(eventList: List<Event>) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val counterEvent = remember { mutableIntStateOf(0) }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        val properties = mapOf(
            SplinterParams.ITEM_ID to UUID.randomUUID().toString(),
            SplinterParams.ITEM_CONTENT to "screen",
            SplinterParams.ITEM_NAME to "Main Content Launched",
        )
        SplinterAgent.getInstance(context)
            .logSplinterEvent(
                "New Event " + counterEvent.intValue.toString(),
                properties
            )
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Splinter Sample App", fontSize = 14.sp) })
    }, bottomBar = {
        BottomAppBar {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    colors = textButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        SplinterAgent.getInstance(context)
                            .logSplinterEvent(
                                "New Event " + counterEvent.intValue.toString(),
                            )
                        counterEvent.intValue += 1

                    }) {
                    Text(text = "Log New Event")
                }
            }
        }
    }) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            EventList(eventList, listState)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    SampleTheme {
        MainContent(emptyList())
    }
}