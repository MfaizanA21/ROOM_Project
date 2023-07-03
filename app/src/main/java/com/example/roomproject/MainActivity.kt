package com.example.roomproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.roomproject.ui.theme.ROOMProjectTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var database: MessageDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ROOMProjectTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    database = MessageDatabase.getDatabase(this)
//                    database.messageDao().getMessage().observeForever() { it ->
//                        val list: List<Message> = it
//                        list?.forEach {
//                            Log.d("Response", it.message)
//                        }
//
//                    }
                    GetData()


                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GetData() {


        val name = remember {
            mutableStateOf("")
        }
        val message = remember {
            mutableStateOf("")
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {

            TextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                label = { Text("Messanger") },
                placeholder = { Text(text = "name of a messanger") }
            )
            Spacer(Modifier.padding(vertical = 2.dp))
            LazyColumn(modifier = Modifier.background(Color.Blue)
                .fillMaxWidth()
                .align(CenterHorizontally)) {
                database.messageDao().getMessage().observe(this@MainActivity){ message ->
                    items(message){
                        Text(text = it.message.toString(), modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            TextField(
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                label = { Text(text = "Message") },
                placeholder = { Text(text = "What is the Message?") }
            )
            Spacer(Modifier.padding(vertical = 2.dp))
            Button(onClick = {
                GlobalScope.launch {
                    database.messageDao().sendMessage(Message(0, name.value, message.value))
                }
            }
            ) {
                Text(text = "Send")

            }


        }



        var temp = remember {
            mutableStateOf("")
        }


    }
}
