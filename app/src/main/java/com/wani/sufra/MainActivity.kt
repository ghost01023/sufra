package com.wani.sufra


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.wani.sufra.ui.theme.SufraTheme
import com.wani.sufra.ui.screen.ItemListScreen
import com.wani.sufra.viewmodel.FoodItemViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SufraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Greeting(
                    //     name = "Android",
                    //     modifier = Modifier.padding(innerPadding)
                    // )
                    innerPadding.toString()
                    val itemViewModel = FoodItemViewModel(applicationContext)
                    ItemListScreen(itemViewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}