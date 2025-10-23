package com.wani.sufra.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//import android.widget.Button
//import android.widget.Button

@Composable
fun DynamicButton(modifier: Modifier = Modifier) {
    Button(onClick= {}, modifier = modifier.fillMaxWidth() ) { Text("This is a dynamic button") }
}