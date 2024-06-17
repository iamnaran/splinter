/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.sample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.iamnaran.splinter.core.SplinterAgent
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.sample.ui.theme.SampleTheme
import kotlinx.coroutines.launch

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
    val counterEvent = remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Splinter Sample App", fontSize = 14.sp) })
    }, bottomBar = {
        BottomAppBar {
            Row {
                TextButton(onClick = {
                    SplinterAgent.getInstance(context).logSplinterEvent("New Event $counterEvent.intValue")
                    counterEvent.intValue += 1

                }) {
                    Text(text = "Log New Event")
                }

                TextButton(onClick = {

                }) {
                    Text(text = "Dispatch All Events")
                }
            }
        }
    }) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {


            EventList(eventList)


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